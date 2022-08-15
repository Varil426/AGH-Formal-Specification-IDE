package bgs.formalspecificationide.events.modelEvents;

import bgs.formalspecificationide.model.ModelAggregate;
import bgs.formalspecificationide.model.ModelBase;

public class ChildAddedEvent extends ModelAggregateModifiedEvent{
    public ChildAddedEvent(ModelAggregate publisher, ModelBase item) {
        super(publisher, item, ModificationEnum.ADDED);
    }
}
