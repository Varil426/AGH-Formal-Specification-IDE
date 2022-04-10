package bgs.formalspecificationide.Services;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

/**
 * Event Aggregator Service.
 */
public class EventAggregatorService {

    /**
     * Command interface.
     */
    public interface Command<T extends Event<?>> {
        void execute(T event);
    }

    private final Hashtable<Class<? extends Event<?>>, List<Command<?>>> eventSubscribers = new Hashtable<>();

    @Inject
    public EventAggregatorService() { }

    /**
     * Used to publish an event.
     * @param event An event.
     */
    @SuppressWarnings("unchecked")
    public <T extends Event<?>> void publish(T event) {
        if (eventSubscribers.containsKey(event.getClass())) {
            var commands = eventSubscribers.get(event.getClass());
            for (var command :
                    commands.stream().map(command -> {
                        try {
                            return (Command<T>) command;
                        }
                        catch (ClassCastException e) {
                            return null;
                        }
                    }).filter(Objects::nonNull).toList()) {
                command.execute(event);
            }
        }
    }

    /**
     * Subscribes to a given event.
     * @param eventType An event type.
     * @param command A command to be executed on event.
     */
    public <T extends Event<?>> void subscribe(Class<T> eventType, Command<T> command) {
        if (!eventSubscribers.contains(eventType.getTypeName())) {
            eventSubscribers.put(eventType, new ArrayList<>());
        }

        eventSubscribers.get(eventType).add(command);
    }
}
