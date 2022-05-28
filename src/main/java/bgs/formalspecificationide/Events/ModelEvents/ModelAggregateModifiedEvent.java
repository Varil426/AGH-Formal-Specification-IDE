package bgs.formalspecificationide.Events.ModelEvents;

import bgs.formalspecificationide.Events.CollectionModifiedEvent;
import bgs.formalspecificationide.Model.ModelAggregate;
import bgs.formalspecificationide.Model.ModelBase;

public abstract class ModelAggregateModifiedEvent extends CollectionModifiedEvent<ModelAggregate, ModelBase> {
    public ModelAggregateModifiedEvent(ModelAggregate publisher, ModelBase item, ModificationEnum modification) {
        super(publisher, item, modification, "children");
    }
}
