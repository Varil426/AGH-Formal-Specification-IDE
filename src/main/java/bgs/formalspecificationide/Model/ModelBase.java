package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Utilities.Event;
import bgs.formalspecificationide.Utilities.IObservable;
import bgs.formalspecificationide.Utilities.IObserver;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.UUID;

public abstract class ModelBase implements IObservable, IObserver {

    public static class PropertyChangedEvent extends Event<ModelBase> {

        private final String propertyName;


        public PropertyChangedEvent(ModelBase publisher, String propertyName) {
            super(publisher);
            this.propertyName = propertyName;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }

    public static class IsDirtyEvent extends Event<ModelBase> {

        public IsDirtyEvent(ModelBase publisher) {
            super(publisher);
        }
    }

    @JsonCreator
    public ModelBase(@JsonProperty("id") UUID id) {
        this.id = id;
    }

    private final UUID id;

    public UUID getId() {
        return id;
    }

    @JsonIgnore
    private final HashSet<IObserver> observers = new HashSet<>();

    @Override
    public final void subscribe(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public final void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    protected final void notifyObservers(Event<?> event) {
        for (var observer : observers) {
            observer.notify(event);
        }
    }

    protected void propertyChanged(String propertyName) {
        notifyPropertyChanged(propertyName);
    }

    private void notifyPropertyChanged(String propertyName) {
        notifyObservers(new PropertyChangedEvent(this, propertyName));
        notifyObservers(new IsDirtyEvent(this));
    }

    @Override
    public void notify(Event<?> event) {
        if (event instanceof IsDirtyEvent) {
            notifyObservers(event);
        }
    }
}
