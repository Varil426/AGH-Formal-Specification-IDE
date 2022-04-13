package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Services.EventAggregatorService;
import bgs.formalspecificationide.Utilities.Event;

public abstract class ModelBase {

    public static class PropertyChangedEvent extends Event<ModelBase> {

        private final String propertyName;

        public PropertyChangedEvent(ModelBase publisher, String propertyName) {
            super(publisher);
            this.propertyName = propertyName;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }

    protected final EventAggregatorService eventAggregatorService;

    public ModelBase(EventAggregatorService eventAggregatorService) {
        this.eventAggregatorService = eventAggregatorService;
    }

    protected void notifyPropertyChanged(String propertyName) {
        eventAggregatorService.publish(new PropertyChangedEvent(this, propertyName));
    }
}
