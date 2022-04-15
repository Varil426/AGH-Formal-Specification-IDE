package bgs.formalspecificationide.Utilities;

public interface IObserver {

    /**
     * Notifies observer about an event in observable.
     * @param event An event.
     */
    void notify(Event<?> event);

}
