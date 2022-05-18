package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class UseCaseDiagram extends ModelAggregate {

    private UUID imageID;

    @JsonCreator
    public UseCaseDiagram(@JsonProperty("id")UUID id, @JsonProperty("imageID") UUID imageID) {
        super(id);
        this.imageID = imageID;
    }

    public List<UseCase> getUseCaseList() {

        return getChildren().stream().filter(e -> e instanceof UseCase).map(e -> (UseCase) e).toList();
    }

    public void addUseCase(UseCase useCase){
        addChild(useCase);
    }

    public void removeUseCase(UseCase useCase){
        removeChild(useCase);
    }

    public UUID getImageID() {
        return imageID;
    }

    public void setImageID(UUID imageID) {
        this.imageID = imageID;
    }
}
