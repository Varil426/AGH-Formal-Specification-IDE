package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Events.IsDirtyEvent;
import bgs.formalspecificationide.Events.PropertyChangedEvent;
import bgs.formalspecificationide.Utilities.*;
import bgs.formalspecificationide.Events.Event;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.UUID;

public abstract class ModelBase implements IObservable {

    @JsonCreator
    public ModelBase(@JsonProperty("id") UUID id) {
        this.id = id;
    }

    private final UUID id;

    public UUID getId() {
        return id;
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
        notifyDirty();
    }

    protected void notifyDirty() {
        notifyObservers(new IsDirtyEvent(this));
        if (this instanceof ICanSetDirty dirty)
            dirty.setDirty();
    }
}
