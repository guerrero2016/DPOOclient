package View.project;

import Controller.project.ProjectsMainViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa el panell dels projectes dels usuaris
 */
public class ProjectsMainView extends JPanel {

    public static final String VIEW_NAME = "ProjectsView";
    public static final int VIEW_TAG = 3;

    private final ProjectSelectionView ownerProjectSelectionView;
    private final ProjectSelectionView friendProjectSelectionView;
    private final JButton addProjectButton;
    public static final String ADD_PROJECT_ACTION_COMMAND = "AddProject";

    private final int S_WIDTH = 1200;

    /**
     * Constructor que requereix d'un panell de projectes del que s'es propietari i un dels projectes compartits
     * @param ownerSelection Panell projectes propis
     * @param sharedSelection Panell projectes compartits
     */
    public ProjectsMainView (ProjectSelectionView ownerSelection, ProjectSelectionView sharedSelection) {

        JPanel ownerPane = new JPanel(new BorderLayout());
        JPanel friendPane = new JPanel(new BorderLayout());
        JLabel ownerLabel = new JLabel("Your projects");
        JLabel friendLabel = new JLabel("Shared projects");
        ownerLabel.setFont(ownerLabel.getFont().deriveFont(Font.BOLD, 30));
        ownerLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        friendLabel.setFont(ownerLabel.getFont());
        friendLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel ownerAuxPanel = new JPanel(new FlowLayout());
        ownerAuxPanel.add(ownerLabel);
        JPanel friendAuxPanel = new JPanel(new FlowLayout());
        friendAuxPanel.add(friendLabel);

        ownerProjectSelectionView = ownerSelection;
        friendProjectSelectionView = sharedSelection;


        ownerPane.add(ownerAuxPanel, BorderLayout.NORTH);
        ownerPane.add(ownerProjectSelectionView, BorderLayout.CENTER);
        friendPane.add(friendAuxPanel, BorderLayout.NORTH);
        friendPane.add(friendProjectSelectionView, BorderLayout.CENTER);

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                ownerPane, friendPane);
        jSplitPane.setDividerLocation(S_WIDTH/2);
        jSplitPane.setEnabled(false);
        add(jSplitPane);

        JPanel southPanel = new JPanel(new BorderLayout());
        addProjectButton = new JButton("+");

        setLayout(new BorderLayout());
        southPanel.add(addProjectButton, BorderLayout.EAST);
        addProjectButton.setActionCommand(ADD_PROJECT_ACTION_COMMAND);

        add(southPanel, BorderLayout.SOUTH);
        add(jSplitPane, BorderLayout.CENTER);

        setVisible(true);

    }

    /**
     * Metode encarregat de registrar el controlador de la vista
     * @param controller Controlador
     */
    public void registerController (ProjectsMainViewController controller) {
        ownerProjectSelectionView.registerController(controller.getOwnerSelectionController());
        friendProjectSelectionView.registerController(controller.getSharedSelectionController());
        addProjectButton.addActionListener(controller);
    }

}