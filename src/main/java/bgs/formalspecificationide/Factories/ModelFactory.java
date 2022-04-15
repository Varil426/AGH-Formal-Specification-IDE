package bgs.formalspecificationide.Factories;

import bgs.formalspecificationide.Model.Project;
import bgs.formalspecificationide.Services.ModelTrackerService;
import com.google.inject.Inject;

public class ModelFactory {

    private final ModelTrackerService modelTrackerService;

    @Inject
    public ModelFactory(ModelTrackerService modelTrackerService) {

        this.modelTrackerService = modelTrackerService;
    }

    public Project createProject(String name) {
        var project = new Project();
        project.subscribe(modelTrackerService);
        return project;
    }

}
