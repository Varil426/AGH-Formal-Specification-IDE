package bgs.formalspecificationide.ui.editors.actionEditor;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.ui.IEditor;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ActionEditor extends AnchorPane implements IEditor {
    
    public ActionEditor() {
        super();

        var fxmlLoader = new FXMLLoader(getClass().getResource(
                "ActionEditor.fxml"));
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
        // TODO
    }
}
