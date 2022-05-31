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

    opens bgs.formalspecificationide.Services to com.google.guice;
    opens bgs.formalspecificationide.Persistence to com.google.guice;
    opens bgs.formalspecificationide.Persistence.Repositories to com.google.guice;
    opens bgs.formalspecificationide.Factories to com.google.guice;
    opens bgs.formalspecificationide.Model to com.fasterxml.jackson.databind;
    opens bgs.formalspecificationide to com.google.guice, javafx.fxml;

    exports bgs.formalspecificationide;
}