package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AtomicActivityCollection extends ModelRootAggregate {

    private final UUID projectId;

    @JsonCreator
    public AtomicActivityCollection(@JsonProperty("id") UUID id, @JsonProperty("projectId") UUID projectId) {
        super(id);
        this.projectId = projectId;
    }

    public List<AtomicActivity> getAtomicActivities() {
        return getChildren().stream().filter(x -> x instanceof AtomicActivity).map(x -> (AtomicActivity)x).toList();
    }

    public Optional<AtomicActivity> getAtomicActivityById(UUID id) {
        return getAtomicActivities().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public UUID getProjectId() {
        return projectId;
    }
}
