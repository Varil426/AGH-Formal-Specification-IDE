package bgs.formalspecificationide.events.modelEvents;

import bgs.formalspecificationide.events.CollectionModifiedEvent;
import bgs.formalspecificationide.model.ModelAggregate;
import bgs.formalspecificationide.model.ModelBase;

public abstract class ModelAggregateModifiedEvent extends CollectionModifiedEvent<ModelAggregate, ModelBase> {
    public ModelAggregateModifiedEvent(ModelAggregate publisher, ModelBase item, ModificationEnum modification) {
        super(publisher, item, modification, "children");
    }
}
