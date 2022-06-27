package bgs.formalspecificationide.tutorial3;/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */

import java.net.URL;

import bgs.formalspecificationide.GraphEditor.api.src.main.java.io.github.eckig.grapheditor.*;
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
    private static final String FONT_AWESOME = "fontawesome.ttf";

    @Override
    public void start(final Stage stage) throws Exception {

        final URL location = getClass().getClassLoader().getResource("GraphEditorDemo.fxml");
        System.out.println(location);
        final FXMLLoader loader = new FXMLLoader();
        final Parent root = loader.load(location.openStream());

        final Scene scene = new Scene(root, 830, 630);

        scene.getStylesheets().add(getClass().getClassLoader().getResource(DEMO_STYLESHEET).toExternalForm());
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
