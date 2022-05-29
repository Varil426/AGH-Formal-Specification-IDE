package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ProjectNameList extends ModelRootAggregate {

    @JsonCreator
    public ProjectNameList(@JsonProperty("id") UUID id) {
        super(id);
    }
}
