package bgs.formalspecificationide.events;

import bgs.formalspecificationide.model.ModelBase;

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