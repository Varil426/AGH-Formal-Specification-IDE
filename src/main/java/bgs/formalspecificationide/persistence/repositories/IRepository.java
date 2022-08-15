package bgs.formalspecificationide.persistence.repositories;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public interface IRepository<T> {
    void add(@NotNull T item);

    List<T> getAll();

    List<T> get(Predicate<T> predicate);

    void remove(@NotNull T item);

    void saveAll();

    void save(@NotNull T item);
}
