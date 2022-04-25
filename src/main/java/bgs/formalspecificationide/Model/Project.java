package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Utilities.Event;
import bgs.formalspecificationide.Utilities.IAggregateRoot;
import bgs.formalspecificationide.Utilities.IIsDirty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

// TODO Add the rest of properties
public final class Project extends ModelAggregate implements IIsDirty, IAggregateRoot<ModelBase> {

    @JsonIgnore
    private boolean isDirty;

    private UUID id;

    private String name;

    public Project() {
        isDirty = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        propertyChanged("Name");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public void clearIsDirty() {
        isDirty = false;
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
