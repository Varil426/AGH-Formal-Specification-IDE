package bgs.formalspecificationide.events;

import bgs.formalspecificationide.model.ModelBase;

public class IsDirtyEvent extends Event<ModelBase> {

    public IsDirtyEvent(ModelBase publisher) {
        super(publisher);
    }
}
