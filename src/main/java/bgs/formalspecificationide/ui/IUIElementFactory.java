package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.ui.editors.useCaseSelector.controls.UseCaseController;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

public interface IUIElementFactory {
    
    Pair<AnchorPane, UseCaseController> CreateUseCase(); 
    
}
