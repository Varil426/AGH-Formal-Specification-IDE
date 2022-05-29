package bgs.formalspecificationide.Utilities;

import bgs.formalspecificationide.Events.Event;

public interface IObserver {

    /**
     * Notifies observer about an event in observable.
     * @param event An event.
     */
    void notify(Event<?> event);

}
