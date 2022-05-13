package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.Image;
import bgs.formalspecificationide.Model.ModelBase;
import bgs.formalspecificationide.Model.Project;
import bgs.formalspecificationide.Persistence.Repositories.IImageRepository;
import bgs.formalspecificationide.Persistence.Repositories.IProjectRepository;
import bgs.formalspecificationide.Services.LoggerService;
import bgs.formalspecificationide.Services.ModelTrackerService;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public class ModelFactory implements IModelFactory {

    private final ModelTrackerService modelTrackerService;
    private final IProjectRepository projectRepository;
    private final IImageRepository imageRepository;
    private final LoggerService loggerService;

    @Inject
    public ModelFactory(ModelTrackerService modelTrackerService, IProjectRepository projectRepository, IImageRepository imageRepository, LoggerService loggerService) {
        this.modelTrackerService = modelTrackerService;
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
        this.loggerService = loggerService;
    }

    @Override
    public Project createProject(@NotNull String name) {
        var project = new Project(UUID.randomUUID());
        project.setName(name);
        registerInModelTracker(project);
        projectRepository.add(project);
        return project;
    }

    @Override
    public Image createImage(UUID id, File file) {
        var img = new Image(id, file);
        imageRepository.add(img);
        return img;
    }

    @Override
    public void registerInModelTracker(@NotNull ModelBase item) {
        item.subscribe(modelTrackerService);
        loggerService.logDebug("Registered %s in ModelTrackerService.".formatted(item.toString()));
    }

}
