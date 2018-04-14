package View;

import Controller.ProjectCreationController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddProjectView extends JPanel {

    private final JTextField nameTextField;
    private final Color[] COLORS = new Color[]{Color.RED, Color.BLUE, Color.CYAN, Color.YELLOW, Color.WHITE, Color.ORANGE, Color.green, Color.gray};
    private ArrayList<JPanel> colorsPanels;
    private final JButton createButton;
    private final CustomDialog dialog;

    public AddProjectView () {

        colorsPanels = new ArrayList<>();

        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Nom");
        nameTextField = new JTextField();

        northPanel.add(nameLabel, BorderLayout.NORTH);
        northPanel.add(nameTextField, BorderLayout.CENTER);

        JLabel colorLabel = new JLabel("Color");
        JPanel colorsPanel = new JPanel(new BorderLayout());
        JPanel palettePanel = createColorsPalette();
        colorsPanel.add(colorLabel, BorderLayout.NORTH);
        colorsPanel.add(palettePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        createButton = new JButton("Afegir");
        buttonPanel.add(createButton);

        setLayout(new BorderLayout());
        add(buttonPanel,BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
        add(colorsPanel, BorderLayout.CENTER);

        northPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        colorsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        dialog = new CustomDialog("Crear projecte", this);
    }

    public void setDialogVisible(boolean isVisible) {
        dialog.setDialogVisible(isVisible);
    }

    public String getProjectName () {
        return nameTextField.getText();
    }

    public JPanel createColorsPalette () {
        final JPanel palettePanel = new JPanel(new GridLayout(2,4));
        palettePanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        for (Color color:this.COLORS) {
            JPanel colorPanel = new JPanel();
            colorPanel.setBackground(color);
            palettePanel.add(colorPanel);
            colorsPanels.add(colorPanel);
        }

        return palettePanel;
    }

    public void registerMouseListener (ProjectCreationController controller) {
        for (JPanel colorPanel: colorsPanels) {
            colorPanel.addMouseListener(controller);
        }
        createButton.addActionListener(controller);
    }

    public void selectColor (JPanel colorPanel) {
        deselectAllColors();
        colorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    private void deselectAllColors () {
        for (JPanel colorPanel:colorsPanels) {
            colorPanel.setBorder(BorderFactory.createEmptyBorder());
        }
    }
}
