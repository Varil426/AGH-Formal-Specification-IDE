package bgs.formalspecificationide.Events;

public class CollectionModifiedEvent<T> extends Event<T>{

    public enum ModificationEnum {
        ADDED,
        REMOVED,
    }

    private final T item;

    private final ModificationEnum modification;

    public CollectionModifiedEvent(T publisher, T item, ModificationEnum modification) {
        super(publisher);
        this.item = item;
        this.modification = modification;
    }

    public T getItem() {
        return item;
    }

    public ModificationEnum getModification() {
        return modification;
    }
}
