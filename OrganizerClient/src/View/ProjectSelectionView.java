package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProjectSelectionView extends JFrame {

    private JScrollPane scrollPane;
    private JPanel gridPanel;
    private JPanel lastFlowPanel;
    private int nGhostBox;
    private int nRows;

    private ArrayList<ProjectBoxView> projectBoxViews;

    public ProjectSelectionView () {

        projectBoxViews = new ArrayList<>();

        setLayout(new BorderLayout());

        createProjectBoxes(new String[]{"1", "2"}, new Color[]{Color.RED, Color.BLACK});
        addProjectBox("3",Color.CYAN);
        removeBoxAt(1);

        setSize(800,500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createProjectBoxes (String [] titles, Color[] colors) {
        int nBoxes = titles.length;

        nRows = calculateNumberRows(nBoxes);
        nGhostBox = calculateGhostBoxes(nBoxes);

        gridPanel = new JPanel(new GridLayout(nRows,1));

        for (int i = 0; i < nBoxes; i++) {
            if (i%4 == 0) {
                lastFlowPanel = new JPanel(new FlowLayout());
                gridPanel.add(lastFlowPanel);
            }
            ProjectBoxView projectBoxView = new ProjectBoxView(titles[i],colors[i]);
            projectBoxViews.add(projectBoxView);
            lastFlowPanel.add(projectBoxView);
        }

        addGhostBoxes();

        scrollPane = new JScrollPane(gridPanel);
        add(scrollPane, BorderLayout.NORTH);
    }

    private void addGhostBoxes () {
        for (int i = 0; i < nGhostBox; i++) {
            lastFlowPanel.add(new ProjectBoxView("", gridPanel.getBackground()));
        }
    }

    private void removeBoxAt (int index) {
        projectBoxViews.remove(index);
        Color [] colors = new Color[projectBoxViews.size()];
        String [] titles = new String[projectBoxViews.size()];

        for (int i = 0; i < projectBoxViews.size(); i++) {
            colors[i] = projectBoxViews.get(i).getBackground();
            titles[i] = projectBoxViews.get(i).getTitle();
        }
        createProjectBoxes(titles, colors);
    }

    public void addProjectBox (String title, Color color) {
        ProjectBoxView projectBoxView = new ProjectBoxView(title, color);
        if (nGhostBox == 0) {
            nRows++;
            nGhostBox = 3;

            lastFlowPanel = new JPanel(new FlowLayout());
            lastFlowPanel.add(projectBoxView);
            addGhostBoxes();
            addFlowPanelToGrid();
        } else {
            deleteGhostBoxes();
            lastFlowPanel.add(projectBoxView);
            nGhostBox--;
            addGhostBoxes();
        }
        projectBoxViews.add(projectBoxView);
    }

    private void addFlowPanelToGrid () {
        ((GridLayout) gridPanel.getLayout()).setRows(nRows);
        gridPanel.add(lastFlowPanel);
        scrollPane.getViewport().setView(gridPanel);
    }

    private void deleteGhostBoxes () {
        for (int i = 3; i > (4 - nGhostBox - 1); i--) {
            lastFlowPanel.remove(i);
        }
    }

    private int calculateGhostBoxes (int nBoxes) {
        return 4 - (nBoxes - 4*(nRows-1));
    }

    private int calculateNumberRows (int nBoxes) {
        if (nBoxes % 4 != 0) {
            return (nBoxes / 4) + 1;
        } else {
            return (nBoxes / 4);
        }
    }

}
