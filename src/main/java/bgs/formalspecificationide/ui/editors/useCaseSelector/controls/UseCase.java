package bgs.formalspecificationide.ui.editors.useCaseSelector.controls;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UseCase extends AnchorPane {

    private bgs.formalspecificationide.model.UseCase useCase;
    
    @FXML
    private TextField useCaseNameTextField;

    @FXML
    private CheckBox isSelectedCheckBox;

    @FXML
    private CheckBox isImportedCheckBox;

    @FXML
    private Button removeButton;

    public UseCase(bgs.formalspecificationide.model.UseCase useCase) {
        super();
        this.useCase = useCase;
        
        var fxmlLoader = new FXMLLoader(getClass().getResource(
                "UseCase.fxml"));
        fxmlLoader.setController(this);

        try {
            var result = fxmlLoader.<AnchorPane>load();
            this.getChildren().add(result);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
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
}
