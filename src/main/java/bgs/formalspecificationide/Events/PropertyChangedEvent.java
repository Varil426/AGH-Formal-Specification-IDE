package bgs.formalspecificationide.Events;

import bgs.formalspecificationide.Events.Event;
import bgs.formalspecificationide.Model.ModelBase;

public class PropertyChangedEvent extends Event<ModelBase> {

    private final String propertyName;


    public PropertyChangedEvent(ModelBase publisher, String propertyName) {
        super(publisher);
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}