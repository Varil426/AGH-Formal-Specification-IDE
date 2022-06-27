package bgs.formalspecificationide.tutorial3;/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */

import java.net.URL;

import io.github.eckig.grapheditor.GraphEditor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A demo application to show uses of the {@link GraphEditor} library.
 */
public class GraphEditorDemo extends Application {

    private static final String APPLICATION_TITLE = "Graph Editor Demo";
    private static final String DEMO_STYLESHEET = "demo.css";
//    private static final String TREE_SKIN_STYLESHEET = "treeskins.css";
    private static final String TITLED_SKIN_STYLESHEET = "titledskins.css";
    private static final String FONT_AWESOME = "fontawesome.ttf";

    @Override
    public void start(final Stage stage) throws Exception {

        final URL location = getClass().getClassLoader().getResource("GraphEditorDemo.fxml");
        final FXMLLoader loader = new FXMLLoader();
        final Parent root = loader.load(location.openStream());

        final Scene scene = new Scene(root, 830, 630);

        scene.getStylesheets().add(getClass().getClassLoader().getResource(DEMO_STYLESHEET).toExternalForm());
//        scene.getStylesheets().add(getClass().getClassLoader().getResource(TREE_SKIN_STYLESHEET).toExternalForm());
        scene.getStylesheets().add(getClass().getClassLoader().getResource(TITLED_SKIN_STYLESHEET).toExternalForm());
        Font.loadFont(getClass().getClassLoader().getResource(FONT_AWESOME).toExternalForm(), 12);

        stage.setScene(scene);
        stage.setTitle(APPLICATION_TITLE);

        stage.show();
        
        final GraphEditorDemoController controller = loader.getController();
        controller.panToCenter();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
