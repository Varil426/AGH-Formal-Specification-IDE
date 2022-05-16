package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Pattern extends ModelBase{

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

    public void setName(String name){
        this.name = name;
        propertyChanged("name");
    }

    public void addInput(Pattern pattern){
        this.inputs.add(pattern.getId());
        propertyChanged("inputs");
    }

    public void removeInput(Pattern pattern){
        if(this.inputs.remove(pattern.getId())){
            propertyChanged("inputs");
        }
    }

    public void addOutput(Pattern pattern){
        this.outputs.add(pattern.getId());
        propertyChanged("inputs");
    }

    public void removeOutput(Pattern pattern){
        if(this.outputs.remove(pattern.getId())){
            propertyChanged("inputs");
        }
    }

    public void addFieldValue(String fieldID, UUID patternTemplateId){
        boolean changed = false;
        if(fieldValues.get(fieldID) != patternTemplateId){
            changed = true;
        }
        fieldValues.put(fieldID, patternTemplateId);

        if(changed) {
            propertyChanged("relations");
        }
    }

    public void removeRelations(String fieldID, UUID patternTemplateId){
        if(fieldValues.remove(fieldID) != null){
            propertyChanged("relations");
        }
    }
}
