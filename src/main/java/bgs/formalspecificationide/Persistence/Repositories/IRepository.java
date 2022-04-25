package bgs.formalspecificationide.Persistence.Repositories;

import bgs.formalspecificationide.Utilities.IAggregateRoot;

import java.util.List;
import java.util.function.Predicate;

public interface IRepository<T extends IAggregateRoot<?>> {

    void add(T item);

    List<T> getAll();

    List<T> get(Predicate<T> predicate);

    void remove(T item);

    void saveAll();

    void save(T item);

}
