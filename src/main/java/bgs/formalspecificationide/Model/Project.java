package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Events.Event;
import bgs.formalspecificationide.Events.IsDirtyEvent;
import bgs.formalspecificationide.Utilities.IAggregateRoot;
import bgs.formalspecificationide.Utilities.ICanSetDirty;
import bgs.formalspecificationide.Utilities.IIsDirty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Project extends ModelAggregate implements IIsDirty, ICanSetDirty, IAggregateRoot<ModelBase> {

    @JsonIgnore
    private boolean isDirty;

    private String name;

    private UUID atomicActivityCollectionId;

    @JsonCreator
    public Project(@JsonProperty("id") UUID id) {
        super(id);
        isDirty = false;
    }

    public List<UseCaseDiagram> getUseCaseDiagramList() {
        return getChildren().stream().filter(e -> e instanceof UseCaseDiagram).map(e -> (UseCaseDiagram) e).toList();
    }

    public void addUseCaseDiagram(UseCaseDiagram useCaseDiagram){
        addChild(useCaseDiagram);
    }

    public void removeUseCaseDiagram(UseCaseDiagram useCaseDiagram){
        removeChild(useCaseDiagram);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        propertyChanged("Name");
    }

    @Override
    public boolean isDirty() {
        return isDirty;
    }

    public UUID getAtomicActivityCollectionId() {
        return atomicActivityCollectionId;
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

    @Override
    protected void propertyChanged(String propertyName) {
        super.propertyChanged(propertyName);
        isDirty = true;
    }

    public void setAtomicActivityCollectionId(UUID atomicActivityCollectionId) {
        this.atomicActivityCollectionId = atomicActivityCollectionId;
    }
}
