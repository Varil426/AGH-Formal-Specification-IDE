package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Events.ModelEvents.ChildAddedEvent;
import bgs.formalspecificationide.Events.ModelEvents.ChildRemovedEvent;
import bgs.formalspecificationide.Events.IsDirtyEvent;
import bgs.formalspecificationide.Utilities.*;
import bgs.formalspecificationide.Events.Event;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class ModelAggregate extends ModelBase implements IAggregate<ModelBase>, IObserver {

    private final List<ModelBase> children;

    @JsonCreator
    public ModelAggregate(@JsonProperty("id") UUID id) {
        super(id);
        children = new ArrayList<>();
    }

    @Override
    public List<ModelBase> getChildren() {
        return children.stream().toList();
    }

    @Override
    public void addChild(ModelBase child) {
        if (child instanceof IAggregateRoot<?>) {
            throw new RuntimeException("AggregateRoot cannot be a child.");
        }

        children.add(child);
        child.subscribe(this);

        notifyObservers(new ChildAddedEvent(this, child));
        notifyDirty();
    }

    @Override
    public void addChildren(List<ModelBase> children) {
        for (var child : children) {
            addChild(child);
        }
    }

    @Override
    public void removeChild(ModelBase child) {
        children.remove(child);
        child.unsubscribe(this);

        notifyObservers(new ChildRemovedEvent(this, child));
        notifyDirty();
    }

    @Override
    public void removeChildren(List<ModelBase> children) {
        for (var child : children) {
            removeChild(child);
        }
    }

    @Override
    public void notify(Event<?> event) {
        if (event instanceof IsDirtyEvent) {
            notifyObservers(event);
        }
    }

    @Override
    public <Z extends ModelBase> List<Z> getChildrenOfType(Class<Z> type) {
        return getChildren().stream().filter(x -> type.isAssignableFrom(x.getClass())).map(type::cast).toList();
    }
}