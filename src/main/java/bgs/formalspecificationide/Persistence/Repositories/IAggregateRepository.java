package bgs.formalspecificationide.Persistence.Repositories;

import bgs.formalspecificationide.Utilities.IAggregateRoot;

public interface IAggregateRepository<T extends IAggregateRoot<?>> extends IRepository<T> { }
