package bgs.formalspecificationide.Events;

import bgs.formalspecificationide.Events.Event;
import bgs.formalspecificationide.Model.ModelBase;

public class IsDirtyEvent extends Event<ModelBase> {

    public IsDirtyEvent(ModelBase publisher) {
        super(publisher);
    }
}
