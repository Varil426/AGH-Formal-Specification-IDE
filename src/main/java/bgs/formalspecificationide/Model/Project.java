package bgs.formalspecificationide.Model;

// TODO Add the rest of properties
public class Project extends ModelBase {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged("Name");
    }
}
