package bgs.formalspecificationide.ui;

import bgs.formalspecificationide.factories.IModelFactory;
import bgs.formalspecificationide.model.ModelBase;
import bgs.formalspecificationide.model.Project;
import bgs.formalspecificationide.model.ProjectName;
import bgs.formalspecificationide.persistence.repositories.IProjectNameRepository;
import bgs.formalspecificationide.persistence.repositories.IProjectRepository;
import bgs.formalspecificationide.services.XmlParserService;
import bgs.formalspecificationide.ui.editors.actionEditor.ActionEditorController;
import bgs.formalspecificationide.ui.editors.imageViewer.ImageViewerController;
import bgs.formalspecificationide.ui.editors.scenarioSelector.ScenarioSelectorEditorController;
import bgs.formalspecificationide.ui.editors.useCaseSelector.UseCaseSelectorEditorController;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import java.util.UUID;

public class MainWindowController implements IController {
    private final XmlParserService xmlParserService;
    private final IModelFactory modelFactory;
    private final IProjectRepository projectRepository;
    private final IProjectNameRepository projectNameRepository;

    private Project project;
    
    @FXML
    private UseCaseSelectorEditorController useCaseSelectorEditorController;

    @FXML
    public ScenarioSelectorEditorController scenarioSelectorEditorController;
    
    @FXML
    public ActionEditorController actionEditorController;

    @FXML
    public ImageViewerController imageViewerController;

    @Inject
    public MainWindowController(XmlParserService xmlParserService, IModelFactory modelFactory, IProjectRepository projectRepository, IProjectNameRepository projectNameRepository) {

        this.xmlParserService = xmlParserService;
        this.modelFactory = modelFactory;
        this.projectRepository = projectRepository;
        this.projectNameRepository = projectNameRepository;
    }

    @Override
    public void load(ModelBase object) {
        if (object instanceof Project project)
            this.project = project;
        else 
            throw new UnsupportedOperationException();
    }

    @Override
    public void unload() {
        project = null;
        // TODO
    }
    
    @FXML
    private void saveClicked() {
        projectRepository.save(project);
        projectNameRepository.saveAll();
    }

    @FXML
    private void loadClicked() {

        class ProjectNamePresenter {
            public ProjectName getProjectName() {
                return projectName;
            }

            final ProjectName projectName;
            
            public ProjectNamePresenter(ProjectName projectName) {
                this.projectName = projectName;
            }
            
            @Override
            public String toString() {
                return projectName.getProjectName();
            }
        }
        
        var projectChooserDialog = new ChoiceDialog<ProjectNamePresenter>();
        projectChooserDialog.getItems().addAll(projectNameRepository.getAll().stream().map(ProjectNamePresenter::new).toList());
        projectChooserDialog.setTitle("Load a project");
        projectChooserDialog.setContentText("Select:");
        projectChooserDialog.showAndWait();
        
        var result = projectChooserDialog.getResult();
        if (result == null)
            return;
        
        var project = projectRepository.getById(result.getProjectName().getProjectId());

        project.ifPresent(this::load);
    }
    
    @FXML
    private void importXml() {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Select an input file");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML Filter", "xml"));
        var file = fileChooser.showOpenDialog(null);
        if (file == null)
            return;
        
        var textInputDialog = new TextInputDialog("Enter a project name");
        textInputDialog.showAndWait();
        var projectName = textInputDialog.getResult();
        if (projectName == null)
            return;
        
        var project = modelFactory.createProject(projectName);
        load(project);
        
        var useCaseDiagram = modelFactory.createUseCaseDiagram(project, UUID.randomUUID(), null);
        
        xmlParserService.parseXml(useCaseDiagram, file);
    }
}
