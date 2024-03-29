module bgs.formalspecificationide {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.guice;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires java.xml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.jetbrains.annotations;
    requires io.github.eckig.grapheditor.api;
    requires io.github.eckig.grapheditor.core;
    requires org.eclipse.emf.edit;
    requires org.eclipse.emf.ecore.xmi;
    requires org.eclipse.core.resources;
    requires org.eclipse.equinox.common;
    requires jface;
    requires json.simple;

    opens bgs.formalspecificationide.services to com.google.guice;
    opens bgs.formalspecificationide.persistence to com.google.guice;
    opens bgs.formalspecificationide.persistence.repositories to com.google.guice;
    opens bgs.formalspecificationide.factories to com.google.guice;
    opens bgs.formalspecificationide.model to com.fasterxml.jackson.databind;
    opens bgs.formalspecificationide to com.google.guice, javafx.fxml;
    opens bgs.formalspecificationide.ui.editors.activityDiagramEditor to com.google.guice, javafx.fxml, javafx.graphics;
    opens bgs.formalspecificationide.ui.editors.activityDiagramEditor.resultsEditor to com.google.guice, javafx.fxml, javafx.graphics;
    opens bgs.formalspecificationide.ui.editors.activityDiagramEditor.ownImpl to com.google.guice, javafx.fxml, javafx.graphics;
    opens bgs.formalspecificationide.ui.editors.activityDiagramEditor.customskin to com.google.guice, javafx.fxml, javafx.graphics;
    opens bgs.formalspecificationide.ui.editors.activityDiagramEditor.managers to com.google.guice, javafx.fxml, javafx.graphics;
    opens bgs.formalspecificationide.ui.editors.activityDiagramEditor.utils to com.google.guice, javafx.fxml, javafx.graphics;

    opens bgs.formalspecificationide.ui to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.useCaseSelector to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.useCaseSelector.controls to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.scenarioSelector to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.scenarioSelector.controls to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.actionEditor to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.actionEditor.controls to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.imageViewer to javafx.fxml, com.google.guice;

    exports bgs.formalspecificationide;
}