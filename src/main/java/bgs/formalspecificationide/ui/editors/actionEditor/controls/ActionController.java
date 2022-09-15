package bgs.formalspecificationide.ui.editors.actionEditor.controls;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.ui.IController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ActionController implements IController {
    
    @FXML
    public TextField textFiled;

    @Override
    public void load(ModelBase object) {
        // TODO
    }

    @Override
    public void unload() {
        // TODO
    }
}
