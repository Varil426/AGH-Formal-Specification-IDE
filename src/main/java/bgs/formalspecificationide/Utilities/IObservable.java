package bgs.formalspecificationide.Utilities;

public interface IObservable {

    /**
     * Subscribes to all events.
     * @param observer An observer.
     */
    void subscribe(IObserver observer);


    /**
     * Unsubscribes from all events.
     * @param observer An observer.
     */
    void unsubscribe(IObserver observer);

}
