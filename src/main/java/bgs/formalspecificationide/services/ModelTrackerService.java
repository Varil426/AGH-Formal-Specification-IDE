package bgs.formalspecificationide.services;

import bgs.formalspecificationide.events.Event;
import bgs.formalspecificationide.utilities.IObserver;
import com.google.inject.Inject;

public class ModelTrackerService implements IObserver {

    private final EventAggregatorService eventAggregatorService;

    @Inject
    public ModelTrackerService(EventAggregatorService eventAggregatorService) {

        this.eventAggregatorService = eventAggregatorService;
    }

    @Override
    public void notify(Event<?> event) {
        eventAggregatorService.publish(event);
    }

}
