package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.Image;
import bgs.formalspecificationide.Model.ModelBase;
import bgs.formalspecificationide.Model.Project;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public interface IModelFactory {
    Project createProject(@NotNull String name);

    Image createImage(UUID id, File file);

    void registerInModelTracker(@NotNull ModelBase item);
}
