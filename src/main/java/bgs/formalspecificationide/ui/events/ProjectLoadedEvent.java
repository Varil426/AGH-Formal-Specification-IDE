package bgs.formalspecificationide.ui.events;

import bgs.formalspecificationide.events.Event;
import bgs.formalspecificationide.model.Project;
import bgs.formalspecificationide.ui.IController;

public class ProjectLoadedEvent extends Event<IController> {

    public Project getProject() {
        return project;
    }

    final Project project;
    
    public ProjectLoadedEvent(IController publisher, Project project) {
        super(publisher);
        this.project = project;
    }
}
