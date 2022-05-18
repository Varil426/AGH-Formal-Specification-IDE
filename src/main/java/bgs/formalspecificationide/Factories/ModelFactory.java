package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.*;
import bgs.formalspecificationide.Persistence.Repositories.IAtomicActivityRepository;
import bgs.formalspecificationide.Persistence.Repositories.IImageRepository;
import bgs.formalspecificationide.Persistence.Repositories.IProjectNameRepository;
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
    private final IProjectNameRepository projectNameRepository;
    private final IImageRepository imageRepository;
    private final IAtomicActivityRepository atomicActivityRepository;
    private final LoggerService loggerService;

    @Inject
    public ModelFactory(ModelTrackerService modelTrackerService, IProjectRepository projectRepository, IProjectNameRepository projectNameRepository, IImageRepository imageRepository, IAtomicActivityRepository atomicActivityRepository, LoggerService loggerService) {
        this.modelTrackerService = modelTrackerService;
        this.projectRepository = projectRepository;
        this.projectNameRepository = projectNameRepository;
        this.imageRepository = imageRepository;
        this.atomicActivityRepository = atomicActivityRepository;
        this.loggerService = loggerService;
    }

    @Override
    public Project createProject(@NotNull String name) {
        var project = new Project(UUID.randomUUID());
        project.setDirty();

        var projectName = new ProjectName(UUID.randomUUID(), project.getId(), name);
        registerInModelTracker(projectName);
        projectNameRepository.add(projectName);

        var atomicActivityCollection = createAtomicActivityCollection(project.getId());
        atomicActivityCollection.setDirty();
        project.setAtomicActivityCollectionId(atomicActivityCollection.getId());
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
    public AtomicActivity createAtomicActivity(Project project, String atomicActivity) {
        //noinspection OptionalGetWithoutIsPresent
        return createAtomicActivity(atomicActivityRepository.getById(project.getAtomicActivityCollectionId()).get(), atomicActivity);
    }

    @Override
    public AtomicActivity createAtomicActivity(AtomicActivityCollection atomicActivityCollection, String atomicActivity) {
        var newAtomicActivity = new AtomicActivity(UUID.randomUUID(), atomicActivity);
        registerInModelTracker(newAtomicActivity);
        atomicActivityCollection.addChild(newAtomicActivity);
        return newAtomicActivity;
    }

    @Override
    public UseCaseDiagram createUseCaseDiagram(Project parent, UUID id, UUID imageID){
        UseCaseDiagram useCaseDiagram = new UseCaseDiagram(id, imageID);
        parent.addChild(useCaseDiagram);

        registerInModelTracker(useCaseDiagram);
        return useCaseDiagram;
    }

    @Override
    public UseCase createUseCase(UseCaseDiagram parent, UUID id, String name){
        UseCase useCase = new UseCase(id, name);
        parent.addChild(useCase);

        registerInModelTracker(useCase);
        return useCase;
    }

    @Override
    public Scenario createScenario(UseCase parent, UUID id, boolean isMain){
        Scenario scenario = new Scenario(id, isMain);
        parent.addChild(scenario);

        registerInModelTracker(scenario);
        return scenario;
    }

    @Override
    public ActivityDiagram createActivityDiagram(Scenario parent, UUID id){
        ActivityDiagram activityDiagram = new ActivityDiagram(id);
        parent.addChild(activityDiagram);

        registerInModelTracker(activityDiagram);
        return activityDiagram;
    }

    @Override
    public Pattern createPattern(ActivityDiagram parent, UUID id, String name){
        Pattern pattern = new Pattern(id, name);
        parent.addChild(parent);

        registerInModelTracker(pattern);
        return pattern;
    }

    @Override
    public void registerInModelTracker(@NotNull ModelBase item) {
        item.subscribe(modelTrackerService);
        loggerService.logDebug("Registered %s in ModelTrackerService.".formatted(item.toString()));
    }

    private AtomicActivityCollection createAtomicActivityCollection(UUID projectId) {
        var atomicActivityCollection = new AtomicActivityCollection(UUID.randomUUID(), projectId);
        registerInModelTracker(atomicActivityCollection);
        atomicActivityRepository.add(atomicActivityCollection);
        return atomicActivityCollection;
    }

}
