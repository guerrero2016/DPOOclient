package View.project;

import Controller.project.ProjectCreationController;
import View.utils.CustomDialog;

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

    /**
     * Constructor per defecte
     */
    public AddProjectView () {
        colorsPanels = new ArrayList<>();

        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Name");
        nameTextField = new JTextField();

        northPanel.add(nameLabel, BorderLayout.NORTH);
        northPanel.add(nameTextField, BorderLayout.CENTER);

        JLabel colorLabel = new JLabel("Color");
        JPanel colorsPanel = new JPanel(new BorderLayout());
        JPanel palettePanel = createColorsPalette();
        colorsPanel.add(colorLabel, BorderLayout.NORTH);
        colorsPanel.add(palettePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        createButton = new JButton("Add");
        buttonPanel.add(createButton);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Create a new project"));
        leftPanel.add(buttonPanel,BorderLayout.SOUTH);
        leftPanel.add(northPanel, BorderLayout.NORTH);
        leftPanel.add(colorsPanel, BorderLayout.CENTER);

        JPanel joinProjectPanel = new JPanel(new BorderLayout());
        JLabel idLabel = new JLabel("Project's ID");
        idTextField = new JTextField();

        JPanel joinButtonPanel = new JPanel(new FlowLayout());
        joinButton = new JButton("Join");
        joinButtonPanel.add(joinButton);

        joinProjectPanel.add(idLabel, BorderLayout.NORTH);
        joinProjectPanel.add(idTextField, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Join a un project"));
        rightPanel.add(joinProjectPanel, BorderLayout.NORTH);
        rightPanel.add(joinButtonPanel, BorderLayout.SOUTH);

        this.setLayout(new GridLayout(1,2));
        this.add(leftPanel);
        this.add(rightPanel);

        northPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        colorsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        joinProjectPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        selectColor(colorsPanels.get(0));

        dialog = new CustomDialog("Add a project", this);
    }

    /**
     * Funcio que fa visible la vista en forma de Dialog. D'aquesta manera les altres pantalles queden
     * temporalment inactives
     * @param isVisible Estat
     */
    public void setDialogVisible(boolean isVisible) {
        dialog.setDialogVisible(isVisible);
    }

    /**
     * Getter del nom del projecte
     * @return Nom del projecte
     */
    public String getProjectName () {
        return nameTextField.getText();
    }

    /**
     * Getter del color del projecte
     * @return Color
     */
    public Color getProjectColor () {return selectedColor;}

    /**
     * Getter de la ID del projecte
     * @return ID
     */
    public String getProjectID () {
        return idTextField.getText();
    }

    /**
     * Funcio encarregada de crear la paleta de colors
     * @return JPanel que representa la paleta de colors
     */
    private JPanel createColorsPalette () {
        final JPanel palettePanel = new JPanel(new GridLayout(2,4));
        palettePanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        for(Color color: this.COLORS) {
            JPanel colorPanel = new JPanel();
            colorPanel.setBackground(color);
            palettePanel.add(colorPanel);
            colorsPanels.add(colorPanel);
        }

        return palettePanel;

    }

    /**
     * Metode encarregat de registrar el controlador de la vista
     * @param controller Controlador
     */
    public void registerMouseListener (ProjectCreationController controller) {
        for (JPanel colorPanel: colorsPanels) {
            colorPanel.addMouseListener(controller);
        }
        createButton.addActionListener(controller);
        joinButton.addActionListener(controller);
    }

    /**
     * Funcio encarregada de seleccionar un color
     * @param colorPanel Panell de colors
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
