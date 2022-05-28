package bgs.formalspecificationide.Persistence.Repositories;

import bgs.formalspecificationide.Utilities.IAggregateRoot;

import java.util.Optional;
import java.util.UUID;

public interface IAggregateRepository<T extends IAggregateRoot<?>> extends IRepository<T> {

    Optional<T> getById(UUID id);

}
