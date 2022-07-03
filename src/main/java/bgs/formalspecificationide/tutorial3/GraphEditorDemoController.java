package bgs.formalspecificationide.tutorial3;

import bgs.formalspecificationide.tutorial3.customskins.*;
import bgs.formalspecificationide.tutorial3.selections.*;
import bgs.formalspecificationide.tutorial3.utils.*;
import bgs.formalspecificationide.tutorial3.world.*;
import io.github.eckig.grapheditor.*;
import io.github.eckig.grapheditor.core.skins.defaults.connection.*;
import io.github.eckig.grapheditor.core.view.*;
import io.github.eckig.grapheditor.model.*;
import javafx.application.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.eclipse.emf.ecore.*;

import java.util.*;

/**
 * Controller for the {@link GraphEditorDemo} application.
 */
public class GraphEditorDemoController {

    private final GraphEditor graphEditor = new OwnDefaultGraphEditor();
    private final SelectionCopier selectionCopier = new SelectionCopier(graphEditor.getSkinLookup(), graphEditor.getSelectionManager());
    private final GraphEditorPersistence graphEditorPersistence = new GraphEditorPersistence();
    private final ObjectProperty<DefaultSkinController> activeSkinController = new SimpleObjectProperty<>() {

        @Override
        protected void invalidated() {
            super.invalidated();
            if (get() != null) {
                get().activate();
            }
        }

    };
    @FXML
    private AnchorPane root;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem addConnectorButton;
    @FXML
    private MenuItem clearConnectorsButton;
    @FXML
    private Menu connectorTypeMenu;
    @FXML
    private Menu connectorPositionMenu;
    @FXML
    private RadioMenuItem inputConnectorTypeButton;
    @FXML
    private RadioMenuItem outputConnectorTypeButton;
    @FXML
    private RadioMenuItem leftConnectorPositionButton;
    @FXML
    private RadioMenuItem rightConnectorPositionButton;
    @FXML
    private RadioMenuItem topConnectorPositionButton;
    @FXML
    private RadioMenuItem bottomConnectorPositionButton;
    @FXML
    private RadioMenuItem showGridButton;
    @FXML
    private RadioMenuItem snapToGridButton;
    @FXML
    private Menu readOnlyMenu;
    @FXML
    private RadioMenuItem defaultSkinButton;
    @FXML
    private Menu intersectionStyle;
    @FXML
    private RadioMenuItem gappedStyleButton;
    @FXML
    private RadioMenuItem detouredStyleButton;
    @FXML
    private ToggleButton minimapButton;
    @FXML
    private GraphEditorContainer graphEditorContainer;
    private DefaultSkinController defaultSkinController;

    /**
     * Called by JavaFX when FXML is loaded.
     */
    public void initialize() {

        final GModel model = GraphFactory.eINSTANCE.createGModel();

        graphEditor.setModel(model);
        graphEditorContainer.setGraphEditor(graphEditor);

        setDetouredStyle();

        defaultSkinController = new DefaultSkinController(graphEditor, graphEditorContainer);

        activeSkinController.set(defaultSkinController);

        graphEditor.modelProperty().addListener((w, o, n) -> selectionCopier.initialize(n));
        selectionCopier.initialize(model);

        initializeMenuBar();
    }

    /**
     * Pans the graph editor container to place the window over the center of the
     * content.
     *
     * <p>
     * Only works after the scene has been drawn, when getWidth() and getHeight()
     * return non-zero values.
     * </p>
     */
    public void panToCenter() {
        graphEditorContainer.panTo(Pos.CENTER);
    }

    @FXML
    public void load() {
        graphEditorPersistence.loadFromFile(graphEditor);
        checkSkinType();
    }

    @FXML
    public void loadSample() {
        defaultSkinButton.setSelected(true);
        setDefaultSkin();
        graphEditorPersistence.loadSample(graphEditor);
    }

    @FXML
    public void loadSampleLarge() {
        defaultSkinButton.setSelected(true);
        setDefaultSkin();
        graphEditorPersistence.loadSampleLarge(graphEditor);
    }

    @FXML
    public void save() {
        graphEditorPersistence.saveToFile(graphEditor);
    }

