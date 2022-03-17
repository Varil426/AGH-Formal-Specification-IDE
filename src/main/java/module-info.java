module bgs.formalspecificationide {
    requires javafx.controls;
    requires javafx.fxml;


    opens bgs.formalspecificationide to javafx.fxml;
    exports bgs.formalspecificationide;
}