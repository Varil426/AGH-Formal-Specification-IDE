package bgs.formalspecificationide.ui.editors.imageViewer;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.ui.IController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ImageViewerController implements IController {

    @FXML
    private Button addImageButton;
    
    @FXML
    private ImageView imageView;
    
    public void onClick() {
        // TODO
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
