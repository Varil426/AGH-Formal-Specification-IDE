package bgs.formalspecificationide.ui.editors.scenarioSelector;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.model.UseCase;
import bgs.formalspecificationide.ui.IEditor;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ScenarioSelectorEditor extends AnchorPane implements IEditor {
    
    private UseCase useCase;
    
    public ScenarioSelectorEditor() {
        super();

        var fxmlLoader = new FXMLLoader(getClass().getResource(
                "ScenarioSelectorEditor.fxml"));
        fxmlLoader.setController(this);

        try {
            var result = fxmlLoader.<AnchorPane>load();
            this.getChildren().add(result);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @Override
    public void load(ModelBase object) {
        if (object instanceof UseCase useCase)
            this.useCase = useCase;
        else 
            throw new UnsupportedOperationException("Unsupported type");
    }
    
}
