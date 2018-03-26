package View;

import javax.swing.*;
import java.awt.*;

public class ProjectSelectionView extends JFrame {

    private JPanel gridPanel;
    private JPanel lastFlowPanel;
    private int nGhostBox;

    public ProjectSelectionView () {

        setLayout(new BorderLayout());

        createProjectBoxes(5);

        setSize(800,500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createProjectBoxes (int nBoxes) {
        final int nRows = calculateNumberRows(nBoxes);
        nGhostBox = calculateGhostBoxes(nBoxes, nRows);

        gridPanel = new JPanel(new GridLayout(nRows,1));

        for (int i = 0; i < nBoxes; i++) {
            if (i%4 == 0) {
                lastFlowPanel = new JPanel(new FlowLayout());
                gridPanel.add(lastFlowPanel);
            }
            addProjectBox("TUPRIMA", Color.BLUE);
        }

        addGhostBoxes();

        add(gridPanel, BorderLayout.NORTH);
    }

    private void addGhostBoxes () {
        for (int i = 0; i < nGhostBox; i++) {
            addProjectBox("", gridPanel.getBackground());
        }
    }

    public void addProjectBox (String title, Color color) {
        lastFlowPanel.add(new ProjectBoxView(title, color));
    }

    private int calculateGhostBoxes (int nBoxes, int nRows) {
        return 4 - (nBoxes - 4*(nRows-1));
    }

    private int calculateNumberRows (int nBoxes) {
        return (nBoxes / 4) + (nBoxes % 4);
    }

}
