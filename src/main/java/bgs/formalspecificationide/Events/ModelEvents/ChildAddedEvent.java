package bgs.formalspecificationide.Events.ModelEvents;

import bgs.formalspecificationide.Model.ModelBase;

public class ChildAddedEvent extends ModelAggregateModifiedEvent{
    public ChildAddedEvent(ModelBase publisher, ModelBase item) {
        super(publisher, item, ModificationEnum.ADDED);
    }
}
