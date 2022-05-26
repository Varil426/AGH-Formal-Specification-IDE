package bgs.formalspecificationide.Events.ModelEvents;

import bgs.formalspecificationide.Model.ModelAggregate;
import bgs.formalspecificationide.Model.ModelBase;

public class ChildRemovedEvent extends ModelAggregateModifiedEvent{
    public ChildRemovedEvent(ModelAggregate publisher, ModelBase item) {
        super(publisher, item, ModificationEnum.REMOVED);
    }
}
