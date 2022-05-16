package bgs.formalspecificationide.Persistence.Repositories;

import bgs.formalspecificationide.Model.Project;
import bgs.formalspecificationide.Persistence.IPersistenceHelper;
import bgs.formalspecificationide.Services.LoggerService;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

class ProjectRepository implements IProjectRepository {

    private final IPersistenceHelper persistenceHelper;
    private final LoggerService loggerService;

    private final Set<Project> projects;

    @Inject
    public ProjectRepository(IPersistenceHelper persistenceHelper, LoggerService loggerService) {
        this.persistenceHelper = persistenceHelper;
        this.loggerService = loggerService;
        projects = new HashSet<>();
    }

    @Override
    public List<String> getProjectNames() {
        return persistenceHelper.getAllProjectFiles().stream()
                .map(file -> file.getName().substring(0, file.getName().lastIndexOf('.'))).toList();
    }

    @Override
    public void add(@NotNull Project item) {
        projects.add(item);
    }

    @Override
    public List<Project> getAll() {
        for (var file : persistenceHelper.getAllProjectFiles()) {
            loadProject(file);
        }

        return projects.stream().toList();
    }

    @Override
    public List<Project> get(Predicate<Project> predicate) {
        return getAll().stream().filter(predicate).toList();
    }

    /**
     * Gets a project by name.
     * @param name The project name.
     * @return The project or null if not found.
     */
    @Override
    public Project getByName(String name) {
        var projectFiles = persistenceHelper.getAllProjectFiles();
        var foundProjectFile = projectFiles.stream().filter(x -> x.getName().equals(name)).findFirst();
        return foundProjectFile.map(this::loadProject).orElse(null);
    }

    @Override
    public void remove(@NotNull Project item) {
        var name = item.getName();
        var file = persistenceHelper.getAllProjectFiles().stream().filter(x -> name.equals(IPersistenceHelper.getFileNameWithoutExtension(x))).findFirst();
        file.ifPresent(persistenceHelper::removeFile);
        projects.remove(item);
    }

    @Override
    public void saveAll() {
        for (var project : projects) {
            save(project);
        }
    }

    @Override
    public void save(@NotNull Project item) {
        if (item.isDirty()) {
            persistenceHelper.saveProjectFile(item);
            item.clearIsDirty();
        }
    }

    private Project loadProject(File file) {
        var projectName = IPersistenceHelper.getFileNameWithoutExtension(file);
        if (projects.stream().anyMatch(x -> x.getName().equals(projectName))) {
            //noinspection OptionalGetWithoutIsPresent
            return projects.stream().filter(x -> x.getName().equals(projectName)).findFirst().get();
        }
        var newProject = persistenceHelper.loadFile(file, Project.class);

        loggerService.logInfo("Successfully loaded a project.");

        projects.add(newProject);
        return newProject;
    }

    @Override
    public Optional<Project> getById(UUID id) {
        return getAll().stream().filter(x -> x.getId() == id).findFirst();
    }
}
