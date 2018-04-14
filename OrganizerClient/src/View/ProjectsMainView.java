package View;

import Controller.ProjectSelectionController;

import javax.swing.*;
import java.awt.*;

public class ProjectsMainView extends JPanel {

    public static final String VIEW_NAME = "ProjectsView";
    public static final int VIEW_TAG = 3;

    private final ProjectSelectionView ownerProjectSelectionView;
    private final ProjectSelectionView friendProjectSelectionView;
    private final JButton logOutButton;
    private final JButton addProjectButton;
    public static final String ADD_PROJECT_ACTION_COMMAND = "AddProject";

    private final int S_WIDTH = 1000;

    public ProjectsMainView () {

        JPanel ownerPane = new JPanel(new BorderLayout());
        JPanel friendPane = new JPanel(new BorderLayout());
        JLabel ownerLabel = new JLabel("Els teus projectes: ");
        JLabel friendLabel = new JLabel("Projectes compartits: ");
        ownerLabel.setFont(ownerLabel.getFont().deriveFont(Font.BOLD, 30));
        friendLabel.setFont(ownerLabel.getFont());

        ownerProjectSelectionView = new ProjectSelectionView();
        friendProjectSelectionView = new ProjectSelectionView();


        ownerPane.add(ownerLabel, BorderLayout.NORTH);
        ownerPane.add(ownerProjectSelectionView, BorderLayout.CENTER);
        friendPane.add(friendLabel, BorderLayout.NORTH);
        friendPane.add(friendProjectSelectionView, BorderLayout.CENTER);

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                ownerPane, friendPane);
        jSplitPane.setDividerLocation(S_WIDTH/2);
        add(jSplitPane);

        JPanel southPanel = new JPanel(new BorderLayout());
        logOutButton = new JButton("Tancar sessió");
        addProjectButton = new JButton("+");

        setLayout(new BorderLayout());
        southPanel.add(logOutButton, BorderLayout.WEST);
        southPanel.add(addProjectButton, BorderLayout.EAST);
        addProjectButton.setActionCommand(ADD_PROJECT_ACTION_COMMAND);

        add(southPanel, BorderLayout.SOUTH);
        add(jSplitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void registerAddProjectViewController (ProjectSelectionController controller) {
        addProjectButton.addActionListener(controller);
    }

    public void registerProjectSelectionController (ProjectSelectionController controller) {
        ownerProjectSelectionView.registerController(controller);
    }

    public void createOwnerBoxProjects (String[] titles, Color[] colors) {
        ownerProjectSelectionView.createProjectBoxes(titles, colors);
    }

    public void createSharedBoxProjects (String[] titles, Color[] colors) {
        friendProjectSelectionView.createProjectBoxes(titles, colors);
    }

    public void addOwnerProjectBox(String title, Color color) {
        ownerProjectSelectionView.addProjectBox(title, color);
    }

    public void addSharedProjectBox(String title, Color color) {
        friendProjectSelectionView.addProjectBox(title, color);
    }
}
