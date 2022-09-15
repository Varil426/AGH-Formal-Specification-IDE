package bgs.formalspecificationide;

import bgs.formalspecificationide.factories.IModelFactory;
import bgs.formalspecificationide.factories.ModelFactory;
import bgs.formalspecificationide.persistence.repositories.RepositoriesModule;
import bgs.formalspecificationide.services.*;
import bgs.formalspecificationide.ui.UIModule;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();

        // Services
        bind(IResourceService.class).to(ResourceService.class).in(Scopes.SINGLETON);
        bind(EventAggregatorService.class).in(Scopes.SINGLETON);
        bind(ModelTrackerService.class).in(Scopes.SINGLETON);
        bind(LoggerService.class).in(Scopes.SINGLETON);
        bind(XmlParserService.class).in(Scopes.SINGLETON);

        // Factories
        bind(IModelFactory.class).to(ModelFactory.class).in(Scopes.SINGLETON);

        // Submodules
        install(new RepositoriesModule());
        install(new UIModule());
    }
}
