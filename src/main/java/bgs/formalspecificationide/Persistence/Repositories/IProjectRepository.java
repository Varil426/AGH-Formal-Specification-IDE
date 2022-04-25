package bgs.formalspecificationide.Persistence.Repositories;

import bgs.formalspecificationide.Model.Project;

import java.util.List;
import java.util.function.Predicate;

public interface IProjectRepository extends IRepository<Project> {
    List<String> getProjectNames();

    @Override
    void add(Project item);

    @Override
    List<Project> getAll();

    @Override
    List<Project> get(Predicate<Project> predicate);

    Project getByName(String name);

    @Override
    void remove(Project item);

    @Override
    void saveAll();

    @Override
    void save(Project item);
}
