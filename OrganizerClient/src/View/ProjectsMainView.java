package View;

import Controller.ProjectSelectionController;

import javax.swing.*;
import java.awt.*;

public class ProjectsMainView extends JFrame {

    private final ProjectSelectionView ownerProjectSelectionView;
    private final ProjectSelectionView friendProjectSelectionView;
    private final JButton logOutButton;
    private final JButton addProjectButton;
    public static final String ADD_PROJECT_ACTION_COMMAND = "AddProject";

    private final int S_WIDTH = 800;

    public ProjectsMainView () {

        ownerProjectSelectionView = new ProjectSelectionView();
        friendProjectSelectionView = new ProjectSelectionView();

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                ownerProjectSelectionView, friendProjectSelectionView);
        jSplitPane.setDividerLocation(S_WIDTH/2);
        add(jSplitPane);

        JPanel southPanel = new JPanel(new BorderLayout());
        logOutButton = new JButton("Tancar sessió");
        addProjectButton = new JButton("+");
        southPanel.add(logOutButton, BorderLayout.WEST);
        southPanel.add(addProjectButton, BorderLayout.EAST);
        addProjectButton.setActionCommand(this.ADD_PROJECT_ACTION_COMMAND);

        add(southPanel, BorderLayout.SOUTH);
        add(jSplitPane, BorderLayout.CENTER);

        setSize(S_WIDTH,500);
        setTitle("LSOrganizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        friendProjectSelectionView.createProjectBoxes(titles, colors);
    }

    public void addProjectBox (String title, Color color) {
        ownerProjectSelectionView.addProjectBox(title, color);
    }
}
