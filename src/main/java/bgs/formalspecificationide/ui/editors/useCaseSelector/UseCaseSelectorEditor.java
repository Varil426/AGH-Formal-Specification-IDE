package bgs.formalspecificationide.ui.editors.useCaseSelector;

import bgs.formalspecificationide.factories.IModelFactory;
import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.model.UseCaseDiagram;
import bgs.formalspecificationide.ui.IEditor;
import bgs.formalspecificationide.ui.editors.useCaseSelector.controls.UseCase;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.UUID;

public class UseCaseSelectorEditor extends AnchorPane implements IEditor {

    @FXML
    private ListView<UseCase> useCasesList;

    @FXML
    private Button addOptionalUseCaseButton;
    
    @FXML
    private Label currentlySelectedUseCaseLabel;
    private final IModelFactory modelFactory;

    private UseCaseDiagram useCaseDiagram;
    
//    public UseCaseSelectorEditor(){
//        modelFactory = null;
//    }
    
    @Inject
    public UseCaseSelectorEditor(IModelFactory modelFactory) {        
        super();
        this.modelFactory = modelFactory;

        var fxmlLoader = new FXMLLoader(getClass().getResource(
                "UseCaseSelectorEditor.fxml"));
        fxmlLoader.setController(this);

        try {
            var result = fxmlLoader.<AnchorPane>load();
            this.getChildren().add(result);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Event Handling
        // addOptionalUseCaseButton.setOnAction(actionEvent -> addUseCaseButtonClicked()); // TODO Event should be moved to IEventAggregator

    }
    
    public void addUseCaseButtonClicked() {
        var useCase = new UseCase(modelFactory.createUseCase(useCaseDiagram, UUID.randomUUID(), "TEST"));
        useCase.onRemoveButtonClicked().addListener(x -> useCasesList.getItems().remove(useCase));
        useCase.onIsSelectedChanged().addListener(x -> currentlySelectedUseCaseLabel.setText(String.format("Currently selected: %s", useCase.getUseCase().getUseCaseName())));
        useCasesList.getItems().add(useCase);
    }

    @Override
    public void load(ModelBase object) {
        if (object instanceof UseCaseDiagram useCaseDiagram)
            this.useCaseDiagram = useCaseDiagram;
        else
            throw new UnsupportedOperationException();
    }
    
}
