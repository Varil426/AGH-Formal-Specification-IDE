package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

public class ActivityDiagram extends ModelAggregate{

    private ArrayList<Pattern> patternList;

    @JsonCreator
    public ActivityDiagram(@JsonProperty("id")UUID id){
        super(id);
    }

    public ArrayList<Pattern> getPatternList() {
        return patternList;
    }

    public void addPatternList(Pattern pattern) {
        this.patternList.add(pattern);
        propertyChanged("patternList");
    }

    public void removePatternList(Pattern pattern) {
        if(this.patternList.remove(pattern)) {
            propertyChanged("patternList");
        }
    }

}
