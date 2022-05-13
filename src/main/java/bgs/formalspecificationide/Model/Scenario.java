package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Scenario extends ModelAggregate{

    private ArrayList<String> actions;
    private boolean isMainScenario;
    Set<UUID> atomicActivities;
    private ActivityDiagram activityDiagram;

    @JsonCreator
    public Scenario(@JsonProperty("id")UUID id, boolean isMainScenario){
        super(id);
        this.isMainScenario = isMainScenario;
        this.actions = new ArrayList<>();
    }

    public ActivityDiagram getActivityDiagram() {
        return activityDiagram;
    }

    public void setActivityDiagram(ActivityDiagram activityDiagram) {
        this.activityDiagram = activityDiagram;
        propertyChanged("activityDiagram");
    }

    public void removeActivityDiagram(){
        this.activityDiagram = null;
        propertyChanged("activityDiagram");
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
