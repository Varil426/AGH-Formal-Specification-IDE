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

    opens bgs.formalspecificationide.services to com.google.guice;
    opens bgs.formalspecificationide.persistence to com.google.guice;
    opens bgs.formalspecificationide.persistence.repositories to com.google.guice;
    opens bgs.formalspecificationide.factories to com.google.guice;
    opens bgs.formalspecificationide.model to com.fasterxml.jackson.databind;
    opens bgs.formalspecificationide to com.google.guice, javafx.fxml;

    opens bgs.formalspecificationide.ui to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.useCaseSelector to javafx.fxml, com.google.guice;
    opens bgs.formalspecificationide.ui.editors.useCaseSelector.controls to javafx.fxml, com.google.guice;

    exports bgs.formalspecificationide;
}