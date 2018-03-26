package View;

import javax.swing.*;
import java.awt.*;

public class ProjectSelectionView extends JFrame {

    private JScrollPane scrollPane;
    private JPanel gridPanel;
    private JPanel lastFlowPanel;
    private int nGhostBox;
    private int nRows;

    public ProjectSelectionView () {

        setLayout(new BorderLayout());


        createProjectBoxes(4);
        addProjectBox("TUPRIMA",Color.BLUE);
        addProjectBox("Lactosito", Color.CYAN);


        setSize(800,500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createProjectBoxes (int nBoxes) {
        nRows = calculateNumberRows(nBoxes);
        nGhostBox = calculateGhostBoxes(nBoxes);

        gridPanel = new JPanel(new GridLayout(nRows,1));

        for (int i = 0; i < nBoxes; i++) {
            if (i%4 == 0) {
                lastFlowPanel = new JPanel(new FlowLayout());
                gridPanel.add(lastFlowPanel);
            }
            lastFlowPanel.add(new ProjectBoxView("ASD", Color.RED));
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

    public void addProjectBox (String title, Color color) {
        if (nGhostBox == 0) {
            nRows++;
            nGhostBox = 3;

            lastFlowPanel = new JPanel(new FlowLayout());
            lastFlowPanel.add(new ProjectBoxView(title, color));
            addGhostBoxes();
            addFlowPanelToGrid();
        } else {
            deleteGhostBoxes();
            lastFlowPanel.add(new ProjectBoxView(title, color));
            nGhostBox--;
            addGhostBoxes();
        }
    }

    private void addFlowPanelToGrid () {
        final JPanel newGridPanel = new JPanel(new GridLayout(nRows,1));
        for (int i = 0; i < nRows - 1; i++) {
            newGridPanel.add(gridPanel.getComponent(i));
        }
        newGridPanel.add(lastFlowPanel);
        gridPanel = newGridPanel;
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
        if (nBoxes % 4 == 3) {
            return (nBoxes / 4) + 1;
        } else {
            return (nBoxes / 4);
        }
    }

}
