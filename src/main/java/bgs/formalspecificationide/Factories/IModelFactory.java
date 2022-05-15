package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public interface IModelFactory {
    Project createProject(@NotNull String name);

    Image createImage(UUID id, File file);

    UseCaseDiagram createUseCaseDiagram(Project parent, UUID id, UUID imageID);

    UseCase createUseCase(UseCaseDiagram parent, UUID id, String name);

    Scenario createScenario(UseCase parent, UUID id, boolean isMain);

    ActivityDiagram createActivityDiagram(Scenario parent, UUID id);

    Pattern createPattern(ActivityDiagram parent, UUID id, String name);

    void registerInModelTracker(@NotNull ModelBase item);
}
