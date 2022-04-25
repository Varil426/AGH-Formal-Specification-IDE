package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Utilities.IAggregate;
import bgs.formalspecificationide.Utilities.IAggregateRoot;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelAggregate extends ModelBase implements IAggregate<ModelBase> {

    private final List<ModelBase> children;

    public ModelAggregate() {
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
}
