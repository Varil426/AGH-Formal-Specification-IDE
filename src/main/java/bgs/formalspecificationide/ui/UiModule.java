package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.ui.editors.actionEditor.ActionEditor;
import bgs.formalspecificationide.ui.editors.scenarioSelector.ScenarioSelectorEditor;
import bgs.formalspecificationide.ui.editors.useCaseSelector.UseCaseSelectorEditor;
import com.google.inject.PrivateModule;
import com.google.inject.Scopes;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class UiModule extends PrivateModule {

    @Override
    protected void configure() {

        // JavaFX
//        bind(MenuBar.class);
//        expose(MenuBar.class);
//        bind(Menu.class);
//        expose(Menu.class);
//        bind(MenuItem.class);
//        expose(MenuItem.class);
//        bind(AnchorPane.class);
//        expose(AnchorPane.class);
        
        // UI
        bind(MainWindow.class).in(Scopes.SINGLETON);
        bind(UseCaseSelectorEditor.class);
        bind(ScenarioSelectorEditor.class);
        bind(ActionEditor.class);

        // Expose
        expose(MainWindow.class);
        expose(UseCaseSelectorEditor.class);
        expose(ScenarioSelectorEditor.class);
        expose(ActionEditor.class);

        // Submodules
    }

}
