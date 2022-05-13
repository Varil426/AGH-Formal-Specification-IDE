package bgs.formalspecificationide.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

public class UseCaseDiagram extends ModelAggregate {

    private ArrayList<UseCase> useCaseList;

    @JsonCreator
    public UseCaseDiagram(@JsonProperty("id")UUID id) {
        super(id);
        this.useCaseList = new ArrayList<>();
    }

    public ArrayList<UseCase> getUseCaseList() {
        return useCaseList;
    }

    public void addUseCase(UseCase useCase){
        this.useCaseList.add(useCase);
        propertyChanged("useCaseList");
    }

    public void removeUseCase(UseCase useCase){
        if(this.useCaseList.remove(useCase)) {
            propertyChanged("useCaseList");
        }
    }
}
