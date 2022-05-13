package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UseCase extends ModelAggregate{

    private String useCaseName;
    private HashMap<UUID, ArrayList<RelationEnum>> relations;
    private ArrayList<Scenario> scenarioList;

    public enum RelationEnum {
        EXTEND,
        INCLUDE,
    }

    @JsonCreator
    public UseCase(@JsonProperty("id")UUID id, String name){
        super(id);
        this.useCaseName = name;
        this.relations = new HashMap<>();
        this.scenarioList = new ArrayList<>();
    }

    public void setName(String name) {
        this.useCaseName = name;
        propertyChanged("useCaseName");
    }

    public void addScenario(Scenario scenario) {
        this.scenarioList.add(scenario);
        propertyChanged("scenarioList");
    }

    public void removeScenario(Scenario scenario) {
        if(this.scenarioList.remove(scenario)) {
            propertyChanged("scenarioList");
        }
    }

    public ArrayList<Scenario> getScenarioList() {
        return scenarioList;
    }

    public void addRelations(UUID otherUseCaseId, RelationEnum relation){
        if(relations.containsKey(otherUseCaseId)){
            relations.get(otherUseCaseId).add(relation);
        }
        else{
            ArrayList<RelationEnum> newRelations = new ArrayList<>();
            newRelations.add(relation);
            relations.put(otherUseCaseId, newRelations);
        }
        propertyChanged("relations");
    }

    public void removeRelations(UUID otherUseCaseId, RelationEnum relation){
        boolean removed = relations.get(otherUseCaseId).remove(relation);
        if(relations.get(otherUseCaseId).isEmpty()){
            relations.remove(otherUseCaseId);
        }
        if(removed) {
            propertyChanged("relations");
        }
    }

    public String getUseCaseName() {
        return useCaseName;
    }

    public HashMap<UUID, ArrayList<RelationEnum>> getRelations() {
        return relations;
    }


}
