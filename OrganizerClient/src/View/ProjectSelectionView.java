package View;

import Controller.ProjectSelectionController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProjectSelectionView extends JFrame {

    private JScrollPane scrollPane;
    private JPanel gridPanel;
    private int nBoxes;
    private ArrayList<ProjectBoxView> projectBoxViews;
    private final JButton logOutButton;
    private final JButton addProjectButton;
    private ProjectSelectionController projectSelectionController;

    public static final String ADD_PROJECT_ACTION_COMMAND = "AddProject";

    private GridBagConstraints gridBagConstraints;

    public ProjectSelectionView () {

        projectBoxViews = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel southPanel = new JPanel(new BorderLayout());
        logOutButton = new JButton("Tancar sessió");
        addProjectButton = new JButton("+");

        gridPanel = new JPanel(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        scrollPane = new JScrollPane(gridPanel);

        southPanel.add(logOutButton, BorderLayout.WEST);
        southPanel.add(addProjectButton, BorderLayout.EAST);

        addProjectButton.setActionCommand(this.ADD_PROJECT_ACTION_COMMAND);

        add(scrollPane, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        setSize(800,500);
        setResizable(false);
        setTitle("LSOrganizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void registerController (ProjectSelectionController controller) {
        this.projectSelectionController = controller;
        addProjectButton.addActionListener(controller);
        for (ProjectBoxView projectBoxView: projectBoxViews) {
            projectBoxView.registerMouseListener(controller);
        }
    }

    public void createProjectBoxes (String [] titles, Color[] colors) {
        nBoxes = titles.length;
        int x = 0;
        int y = -1;

        for (int j = 0; j < nBoxes; j++) {
            if (j % 4 == 0) {
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
        int x = nBoxes - 4*(y);
        if (nBoxes % 4 == 0) {
            x = 0;
            y++;
        }
        createProjectBoxView(title, color, x, y);
        nBoxes++;
    }

    private void removeBoxAt (int index) {
        projectBoxViews.remove(index);
        Color [] colors = new Color[projectBoxViews.size()];
        String [] titles = new String[projectBoxViews.size()];

        for (int i = 0; i < projectBoxViews.size(); i++) {
            colors[i] = projectBoxViews.get(i).getBackground();
            titles[i] = projectBoxViews.get(i).getTitle();
        }
        projectBoxViews.removeAll(projectBoxViews);
        createProjectBoxes(titles, colors);
    }

    private int calculateNumberRows (int nBoxes) {
        if (nBoxes % 4 == 0) {
            return nBoxes/4;
        } else {
            return nBoxes/4 + 1;
        }
    }

}
