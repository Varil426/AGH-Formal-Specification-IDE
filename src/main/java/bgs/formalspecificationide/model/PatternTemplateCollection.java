package bgs.formalspecificationide.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PatternTemplateCollection extends ModelRootAggregate {

    @JsonCreator
    public PatternTemplateCollection(@JsonProperty("id") UUID id) {
        super(id);
    }

    public List<PatternTemplate> getPatternTemplates() {
        return getChildren().stream().filter(x -> x instanceof PatternTemplate).map(x -> (PatternTemplate)x).toList();
    }

    public Optional<PatternTemplate> getPatternTemplateById(UUID id) {
        return getPatternTemplates().stream().filter(x -> x.getId().equals(id)).findFirst();
    }
}
