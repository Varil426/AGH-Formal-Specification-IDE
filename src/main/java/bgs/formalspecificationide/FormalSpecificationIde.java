package bgs.formalspecificationide;

import bgs.formalspecificationide.Factories.ModelFactory;
import bgs.formalspecificationide.Model.Project;
import bgs.formalspecificationide.Persistence.Repositories.IProjectRepository;
import bgs.formalspecificationide.Services.IResourceService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.stage.Stage;

public class FormalSpecificationIde extends Application {

    private final Injector injector = Guice.createInjector(new MainModule());

    private final IResourceService resourceService = injector.getInstance(IResourceService.class);

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(resourceService.getText("ApplicationTitle"));


        stage.show();
    }
}
