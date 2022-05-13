package bgs.formalspecificationide.Persistence;

import bgs.formalspecificationide.Factories.IModelFactory;
import bgs.formalspecificationide.Model.ModelBase;
import bgs.formalspecificationide.Model.Project;
import bgs.formalspecificationide.Services.LoggerService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.google.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

class PersistenceHelper implements IPersistenceHelper {

    private final Path persistenceDirectory = Paths.get("persistence");

    private final Path projectDirectory = persistenceDirectory.resolve("projects");

    private final Path imagesDirectory = persistenceDirectory.resolve("images");

    private final LoggerService loggerService;

    private final ObjectMapper objectMapper;

    @Inject
    public PersistenceHelper(LoggerService loggerService, IModelFactory modelFactory) {
        this.loggerService = loggerService;

        var polymorphicTypeValidator = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(ModelBase.class)
                .allowIfBaseType(List.class)
                .build();


        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(polymorphicTypeValidator, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        objectMapper.registerModule(new JsonModule(modelFactory));

        setupDirectories();
    }

    @Override
    public List<File> getAllProjectFiles() {
        return Stream.of(Objects.requireNonNull(new File(projectDirectory.toAbsolutePath().toString()).listFiles()))
                .filter(file -> file.isFile() && IPersistenceHelper.getFileExtension(file).equals("json")).toList();
    }

    @Override
    public List<File> getAllImageFiles() {
        return Stream.of(Objects.requireNonNull(new File(imagesDirectory.toAbsolutePath().toString()).listFiles()))
                .filter(file -> file.isFile() && IPersistenceHelper.getFileExtension(file).equals("png") || IPersistenceHelper.getFileExtension(file).equals("jpg")).toList();
    }

    @Override
    public void saveProjectFile(Project project) {
        if (saveFile(generatePathToJson(projectDirectory.toAbsolutePath().toString(), project.getName()), project))
            loggerService.logInfo("Saved project %s".formatted(project.getName()));
    }

    @Override
    public Optional<File> saveImage(File imageFile, UUID id) {
        File newFile;
        try {
            var newPath = Files.copy(imageFile.toPath(), Path.of(imagesDirectory.toString(), "%s.%s".formatted(id.toString(), IPersistenceHelper.getFileExtension(imageFile))));
            newFile = newPath.toFile();
        } catch (IOException e) {
            loggerService.logError("Couldn't copy image.");
            return Optional.empty();
        }
        loggerService.logInfo("Successfully copied image.");
        return Optional.of(newFile);
    }

    @Override
    public boolean saveFile(Path path, Object content) {
        try {
            objectMapper.writeValue(path.toAbsolutePath().toFile(), content);//new JsonWrapper<>(content));
            return true;
        } catch (IOException e) {
            loggerService.logError("Couldn't save to file.");
            return false;
        }
    }

    @Override
    public void removeFile(File file) {
        // TODO Add check for testing whether we are trying to delete file from our persistence and not some random file from drive.

        try {
            Files.delete(file.toPath());
            loggerService.logInfo("Deleted file %s".formatted(file.getName()));
        } catch (IOException e) {
            loggerService.logError("Couldn't delete a file.");
        }
    }

    /**
     * Reads file and converts it to Java Object.
     * @param file File.
     * @param <T> Type to be returned.
     * @return Returns the new Java Object or null in case of failure.
     */
    @Override
    public <T> T loadFile(File file, Class<T> type) {
        try {
            return objectMapper.readValue(file, type);
        } catch (IOException e) {
            loggerService.logError("Couldn't read a file.");
            return null;
        }
    }

    @Override
    public Path generatePathToJson(String directory, String name) {
        return Paths.get(String.format("%s/%s.json", directory, name));
    }

    /**
     * Checks and creates directories used by the persistence layer.
     */
    private void setupDirectories() {
        var paths = Arrays.asList(persistenceDirectory, projectDirectory, imagesDirectory);

        for (var path : paths) {
            if (!Files.isDirectory(path)) {
                try {
                    Files.createDirectories(path);
                } catch (IOException e) {
                    loggerService.logError("Couldn't create persistence directories.");
                }
            }
        }
    }
}
