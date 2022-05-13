package bgs.formalspecificationide.Persistence.Repositories;

import bgs.formalspecificationide.Persistence.PersistenceModule;
import com.google.inject.PrivateModule;
import com.google.inject.Scopes;

public class RepositoriesModule extends PrivateModule {

    @Override
    protected void configure() {

        // Repositories
        bind(IProjectRepository.class).to(ProjectRepository.class).in(Scopes.SINGLETON);
        bind(IImageRepository.class).to(ImageRepository.class).in(Scopes.SINGLETON);

        // Expose
        expose(IProjectRepository.class);
        expose(IImageRepository.class);

        // Submodules
        install(new PersistenceModule());
    }

}
