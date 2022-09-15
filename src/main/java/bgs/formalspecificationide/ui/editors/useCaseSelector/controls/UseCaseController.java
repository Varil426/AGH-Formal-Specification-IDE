package bgs.formalspecificationide.ui.editors.useCaseSelector.controls;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.model.UseCase;
import bgs.formalspecificationide.ui.IController;
import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

public class UseCaseController implements IController {

    private UseCase useCase;
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private TextField useCaseNameTextField;

    @FXML
    private CheckBox isSelectedCheckBox;

    @FXML
    private CheckBox isImportedCheckBox;

    @FXML
    private Button removeButton;
    
    private Function<AnchorPane, Void> onRemoveClicked;
    
    @Inject
    public UseCaseController() { }
    
    public void initialize() {
        isImportedCheckBox.setDisable(true);
    }
    
    public ObjectProperty<EventHandler<ActionEvent>> onIsSelectedChanged() {
        return isSelectedCheckBox.onActionProperty();
    }

    public void onRemoveButtonClicked() throws InvocationTargetException, IllegalAccessException {
        useCase.getParent().ifPresent(x -> x.removeChild(useCase));
        
        if (onRemoveClicked != null)
            onRemoveClicked.apply(pane);
    }

    public bgs.formalspecificationide.model.UseCase getUseCase() {
        return useCase;
    }

    @Override
    public void load(ModelBase object) {
        if (object instanceof UseCase useCase) {
            this.useCase = useCase;
            useCaseNameTextField.setText(useCase.getUseCaseName());
            isImportedCheckBox.setSelected(useCase.isImported());
            removeButton.setDisable(useCase.isImported());
        }
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void unload() {

    }

    public void setOnRemoveClicked(Function<AnchorPane, Void> onRemoveClicked) {
        this.onRemoveClicked = onRemoveClicked;
    }
    
    @FXML
    private void useCaseNameChanged() {
        useCase.setName(useCaseNameTextField.getText());
    }
}
