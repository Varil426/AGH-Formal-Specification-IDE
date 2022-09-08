package bgs.formalspecificationide.ui.editors.useCaseSelector;

import bgs.formalspecificationide.factories.IModelFactory;
import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.model.UseCaseDiagram;
import bgs.formalspecificationide.ui.IDomainAware;
import bgs.formalspecificationide.ui.IUIElementFactory;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class UseCaseSelectorEditorController implements IDomainAware {

    @FXML
    private ListView<AnchorPane> useCasesList;

    @FXML
    private Button addOptionalUseCaseButton;
    
    @FXML
    private Label currentlySelectedUseCaseLabel;
    private final IModelFactory modelFactory;
    private final IUIElementFactory iuiElementFactory;

    private UseCaseDiagram useCaseDiagram;
    
//    public UseCaseSelectorEditor(){
//        modelFactory = null;
//    }
    
    @Inject
    public UseCaseSelectorEditorController(IModelFactory modelFactory, IUIElementFactory iuiElementFactory) {        
        super();
        this.modelFactory = modelFactory;
        this.iuiElementFactory = iuiElementFactory;

//        var fxmlLoader = new FXMLLoader(getClass().getResource(
//                "UseCaseSelectorEditor.fxml"));
//        fxmlLoader.setController(this);
//
//        try {
//            var result = fxmlLoader.<AnchorPane>load();
//            this.getChildren().add(result);
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }

        // Event Handling
        // addOptionalUseCaseButton.setOnAction(actionEvent -> addUseCaseButtonClicked()); // TODO Event should be moved to IEventAggregator

    }
    
    public void addUseCaseButtonClicked() {
//        var useCase = new UseCaseController(modelFactory.createUseCase(useCaseDiagram, UUID.randomUUID(), "TEST"));
//        useCase.onRemoveButtonClicked().addListener(x -> useCasesList.getItems().remove(useCase));
//        useCase.onIsSelectedChanged().addListener(x -> currentlySelectedUseCaseLabel.setText(String.format("Currently selected: %s", useCase.getUseCase().getUseCaseName())));
//        useCasesList.getItems().add(useCase);
        
        var uiElementPair = iuiElementFactory.CreateUseCase();
        // TODO LOAD
        useCasesList.getItems().add(uiElementPair.getKey());
    }

    @Override
    public void load(ModelBase object) {
        if (object instanceof UseCaseDiagram useCaseDiagram)
            this.useCaseDiagram = useCaseDiagram;
        else
            throw new UnsupportedOperationException();
    }

    @Override
    public void unload() {
        // TODO
    }

}
