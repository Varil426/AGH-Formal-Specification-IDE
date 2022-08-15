package bgs.formalspecificationide.persistence.repositories;

import bgs.formalspecificationide.persistence.PersistenceModule;
import com.google.inject.PrivateModule;
import com.google.inject.Scopes;

public class RepositoriesModule extends PrivateModule {

    @Override
    protected void configure() {

        // Repositories
        bind(IProjectRepository.class).to(ProjectRepository.class).in(Scopes.SINGLETON);
        bind(IImageRepository.class).to(ImageRepository.class).in(Scopes.SINGLETON);
        bind(IAtomicActivityRepository.class).to(AtomicActivityRepository.class).in(Scopes.SINGLETON);
        bind(IProjectNameRepository.class).to(ProjectNameRepository.class).in(Scopes.SINGLETON);
        bind(IPatternTemplateRepository.class).to(PatternTemplateRepository.class).in(Scopes.SINGLETON);

        // Expose
        expose(IProjectRepository.class);
        expose(IImageRepository.class);
        expose(IAtomicActivityRepository.class);
        expose(IProjectNameRepository.class);
        expose(IPatternTemplateRepository.class);

        // Submodules
        install(new PersistenceModule());
    }

}
