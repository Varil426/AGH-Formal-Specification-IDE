package bgs.formalspecificationide.ui.editors.scenarioSelector.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Scenario extends AnchorPane {

    private final bgs.formalspecificationide.model.Scenario scenario;

    public Scenario(bgs.formalspecificationide.model.Scenario scenario) {
        super();
        this.scenario = scenario;

        var fxmlLoader = new FXMLLoader(getClass().getResource(
                "Scenario.fxml"));
        fxmlLoader.setController(this);

        try {
            var result = fxmlLoader.<AnchorPane>load();
            this.getChildren().add(result);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}
