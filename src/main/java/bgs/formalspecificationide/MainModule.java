package bgs.formalspecificationide;

import bgs.formalspecificationide.Factories.IModelFactory;
import bgs.formalspecificationide.Factories.ModelFactory;
import bgs.formalspecificationide.Persistence.Repositories.RepositoriesModule;
import bgs.formalspecificationide.Services.*;
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
    }
}
