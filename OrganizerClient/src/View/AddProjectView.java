package View;

import javax.swing.*;
import java.awt.*;

public class AddProjectView extends JFrame {

    private final JTextField nameTextField;
    private final Color[] COLORS = new Color[]{Color.RED, Color.BLUE, Color.CYAN, Color.YELLOW, Color.WHITE, Color.ORANGE, Color.green, Color.gray};

    public AddProjectView () {
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

        add(northPanel, BorderLayout.NORTH);
        add(colorsPanel, BorderLayout.CENTER);

        northPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        colorsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        setSize(300,300);
        setResizable(false);
        setTitle("Nou projecte");
        setVisible(true);
    }

    public JPanel createColorsPalette () {
        int margin = 10;
        final JPanel palettePanel = new JPanel(new GridLayout(2,4));
        palettePanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        for (Color color:this.COLORS) {
            JPanel colorPanel = new JPanel();
            colorPanel.setBackground(color);
            palettePanel.add(colorPanel);
        }

        return palettePanel;
    }

}
