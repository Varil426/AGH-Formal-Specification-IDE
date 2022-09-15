package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.ui.editors.actionEditor.ActionEditorController;
import bgs.formalspecificationide.ui.editors.actionEditor.controls.ActionController;
import bgs.formalspecificationide.ui.editors.imageViewer.ImageViewerController;
import bgs.formalspecificationide.ui.editors.scenarioSelector.ScenarioSelectorEditorController;
import bgs.formalspecificationide.ui.editors.useCaseSelector.UseCaseSelectorEditorController;
import bgs.formalspecificationide.ui.editors.useCaseSelector.controls.UseCaseController;
import com.google.inject.PrivateModule;
import com.google.inject.Scopes;

public class UIModule extends PrivateModule {

    @Override
    protected void configure() {
        
        // Factories
        bind(IUIElementFactory.class).to(UIElementsFactory.class).in(Scopes.SINGLETON);
        
        // Controllers
        bind(MainWindowController.class).in(Scopes.SINGLETON);
        bind(UseCaseSelectorEditorController.class);
        bind(UseCaseController.class);
        bind(ScenarioSelectorEditorController.class);
        bind(ActionEditorController.class);
        bind(ActionController.class);
        bind(ImageViewerController.class);

        // Expose
        expose(IUIElementFactory.class);
        expose(MainWindowController.class);
        expose(UseCaseSelectorEditorController.class);
        expose(UseCaseController.class);
        expose(ScenarioSelectorEditorController.class);
        expose(ActionEditorController.class);
        expose(ActionController.class);
        expose(ImageViewerController.class);

        // Submodules
    }

}
