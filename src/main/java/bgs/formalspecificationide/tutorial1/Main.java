package bgs.formalspecificationide.tutorial1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Testowy tytuł");
//        stage.setWidth(1200);
//        stage.setHeight(800);
        stage.setResizable(false);
        stage.setX(50);
        stage.setY(50);

//        Text text = new Text();
//        text.setText("TEXTTEXTTEXTTEXT");
//        text.setX(50);
//        text.setY(50);
//        text.setFont(Font.font("Verdana", 25));
//        text.setFill(Color.GREEN);
//        root.getChildren().add(text);
//
//        Line line = new Line();
//        line.setStartX(100);
//        line.setStartY(100);
//        line.setEndX(500);
//        line.setEndY(100);
//        root.getChildren().add(line);
//
//        Rectangle rectangle = new Rectangle();
//        rectangle.setX(100);
//        rectangle.setY(150);
//        rectangle.setWidth(100);
//        rectangle.setHeight(100);
//        rectangle.setFill(Color.BLUE);
//        root.getChildren().add(rectangle);
//
//        Image image = new Image("qwert.png");
//        ImageView imageView = new ImageView(image);
//        imageView.setX(400);
//        imageView.setY(400);


        stage.setScene(scene);
        stage.show();
    }
}
