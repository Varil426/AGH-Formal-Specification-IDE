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
        return getChildrenOfType(UseCase.class);
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
        if (!this.imageID.equals(imageID)) {
            this.imageID = imageID;
            propertyChanged("imageId");
        }
    }
}
