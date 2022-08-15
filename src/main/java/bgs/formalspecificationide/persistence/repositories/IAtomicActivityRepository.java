package bgs.formalspecificationide.persistence.repositories;

import bgs.formalspecificationide.model.AtomicActivityCollection;
import bgs.formalspecificationide.model.AtomicActivity;
import bgs.formalspecificationide.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAtomicActivityRepository extends IAggregateRepository<AtomicActivityCollection> {

    void saveByProject(Project project);

    void saveByProjectId(UUID projectId);

    List<AtomicActivity> getAllAtomicActivitiesInProject(Project project);

    List<AtomicActivity> getAllAtomicActivitiesInProject(UUID projectId);

    Optional<AtomicActivity> getAtomicActivityById(Project project, UUID atomicActivityId);

    Optional<AtomicActivity> getAtomicActivityById(UUID projectId, UUID atomicActivityId);

    void removeAtomicActivity(Project project, AtomicActivity atomicActivity);

    void removeAtomicActivity(UUID projectId, AtomicActivity atomicActivity);
}
