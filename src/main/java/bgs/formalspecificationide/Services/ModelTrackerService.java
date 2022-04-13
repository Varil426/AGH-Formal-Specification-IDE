package bgs.formalspecificationide.Services;

import bgs.formalspecificationide.Utilities.Event;
import bgs.formalspecificationide.Utilities.IObserver;
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
