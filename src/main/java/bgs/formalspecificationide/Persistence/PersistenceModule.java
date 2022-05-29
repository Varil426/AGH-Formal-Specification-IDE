package bgs.formalspecificationide.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {

        // Services
        bind(IPersistenceHelper.class).to(PersistenceHelper.class).in(Scopes.SINGLETON);
    }

}
