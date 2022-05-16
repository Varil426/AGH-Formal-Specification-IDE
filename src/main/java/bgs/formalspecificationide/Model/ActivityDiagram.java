package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActivityDiagram extends ModelAggregate{

    private ArrayList<Pattern> patternList;

    @JsonCreator
    public ActivityDiagram(@JsonProperty("id")UUID id){
        super(id);
    }

    public List<Pattern> getPatternList() {
        return getChildren().stream().filter(e -> e instanceof Pattern).map(e -> (Pattern) e).toList();
    }

    public void addPatternList(Pattern pattern) {
        addChild(pattern);
    }

    public void removePatternList(Pattern pattern) {
        removeChild(pattern);
    }

}
