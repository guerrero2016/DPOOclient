package View;

import Controller.ProjectSelectionController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProjectSelectionView extends JPanel {

    private JScrollPane scrollPane;
    private JPanel gridPanel;
    private int nBoxes;
    private ArrayList<ProjectBoxView> projectBoxViews;
    private ProjectSelectionController projectSelectionController;
    private GridBagConstraints gridBagConstraints;

    private final int numberOfColumns = 3;

    public ProjectSelectionView () {

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
        gridBagConstraints.weightx = 0.25;
        ProjectBoxView projectBoxView = new ProjectBoxView(title, color);
        projectBoxView.registerMouseListener(projectSelectionController);
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

    public void removeProjectAtIndex (int index) {

    }

    private int calculateNumberRows (int nBoxes) {
        if (nBoxes % numberOfColumns == 0) {
            return nBoxes/numberOfColumns;
        } else {
            return nBoxes/numberOfColumns + 1;
        }
    }

}
