package bgs.formalspecificationide.persistence.repositories;

import bgs.formalspecificationide.model.Project;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IProjectRepository extends IAggregateRepository<Project> {
    List<String> getProjectNames();

    @Override
    void add(@NotNull Project item);

    @Override
    List<Project> getAll();

    @Override
    List<Project> get(Predicate<Project> predicate);

    Optional<Project> getByName(String name);

    @Override
    void remove(@NotNull Project item);

    @Override
    void saveAll();

    @Override
    void save(@NotNull Project item);
}
