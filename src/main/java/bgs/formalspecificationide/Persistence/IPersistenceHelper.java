package bgs.formalspecificationide.Persistence;

import bgs.formalspecificationide.Model.Project;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface IPersistenceHelper {

    static String getFileExtension(File file) {
        var fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    static String getFileNameWithoutExtension(File file) {
        var fileName = file.getName();
        var dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    List<File> getAllProjectFiles();

    void saveProjectFile(Project project);

    boolean saveFile(Path path, Object content);

    void removeFile(File file);

    <T> T loadFile(File file, Class<T> type);

    Path generatePathToJson(String directory, String name);

    void setupDirectories();
}