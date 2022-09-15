package bgs.formalspecificationide.ui.editors.actionEditor;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.ui.IController;
import com.google.inject.Inject;

public class ActionEditorController implements IController {
    
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
