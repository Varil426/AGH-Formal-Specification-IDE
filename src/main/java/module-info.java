module bgs.formalspecificationide {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.guice;


    opens bgs.formalspecificationide to javafx.fxml;
    exports bgs.formalspecificationide;
}