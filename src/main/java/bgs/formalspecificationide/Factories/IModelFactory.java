package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public interface IModelFactory {
    Project createProject(@NotNull String name);

    Image createImage(UUID id, File file);

    AtomicActivity createAtomicActivity(Project project, String atomicActivity);

    AtomicActivity createAtomicActivity(AtomicActivityCollection atomicActivityCollection, String atomicActivity);

    void registerInModelTracker(@NotNull ModelBase item);
}
