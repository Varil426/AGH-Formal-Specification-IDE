package bgs.formalspecificationide.events.modelEvents;

import bgs.formalspecificationide.model.ModelAggregate;
import bgs.formalspecificationide.model.ModelBase;

public class ChildRemovedEvent extends ModelAggregateModifiedEvent{
    public ChildRemovedEvent(ModelAggregate publisher, ModelBase item) {
        super(publisher, item, ModificationEnum.REMOVED);
    }
}
