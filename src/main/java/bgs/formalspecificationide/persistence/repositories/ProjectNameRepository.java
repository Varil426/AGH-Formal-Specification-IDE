package bgs.formalspecificationide.persistence.repositories;

import bgs.formalspecificationide.model.ProjectName;
import bgs.formalspecificationide.model.ProjectNameList;
import bgs.formalspecificationide.persistence.IPersistenceHelper;
import bgs.formalspecificationide.services.LoggerService;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

class ProjectNameRepository implements IProjectNameRepository {

    private final IPersistenceHelper persistenceHelper;

    private final LoggerService loggerService;

    @Nullable
    private ProjectNameList projectNames;

    @Inject
    public ProjectNameRepository(IPersistenceHelper persistenceHelper, LoggerService loggerService) {
        this.persistenceHelper = persistenceHelper;
        this.loggerService = loggerService;
        projectNames = null;
    }

    @Override
    public void add(@NotNull ProjectName item) {
        if (projectNames == null)
            projectNames = loadProjectNameList();

        if (projectNames.getChildrenOfType(ProjectName.class).stream().anyMatch(x -> x.getId().equals(item.getId()) || x.getProjectId().equals(item.getProjectId())))
            return;
        projectNames.addChild(item);
    }

    @Override
    public List<ProjectName> getAll() {
        if (projectNames == null)
            projectNames = loadProjectNameList();
        return projectNames.getChildrenOfType(ProjectName.class).stream().toList();
    }

    @Override
    public List<ProjectName> get(Predicate<ProjectName> predicate) {
        return getAll().stream().filter(predicate).toList();
    }

    @Override
    public void remove(@NotNull ProjectName item) {
        if (projectNames == null)
            projectNames = loadProjectNameList();
        projectNames.removeChild(item);
    }

    @Override
    public void saveAll() {
        if (projectNames == null)
            projectNames = loadProjectNameList();
        if (projectNames.isDirty()) {
            persistenceHelper.saveProjectNames(projectNames);
            loggerService.logInfo("Saved Project Name List");
        }
    }

    @Override
    public void save(@NotNull ProjectName item) {
        saveAll();
    }

    private ProjectNameList loadProjectNameList() {
        var projectNameList = persistenceHelper.loadFile(persistenceHelper.getProjectNamesFile(), ProjectNameList.class);
        if (projectNameList == null)
            return new ProjectNameList(UUID.randomUUID());
        loggerService.logInfo("Successfully loaded Project Name List");
        return projectNameList;
    }
}
