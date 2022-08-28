package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.model.Project;
import bgs.formalspecificationide.ui.editors.useCaseSelector.UseCaseSelectorEditor;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainWindow extends AnchorPane implements IEditor {

    private final Injector injector;
    
    private Project project;
    
    @FXML
    private UseCaseSelectorEditor useCaseSelectorEditor;
    
//    public MainWindow() {
//        injector = null;
//
//        var fxmlLoader = new FXMLLoader(getClass().getResource(
//                "MainWindow.fxml"));
//
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//
//    }    
    
    @Inject
    public MainWindow(Injector injector) {
        super();
        this.injector = injector;

        var fxmlLoader = new FXMLLoader(getClass().getResource(
                "MainWindow.fxml"));

        fxmlLoader.setBuilderFactory(type -> () -> injector.getInstance(type));
        fxmlLoader.setControllerFactory(injector::getInstance);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
    }

    @Override
    public void load(ModelBase object) {
        if (object instanceof Project project)
            this.project = project;
        else 
            throw new UnsupportedOperationException();
    }
}
