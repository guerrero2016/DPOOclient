package View;

import javax.swing.*;
import java.awt.*;

public class ProjectSelectionView extends JFrame {

    JPanel gridPanel;

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
        final int nGhostBox = calculateGhostBoxes(nBoxes, nRows);

        gridPanel = new JPanel(new GridLayout(nRows,1));
        JPanel flowPanel = new JPanel(new FlowLayout());

        for (int i = 0; i < nBoxes; i++) {
            if (i%4 == 0) {
                flowPanel = new JPanel(new FlowLayout());
                gridPanel.add(flowPanel);
            }
            flowPanel.add(new ProjectBoxView("a", Color.BLUE));
        }

        add(gridPanel, BorderLayout.NORTH);
    }

    private int calculateGhostBoxes (int nBoxes, int nRows) {
        return 4 - (nBoxes - 4*nRows);
    }

    private int calculateNumberRows (int nBoxes) {
        return (nBoxes / 4) + (nBoxes % 4);
    }

}
