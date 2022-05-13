package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Utilities.Event;
import bgs.formalspecificationide.Utilities.IAggregateRoot;
import bgs.formalspecificationide.Utilities.IIsDirty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

// TODO Add the rest of properties
public class Project extends ModelAggregate implements IIsDirty, IAggregateRoot<ModelBase> {

    @JsonIgnore
    private boolean isDirty;

    private String name;
    private ArrayList<UseCaseDiagram> useCaseDiagramList;

    @JsonCreator
    public Project(@JsonProperty("id") UUID id) {
        super(id);
        isDirty = false;
        this.useCaseDiagramList= new ArrayList<>();
    }

    public ArrayList<UseCaseDiagram> getUseCaseDiagramList() {
        return useCaseDiagramList;
    }

    public void addUseCaseDiagram(UseCaseDiagram useCaseDiagram){
        this.useCaseDiagramList.add(useCaseDiagram);
        propertyChanged("useCaseDiagramList");
    }

    public void removeUseCaseDiagram(UseCaseDiagram useCaseDiagram){
        if(this.useCaseDiagramList.remove(useCaseDiagram)) {
            propertyChanged("useCaseDiagramList");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        propertyChanged("Name");
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
