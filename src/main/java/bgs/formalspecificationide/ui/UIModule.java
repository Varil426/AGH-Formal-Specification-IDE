package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.ui.editors.actionEditor.ActionEditorController;
import bgs.formalspecificationide.ui.editors.scenarioSelector.ScenarioSelectorEditorController;
import bgs.formalspecificationide.ui.editors.useCaseSelector.UseCaseSelectorEditorController;
import com.google.inject.PrivateModule;
import com.google.inject.Scopes;

public class UIModule extends PrivateModule {

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
        
        // Factories
        bind(IUIElementFactory.class).to(UIElementsFactory.class).in(Scopes.SINGLETON);
        
        // Controllers
        bind(MainWindowController.class).in(Scopes.SINGLETON);
        bind(UseCaseSelectorEditorController.class);
        bind(ScenarioSelectorEditorController.class);
        bind(ActionEditorController.class);

        // Expose
        expose(IUIElementFactory.class);
        expose(MainWindowController.class);
        expose(UseCaseSelectorEditorController.class);
        expose(ScenarioSelectorEditorController.class);
        expose(ActionEditorController.class);

        // Submodules
    }

}
