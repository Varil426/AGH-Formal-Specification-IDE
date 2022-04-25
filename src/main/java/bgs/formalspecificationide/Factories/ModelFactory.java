package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.Project;
import bgs.formalspecificationide.Persistence.Repositories.IProjectRepository;
import bgs.formalspecificationide.Services.LoggerService;
import bgs.formalspecificationide.Services.ModelTrackerService;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ModelFactory implements IModelFactory {

    private final ModelTrackerService modelTrackerService;
    private final IProjectRepository projectRepository;
    private final LoggerService loggerService;

    @Inject
    public ModelFactory(ModelTrackerService modelTrackerService, IProjectRepository projectRepository, LoggerService loggerService) {
        this.modelTrackerService = modelTrackerService;
        this.projectRepository = projectRepository;
        this.loggerService = loggerService;
    }

    @Override
    public Project createProject(@NotNull String name) {
        var project = new Project();
        project.setName(name);
        project.setId(UUID.randomUUID());
        registerProject(project);
        projectRepository.add(project);
        return project;
    }

    @Override
    public void registerProject(@NotNull Project project) {
        project.subscribe(modelTrackerService);

        loggerService.logDebug("Registered the project %s.".formatted(project.getName()));
    }

}
