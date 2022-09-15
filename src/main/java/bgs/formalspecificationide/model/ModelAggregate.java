package bgs.formalspecificationide.model;

import bgs.formalspecificationide.events.Event;
import bgs.formalspecificationide.events.IsDirtyEvent;
import bgs.formalspecificationide.events.modelEvents.ChildAddedEvent;
import bgs.formalspecificationide.events.modelEvents.ChildRemovedEvent;
import bgs.formalspecificationide.utilities.IAggregate;
import bgs.formalspecificationide.utilities.IAggregateRoot;
import bgs.formalspecificationide.utilities.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class ModelAggregate extends ModelBase implements IAggregate<ModelBase>, IObserver {

    private final List<ModelBase> children;

    public ModelAggregate(UUID id) {
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