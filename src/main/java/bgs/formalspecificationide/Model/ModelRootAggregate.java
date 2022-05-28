package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Events.Event;
import bgs.formalspecificationide.Events.IsDirtyEvent;
import bgs.formalspecificationide.Utilities.IAggregateRoot;
import bgs.formalspecificationide.Utilities.ICanSetDirty;
import bgs.formalspecificationide.Utilities.IIsDirty;
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
