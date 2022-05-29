package bgs.formalspecificationide.Events.ModelEvents;

import bgs.formalspecificationide.Model.ModelAggregate;
import bgs.formalspecificationide.Model.ModelBase;

public class ChildAddedEvent extends ModelAggregateModifiedEvent{
    public ChildAddedEvent(ModelAggregate publisher, ModelBase item) {
        super(publisher, item, ModificationEnum.ADDED);
    }
}
