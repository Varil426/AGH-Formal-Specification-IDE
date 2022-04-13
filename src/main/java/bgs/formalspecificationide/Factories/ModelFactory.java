package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.Project;
import bgs.formalspecificationide.Services.ModelTrackerService;

public class ModelFactory {

    private final ModelTrackerService modelTrackerService;

    public ModelFactory(ModelTrackerService modelTrackerService) {

        this.modelTrackerService = modelTrackerService;
    }

    public Project createProject(String name) {
        var project = new Project();
        project.subscribe(modelTrackerService);
        return project;
    }

}
