package bgs.formalspecificationide.ui.editors.useCaseSelector.controls;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.model.UseCase;
import bgs.formalspecificationide.ui.IDomainAware;
import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class UseCaseController implements IDomainAware {

    private UseCase useCase;
    
    @FXML
    private TextField useCaseNameTextField;

    @FXML
    private CheckBox isSelectedCheckBox;

    @FXML
    private CheckBox isImportedCheckBox;

    @FXML
    private Button removeButton;

//    public UseCaseController(bgs.formalspecificationide.model.UseCase useCase) {
//        super();
//        this.useCase = useCase;
//        
//        var fxmlLoader = new FXMLLoader(getClass().getResource(
//                "UseCase.fxml"));
//        fxmlLoader.setController(this);
//
//        try {
//            var result = fxmlLoader.<AnchorPane>load();
//            this.getChildren().add(result);
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//    }
    
    @Inject
    public UseCaseController() {
        
    }
    
    public ObjectProperty<EventHandler<ActionEvent>> onIsSelectedChanged() {
        return isSelectedCheckBox.onActionProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onRemoveButtonClicked() {
        return removeButton.onActionProperty();
    }

    public bgs.formalspecificationide.model.UseCase getUseCase() {
        return useCase;
    }

    @Override
    public void load(ModelBase object) {
        if (object instanceof UseCase useCase)
            this.useCase = useCase;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void unload() {

    }
}
