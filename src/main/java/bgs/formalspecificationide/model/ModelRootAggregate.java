package bgs.formalspecificationide.model;

import bgs.formalspecificationide.events.Event;
import bgs.formalspecificationide.events.IsDirtyEvent;
import bgs.formalspecificationide.utilities.IAggregateRoot;
import bgs.formalspecificationide.utilities.ICanSetDirty;
import bgs.formalspecificationide.utilities.IIsDirty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public abstract class ModelRootAggregate extends ModelAggregate implements IIsDirty, ICanSetDirty, IAggregateRoot<ModelBase> {

    @JsonIgnore
    private boolean isDirty;

    public ModelRootAggregate(UUID id) {
        super(id);
        isDirty = false;
    }

    @Override
    public final boolean isDirty() {
        return isDirty;
    }

    @Override
    public final void clearIsDirty() {
        isDirty = false;
    }

    @Override
    public final void setDirty() {
        isDirty = true;
    }

    @Override
    public void notify(Event<?> event) {
        super.notify(event);

        if (event instanceof IsDirtyEvent) {
            isDirty = true;
        }
    }

    @Override
    protected void propertyChanged(String propertyName) {
        super.propertyChanged(propertyName);
        isDirty = true;
    }
}
