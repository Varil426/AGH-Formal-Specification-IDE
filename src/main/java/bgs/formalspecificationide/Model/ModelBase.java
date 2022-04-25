package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Utilities.Event;
import bgs.formalspecificationide.Utilities.IObservable;
import bgs.formalspecificationide.Utilities.IObserver;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

public abstract class ModelBase implements IObservable, IObserver {

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

    public static class IsDirtyEvent extends Event<ModelBase> {

        public IsDirtyEvent(ModelBase publisher) {
            super(publisher);
        }
    }

    @JsonIgnore
    private final HashSet<IObserver> observers = new HashSet<>();

    @Override
    public final void subscribe(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public final void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    protected final void notifyObservers(Event<?> event) {
        for (var observer : observers) {
            observer.notify(event);
        }
    }

    protected void propertyChanged(String propertyName) {
        notifyPropertyChanged(propertyName);
    }

    private void notifyPropertyChanged(String propertyName) {
        notifyObservers(new PropertyChangedEvent(this, propertyName));
        notifyObservers(new IsDirtyEvent(this));
    }

    @Override
    public void notify(Event<?> event) {
        if (event instanceof IsDirtyEvent) {
            notifyObservers(event);
        }
    }
}
