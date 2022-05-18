package bgs.formalspecificationide.Events.ModelEvents;

import bgs.formalspecificationide.Events.CollectionModifiedEvent;
import bgs.formalspecificationide.Model.ModelBase;

public abstract class ModelAggregateModifiedEvent extends CollectionModifiedEvent<ModelBase> {
    public ModelAggregateModifiedEvent(ModelBase publisher, ModelBase item, ModificationEnum modification) {
        super(publisher, item, modification);
    }
}
