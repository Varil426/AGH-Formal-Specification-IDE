package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Scenario extends ModelAggregate{

    private ArrayList<String> actions;
    private boolean isMainScenario;
    Set<UUID> atomicActivities;

    @JsonCreator
    public Scenario(@JsonProperty("id")UUID id, boolean isMainScenario){
        super(id);
        this.isMainScenario = isMainScenario;
        this.actions = new ArrayList<>();
    }

    public List<ActivityDiagram> getActivityDiagram() {
        return getChildren().stream().map(e -> (ActivityDiagram) e).toList();
    }

    public void setActivityDiagram(ActivityDiagram activityDiagram) {
        addChild(activityDiagram);
    }

    public void removeActivityDiagram(ActivityDiagram activityDiagram){
        removeChild(activityDiagram);
    }

    public void setMainScenario(boolean isMain){
        this.isMainScenario = isMain;
        propertyChanged("isMainScenario");
    }

    public void addAtomicActivity(UUID atomicId){
        this.atomicActivities.add(atomicId);
        propertyChanged("atomicActivities");
    }

    public void removeAtomicActivity(UUID atomicId){
        if(this.atomicActivities.remove(atomicId)) {
            propertyChanged("atomicActivities");
        }
    }

    public void addActions(String action){
        this.actions.add(action);
        propertyChanged("actions");
    }

    public void removeActions(String action){
        if(this.actions.remove(action)) {
            propertyChanged("actions");
        }
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public boolean isMainScenario() {
        return isMainScenario;
    }

    public Set<UUID> getAtomicActivities() {
        return atomicActivities;
    }


}
