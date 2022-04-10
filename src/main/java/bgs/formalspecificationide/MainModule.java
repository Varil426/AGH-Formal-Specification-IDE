package bgs.formalspecificationide;

import bgs.formalspecificationide.Services.EventAggregatorService;
import bgs.formalspecificationide.Services.IResourceService;
import bgs.formalspecificationide.Services.LoggerService;
import bgs.formalspecificationide.Services.ResourceService;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();

        // Services
        bind(IResourceService.class).to(ResourceService.class).in(Scopes.SINGLETON);
        bind(EventAggregatorService.class).in(Scopes.SINGLETON);
        bind(LoggerService.class).in(Scopes.SINGLETON);

        // Factories

        // Repositories

    }
}
