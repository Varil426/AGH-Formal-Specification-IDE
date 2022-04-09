module bgs.formalspecificationide {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.guice;
    requires org.json;


    opens bgs.formalspecificationide to javafx.fxml;
    exports bgs.formalspecificationide;
    exports bgs.formalspecificationide.Services;
    exports bgs.formalspecificationide.Exceptions;
}