module bgs.formalspecificationide {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.guice;
    requires org.json;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires java.xml;

    opens bgs.formalspecificationide to javafx.fxml;
    exports bgs.formalspecificationide;
    exports bgs.formalspecificationide.Services;
    exports bgs.formalspecificationide.Exceptions;
    exports bgs.formalspecificationide.Utilities;
}