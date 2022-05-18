package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Project extends ModelRootAggregate {

    private String name;

    private UUID atomicActivityCollectionId;

    @JsonCreator
    public Project(@JsonProperty("id") UUID id) {
        super(id);
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

    public UUID getAtomicActivityCollectionId() {
        return atomicActivityCollectionId;
    }

    public void setAtomicActivityCollectionId(UUID atomicActivityCollectionId) {
        this.atomicActivityCollectionId = atomicActivityCollectionId;
    }
}
