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

    opens bgs.formalspecificationide.Services to com.google.guice;
    opens bgs.formalspecificationide.Persistence to com.google.guice;
    opens bgs.formalspecificationide.Persistence.Repositories to com.google.guice;
    opens bgs.formalspecificationide.Factories to com.google.guice;
    opens bgs.formalspecificationide.Model to com.fasterxml.jackson.databind;
    opens bgs.formalspecificationide to com.google.guice, javafx.fxml;
    opens bgs.formalspecificationide.tutorial1 to com.google.guice, javafx.fxml, javafx.graphics;
    opens bgs.formalspecificationide.tutorial3 to com.google.guice, javafx.fxml, javafx.graphics;

    exports bgs.formalspecificationide;
    opens bgs.formalspecificationide.tutorial3.ownImpl to com.google.guice, javafx.fxml, javafx.graphics;
}