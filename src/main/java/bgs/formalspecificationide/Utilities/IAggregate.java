package bgs.formalspecificationide.Utilities;

import bgs.formalspecificationide.Model.ModelBase;

import java.util.List;

public interface IAggregate<T> {

    List<T> getChildren();

    void addChild(T child);

    void addChildren(List<T> children);

    void removeChild(T child);

    void removeChildren(List<T> children);

    <Z extends T> List<Z> getChildrenOfType(Class<Z> type);

}
