package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Utilities.Event;
import bgs.formalspecificationide.Utilities.IAggregateRoot;
import bgs.formalspecificationide.Utilities.ICanSetDirty;
import bgs.formalspecificationide.Utilities.IIsDirty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AtomicActivityCollection extends ModelAggregate implements IIsDirty, ICanSetDirty, IAggregateRoot<ModelBase> {

    private final UUID projectId;

    @JsonIgnore
    private boolean isDirty;

    @JsonCreator
    public AtomicActivityCollection(@JsonProperty("id") UUID id, @JsonProperty("projectId") UUID projectId) {
        super(id);
        this.projectId = projectId;
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
    public void setDirty() {
        isDirty = true;
    }

    @Override
    public void notify(Event<?> event) {
        super.notify(event);

        if (event instanceof IsDirtyEvent) {
            isDirty = true;
        }
    }

    public List<AtomicActivity> getAtomicActivities() {
        return getChildren().stream().filter(x -> x instanceof AtomicActivity).map(x -> (AtomicActivity)x).toList();
    }

    public Optional<AtomicActivity> getAtomicActivityById(UUID id) {
        return getAtomicActivities().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public UUID getProjectId() {
        return projectId;
    }
}
