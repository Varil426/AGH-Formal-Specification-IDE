package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.model.Project;
import bgs.formalspecificationide.ui.editors.actionEditor.ActionEditorController;
import bgs.formalspecificationide.ui.editors.scenarioSelector.ScenarioSelectorEditorController;
import bgs.formalspecificationide.ui.editors.useCaseSelector.UseCaseSelectorEditorController;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainWindowController implements IController {

//    private final Injector injector;
    
    private Project project;
    
    @FXML
    private UseCaseSelectorEditorController useCaseSelectorEditorController;

    @FXML
    public ScenarioSelectorEditorController scenarioSelectorEditorController;
    
    @FXML
    public ActionEditorController actionEditorController;
    
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
    
//    @Inject
//    public MainWindow(Injector injector) {
//        super();
//        this.injector = injector;
//
//        var fxmlLoader = new FXMLLoader(getClass().getResource(
//                "MainWindow.fxml"));
//        
////        fxmlLoader.setBuilderFactory(type -> () -> injector.getInstance(type));
//        
////        fxmlLoader.setBuilderFactory(new BuilderFactory() {
////            
////            final BuilderFactory builderFactory = new JavaFXBuilderFactory();
////            
////            @Override
////            public Builder<?> getBuilder(Class<?> type) {
////                var source = injector.getBinding(type).getSource();
////
////
////                if (source instanceof Class<?> sourceAsClass && sourceAsClass.getPackageName().contains("javafx"))
////                    return builderFactory.getBuilder(type);
////                return () -> injector.getInstance(type);
////            }
////        });
//        fxmlLoader.setControllerFactory(injector::getInstance);
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
    public MainWindowController() {
        super();

    }

    @Override
    public void load(ModelBase object) {
        if (object instanceof Project project)
            this.project = project;
        else 
            throw new UnsupportedOperationException();
    }

    @Override
    public void unload() {
        project = null;
        // TODO
    }
}
