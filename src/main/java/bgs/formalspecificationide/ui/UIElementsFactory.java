package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.ui.editors.useCaseSelector.controls.UseCaseController;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.io.IOException;

class UIElementsFactory implements IUIElementFactory {

    private final Injector injector;

//    private final FXMLLoader fxmlLoader;
    
    private final String editorsPath = "editors/";
    
    private final String useCaseSelectorControlsPath = editorsPath + "useCaseSelector/controls/";
    
    @Inject
    public UIElementsFactory(Injector injector) {

        this.injector = injector;
//        fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(injector::getInstance);
    }
    
    @Override
    public Pair<AnchorPane, UseCaseController> CreateUseCase() {
        return loadFromFxmnl(useCaseSelectorControlsPath + "UseCase.fxml");
    }
    
    private <TUIElement, TController> Pair<TUIElement, TController> loadFromFxmnl(String path) {
        try {
            var fxmlLoader = new FXMLLoader(getClass().getResource(path));
            fxmlLoader.setControllerFactory(injector::getInstance);
            
            var result = fxmlLoader.<TUIElement>load();
            var controller = fxmlLoader.<TController>getController();
            return new Pair<>(result, controller);
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO
        }

    }
}
