package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.MainModule;
import bgs.formalspecificationide.ui.editors.useCaseSelector.UseCaseSelectorEditor;
import com.google.inject.PrivateModule;
import com.google.inject.Scopes;

;

public class UiModule extends PrivateModule {

    @Override
    protected void configure() {

        // UI
        bind(MainWindow.class).in(Scopes.SINGLETON);
        bind(UseCaseSelectorEditor.class);

        // Expose
        expose(MainWindow.class);
        expose(UseCaseSelectorEditor.class);

        // Submodules
    }

}
