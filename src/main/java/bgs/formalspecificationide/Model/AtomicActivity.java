package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AtomicActivity extends ModelBase {

    private final String atomicActivity;

    @JsonCreator
    public AtomicActivity(@JsonProperty("id") UUID id, @JsonProperty("atomicActivity") String atomicActivity) {
        super(id);
        this.atomicActivity = atomicActivity;
    }

    public String getAtomicActivity() {
        return atomicActivity;
    }
}
