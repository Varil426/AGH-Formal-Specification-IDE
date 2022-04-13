package bgs.formalspecificationide.Model;

import bgs.formalspecificationide.Services.EventAggregatorService;
import bgs.formalspecificationide.Utilities.Event;

// TODO Add the rest of properties
public class Project extends ModelBase {

    private String name;

    public Project(EventAggregatorService eventAggregatorService) {
        super(eventAggregatorService);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged("Name");
    }
}
