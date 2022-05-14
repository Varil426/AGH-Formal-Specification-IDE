package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Utilities.Event;
import bgs.formalspecificationide.Utilities.IAggregate;
import bgs.formalspecificationide.Utilities.IAggregateRoot;
import bgs.formalspecificationide.Utilities.IObserver;
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
}
