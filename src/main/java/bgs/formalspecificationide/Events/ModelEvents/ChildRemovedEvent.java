package bgs.formalspecificationide.Events.ModelEvents;

import bgs.formalspecificationide.Model.ModelBase;

public class ChildRemovedEvent extends ModelAggregateModifiedEvent{
    public ChildRemovedEvent(ModelBase publisher, ModelBase item) {
        super(publisher, item, ModificationEnum.REMOVED);
    }
}
