package bgs.formalspecificationide;

import bgs.formalspecificationide.services.IResourceService;
import bgs.formalspecificationide.ui.MainWindow;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FormalSpecificationIde extends Application {

    private final Injector injector = Guice.createInjector(new MainModule());

    private final IResourceService resourceService = injector.getInstance(IResourceService.class);

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(resourceService.getText("ApplicationTitle"));
        
        var root = injector.getInstance(MainWindow.class);

        stage.setScene(new Scene(root));

        stage.show();
    }
}
