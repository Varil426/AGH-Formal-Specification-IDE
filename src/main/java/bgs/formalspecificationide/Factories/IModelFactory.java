package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.ModelBase;
import bgs.formalspecificationide.Model.Project;
import org.jetbrains.annotations.NotNull;

public interface IModelFactory {
    Project createProject(@NotNull String name);

    void registerInModelTracker(@NotNull ModelBase item);
}
