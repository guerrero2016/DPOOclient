package View;

import Controller.ProjectSelectionController;
import Model.Project;

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

    public void createProjectBoxes (String [] titles, Color[] colors) {
        setVisible(false);
        nBoxes = titles.length;
        int x = 0;
        int y = -1;

        for (int j = 0; j < nBoxes; j++) {
            if (j % numberOfColumns == 0) {
                x = 0;
                y++;
            }
            createProjectBoxView(titles[j],colors[j], x, y);
            x++;
        }
        setVisible(true);
    }

    private void createProjectBoxView (String title, Color color, int x, int y) {
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.insets.top = 10;
        gridBagConstraints.weightx = 1;
        ProjectBoxView projectBoxView = new ProjectBoxView(title, color, projectBoxViews.size(), isOwner);
        projectBoxView.registerMouseListener(projectSelectionController);
        projectBoxView.registerButtonListener(projectSelectionController);
        projectBoxViews.add(projectBoxView);
        gridPanel.add(projectBoxView, gridBagConstraints);
        scrollPane.getViewport().setView(gridPanel);
    }

    public void addProjectBox (String title, Color color) {
        int y = calculateNumberRows(nBoxes) - 1;
        int x = nBoxes - numberOfColumns*(y);
        if (nBoxes % numberOfColumns == 0) {
            x = 0;
            y++;
        }
        createProjectBoxView(title, color, x, y);
        nBoxes++;
    }

    public void removeProject(int index) {
        projectBoxViews.remove(index);

        String [] titles = new String[projectBoxViews.size()];
        Color [] colors = new Color [projectBoxViews.size()];

        for (int i = 0; i < projectBoxViews.size(); i++) {
            titles[i] = projectBoxViews.get(i).getTitle();
            colors[i] = projectBoxViews.get(i).getBackground();
        }

        projectBoxViews = new ArrayList<>();

        setVisible(false);
        gridPanel = new JPanel(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        createProjectBoxes(titles, colors);
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
