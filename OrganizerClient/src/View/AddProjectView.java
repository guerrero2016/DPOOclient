package View;

import Controller.ProjectCreationController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe que representa la finestra d'afegir un nou projecte
 */
public class AddProjectView extends JPanel {

    private final JTextField nameTextField;
    private final JTextField idTextField;
    private final Color[] COLORS = new Color[]{Color.RED, Color.BLUE, Color.CYAN, Color.YELLOW, Color.WHITE, Color.ORANGE, Color.green, Color.gray};
    private ArrayList<JPanel> colorsPanels;
    private final JButton createButton;
    private final JButton joinButton;
    private final CustomDialog dialog;
    private Color selectedColor;

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

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Crear nou projecte"));
        leftPanel.add(buttonPanel,BorderLayout.SOUTH);
        leftPanel.add(northPanel, BorderLayout.NORTH);
        leftPanel.add(colorsPanel, BorderLayout.CENTER);

        JPanel joinProjectPanel = new JPanel(new BorderLayout());
        JLabel idLabel = new JLabel("ID projecte");
        idTextField = new JTextField();

        JPanel joinButtonPanel = new JPanel(new FlowLayout());
        joinButton = new JButton("Unir-se");
        joinButtonPanel.add(joinButton);

        joinProjectPanel.add(idLabel, BorderLayout.NORTH);
        joinProjectPanel.add(idTextField, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Unir-se a un projecte"));
        rightPanel.add(joinProjectPanel, BorderLayout.NORTH);
        rightPanel.add(joinButtonPanel, BorderLayout.SOUTH);

        this.setLayout(new GridLayout(1,2));
        this.add(leftPanel);
        this.add(rightPanel);

        northPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        colorsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        joinProjectPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        selectColor(colorsPanels.get(0));

        dialog = new CustomDialog("Afegir projecte", this);
    }

    /**
     * Funcio que va visible la vista en forma de Dialog. D'aquesta manera les altres pantalles queden
     * temporalment inactives.
     * @param isVisible
     */
    public void setDialogVisible(boolean isVisible) {
        dialog.setDialogVisible(isVisible);
    }

    public String getProjectName () {
        return nameTextField.getText();
    }
    public Color getProjectColor () {return selectedColor;}

    public String getProjectID () {
        return idTextField.getText();
    }

    /**
     * Funcio encarregada de crear la paleta de colors
     * @return JPanel que representa la paleta de colors
     */
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
        joinButton.addActionListener(controller);
    }

    /**
     * Funcio encarregada de seleccionar un color
     * @param colorPanel
     */
    public void selectColor (JPanel colorPanel) {
        deselectAllColors();
        colorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        selectedColor = colorPanel.getBackground();
    }

    /**
     * Funcio que deselecciona tots els colors
     */
    private void deselectAllColors () {
        for (JPanel colorPanel:colorsPanels) {
            colorPanel.setBorder(BorderFactory.createEmptyBorder());
        }
    }
}
