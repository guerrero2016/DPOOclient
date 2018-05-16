package View;

import Controller.ProjectSelectionController;
import model.project.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProjectSelectionView extends JPanel {

    private final boolean isOwner;
    private JScrollPane scrollPane;
    private JPanel gridPanel;
    private int nBoxes;
    private ArrayList<ProjectBoxView> projectBoxViews;
    private ProjectSelectionController projectSelectionController;
    private GridBagConstraints gridBagConstraints;

    private final int numberOfColumns = 3;
    private ArrayList<Project> projects;

    public ProjectSelectionView (boolean isOwner) {

        this.isOwner = isOwner;

        projectBoxViews = new ArrayList<>();
        setLayout(new BorderLayout());

        gridPanel = new JPanel(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        scrollPane = new JScrollPane(gridPanel);

        add(scrollPane, BorderLayout.CENTER);

    }

    public void registerController (ProjectSelectionController controller) {
        this.projectSelectionController = controller;
        for (ProjectBoxView projectBoxView: projectBoxViews) {
            projectBoxView.registerMouseListener(controller);
            projectBoxView.registerButtonListener(controller);
        }
    }

    public void createProjectBoxes(ArrayList<Project> projects) {

        setVisible(false);
        this.projects = projects;
        nBoxes = projects.size();
        int x = 0;
        int y = -1;

        for (int j = 0; j < nBoxes; j++) {
            if (j % numberOfColumns == 0) {
                x = 0;
                y++;
            }
            createProjectBoxView(projects.get(j), x, y, j);
            x++;
        }
        setVisible(true);
    }

    private void createProjectBoxView (Project project, int x, int y, int index) {
        if(project != null) {
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.gridx = x;
            gridBagConstraints.gridy = y;
            gridBagConstraints.insets.top = 10;
            gridBagConstraints.weightx = 1;
            ProjectBoxView projectBoxView = new ProjectBoxView(project, index, isOwner);
            projectBoxView.registerMouseListener(projectSelectionController);
            projectBoxView.registerButtonListener(projectSelectionController);
            projectBoxViews.add(projectBoxView);
            gridPanel.add(projectBoxView, gridBagConstraints);
            scrollPane.getViewport().setView(gridPanel);
        }
    }

    public void addProjectBox(Project project) {
        projects.add(project);
        int y = calculateNumberRows(nBoxes) - 1;
        int x = nBoxes - numberOfColumns*(y);
        if (nBoxes % numberOfColumns == 0) {
            x = 0;
            y++;
        }
        createProjectBoxView(project, x, y, projects.size() - 1);
        nBoxes++;
    }

    public void removeProject(int index) {
        projectBoxViews.remove(index);
        projects.remove(index);
        projectBoxViews = new ArrayList<>();
        setVisible(false);
        gridPanel = new JPanel(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        scrollPane.getViewport().setView(gridPanel);
        createProjectBoxes(projects);
        setVisible(true);
    }

    private int calculateNumberRows (int nBoxes) {
        if (nBoxes % numberOfColumns == 0) {
            return nBoxes/numberOfColumns;
        } else {
            return nBoxes/numberOfColumns + 1;
        }
    }

}
