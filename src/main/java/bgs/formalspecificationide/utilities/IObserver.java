package bgs.formalspecificationide.utilities;

import bgs.formalspecificationide.events.Event;

public interface IObserver {

    /**
     * Notifies observer about an event in observable.
     * @param event An event.
     */
    void notify(Event<?> event);

}