    @FXML
    public void clearAll() {
        Commands.clear(graphEditor.getModel());
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    @FXML
    public void undo() {
        Commands.undo(graphEditor.getModel());
    }

    @FXML
    public void redo() {
        Commands.redo(graphEditor.getModel());
    }

    @FXML
    public void copy() {
        selectionCopier.copy();
    }

    @FXML
    public void paste() {
        activeSkinController.get().handlePaste(selectionCopier);
    }

    @FXML
    public void selectAll() {
        activeSkinController.get().handleSelectAll();
    }

    @FXML
    public void deleteSelection() {
        final List<EObject> selection = new ArrayList<>(graphEditor.getSelectionManager().getSelectedItems());
        graphEditor.delete(selection);
    }

    @FXML
    public void addSeq() {
        World.getInstance().setCurrentNodeType("SEQ");
        activeSkinController.get().addNode(graphEditor.getView().getLocalToSceneTransform().getMxx());
    }

    @FXML
    public void addBranch() {
        World.getInstance().setCurrentNodeType("BRANCH");
        activeSkinController.get().addNode(graphEditor.getView().getLocalToSceneTransform().getMxx());
    }

    @FXML
    public void addConnector() {
        activeSkinController.get().addConnector(getSelectedConnectorPosition(), inputConnectorTypeButton.isSelected());
    }

    @FXML
    public void clearConnectors() {
        activeSkinController.get().clearConnectors();
    }

    @FXML
    public void setDefaultSkin() {
        activeSkinController.set(defaultSkinController);
    }

    @FXML
    public void setGappedStyle() {

        graphEditor.getProperties().getCustomProperties().remove(SimpleConnectionSkin.SHOW_DETOURS_KEY);
        graphEditor.reload();
    }

    @FXML
    public void setDetouredStyle() {

        final Map<String, String> customProperties = graphEditor.getProperties().getCustomProperties();
        customProperties.put(SimpleConnectionSkin.SHOW_DETOURS_KEY, Boolean.toString(true));
        graphEditor.reload();
    }

    @FXML
    public void toggleMinimap() {
        graphEditorContainer.getMinimap().visibleProperty().bind(minimapButton.selectedProperty());
    }

    /**
     * Initializes the menu bar.
     */
    private void initializeMenuBar() {

        final ToggleGroup skinGroup = new ToggleGroup();
        skinGroup.getToggles().addAll(defaultSkinButton);

        final ToggleGroup connectionStyleGroup = new ToggleGroup();
        connectionStyleGroup.getToggles().addAll(gappedStyleButton, detouredStyleButton);

        final ToggleGroup connectorTypeGroup = new ToggleGroup();
        connectorTypeGroup.getToggles().addAll(inputConnectorTypeButton, outputConnectorTypeButton);

        final ToggleGroup positionGroup = new ToggleGroup();
        positionGroup.getToggles().addAll(leftConnectorPositionButton, rightConnectorPositionButton);
        positionGroup.getToggles().addAll(topConnectorPositionButton, bottomConnectorPositionButton);

        graphEditor.getProperties().gridVisibleProperty().bind(showGridButton.selectedProperty());
        graphEditor.getProperties().snapToGridProperty().bind(snapToGridButton.selectedProperty());

        for (final EditorElement type : EditorElement.values()) {
            final CheckMenuItem readOnly = new CheckMenuItem(type.name());
            graphEditor.getProperties().readOnlyProperty(type).bind(readOnly.selectedProperty());
            readOnlyMenu.getItems().add(readOnly);
        }

        minimapButton.setGraphic(AwesomeIcon.MAP.node());

        final SetChangeListener<? super EObject> selectedNodesListener = change -> checkConnectorButtonsToDisable();
        graphEditor.getSelectionManager().getSelectedItems().addListener(selectedNodesListener);
        checkConnectorButtonsToDisable();
    }

    /**
     * Crudely inspects the model's first node and sets the new skin type accordingly.
     */
    private void checkSkinType() {
        if (!graphEditor.getModel().getNodes().isEmpty()) {
            activeSkinController.set(defaultSkinController);
        }
    }

    /**
     * Checks if the connector buttons need disabling (e.g. because no nodes are selected).
     */
    private void checkConnectorButtonsToDisable() {

        final boolean nothingSelected = graphEditor.getSelectionManager().getSelectedItems().stream()
                .noneMatch(e -> e instanceof GNode);

        if (nothingSelected) {
            addConnectorButton.setDisable(true);
            clearConnectorsButton.setDisable(true);
            connectorTypeMenu.setDisable(false);
            connectorPositionMenu.setDisable(false);
        } else {
            addConnectorButton.setDisable(false);
            clearConnectorsButton.setDisable(false);
            connectorTypeMenu.setDisable(false);
            connectorPositionMenu.setDisable(false);
        }
    }

    /**
     * Gets the side corresponding to the currently selected connector position in the menu.
     *
     * @return the {@link Side} corresponding to the currently selected connector position
     */
    private Side getSelectedConnectorPosition() {

        if (leftConnectorPositionButton.isSelected()) {
            return Side.LEFT;
        } else if (rightConnectorPositionButton.isSelected()) {
            return Side.RIGHT;
        } else if (topConnectorPositionButton.isSelected()) {
            return Side.TOP;
        } else {
            return Side.BOTTOM;
        }
    }
}
