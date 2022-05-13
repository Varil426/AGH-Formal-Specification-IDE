package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Pattern extends ModelAggregate{

    private String name;
    private UUID patternTemplateId;
    //PatterntemplateID
    private HashMap<String, UUID> fieldValues;
    //PatternID
    private ArrayList<UUID> inputs;
    //PatternId
    private ArrayList<UUID> outputs;

    @JsonCreator
    public Pattern(@JsonProperty("id") UUID id, String name){
        super(id);
        this.name = name;
        this.fieldValues = new HashMap<>();
        this.outputs = new ArrayList<>();
        this.inputs = new ArrayList<>();
    }

    public void addInput(Pattern pattern){
        this.inputs.add(pattern.getId());
        propertyChanged("inputs");
    }

    public void removeInput(Pattern pattern){

    }
}
