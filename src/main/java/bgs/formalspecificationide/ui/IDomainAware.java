package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.model.ModelBase;

public interface IDomainAware {

    void load(ModelBase object);
    
    void unload();
}
