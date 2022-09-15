package bgs.formalspecificationide;

import bgs.formalspecificationide.services.IResourceService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FormalSpecificationIde extends Application {

    private final Injector injector = Guice.createInjector(new MainModule());

    private final IResourceService resourceService = injector.getInstance(IResourceService.class);
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(resourceService.getText("ApplicationTitle"));
        stage.setResizable(false);

        fxmlLoader = new FXMLLoader(getClass().getResource("ui/MainWindow.fxml"));
        fxmlLoader.setControllerFactory(injector::getInstance);
        var root = new AnchorPane();
        fxmlLoader.setRoot(root);
        fxmlLoader.<AnchorPane>load();
        stage.setScene(new Scene(root));

        stage.show();
    }
}