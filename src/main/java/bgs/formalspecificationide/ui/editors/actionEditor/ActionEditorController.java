package bgs.formalspecificationide.ui.editors.actionEditor;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.ui.IDomainAware;
import com.google.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ActionEditorController implements IDomainAware {
    
//    public ActionEditorController() {
//        super();
//
//        var fxmlLoader = new FXMLLoader(getClass().getResource(
//                "ActionEditor.fxml"));
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
    public ActionEditorController() {
        
    }
    
    @Override
    public void load(ModelBase object) {
        // TODO
    }

    @Override
    public void unload() {
        // TODO
    }
}
