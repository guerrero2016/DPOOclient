package View.project;

import Controller.project.ProjectBoxController;
import View.utils.CustomProjectButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Classe que representa una caixa d'un projecte
 */
public class ProjectBoxView extends JPanel {

    private static final String DELETE_AC = "DELETE";

    private final int HEIGHT = 80;  //50
    private final int WIDTH = 180;  //180
    private final int MAX_CHARS = 10;
    private final static String DELETE_ICON = "img/delete_icon.png";

    private String title;
    private CustomProjectButton jbDelete;
    private ActionListener controller;

    /**
     * Constructor que requereix de diversos parametres que especifiquen com es la caixa
     * @param title Titol
     * @param color Color
     * @param index Index del projecte
     * @param isOwner Si es un projecte del propietari
     * @param controller Controlador
     */
    public ProjectBoxView (String title, Color color, int index, boolean isOwner, ProjectBoxController controller) {
        setLayout(new BorderLayout());

        this.title  = title;

        JPanel jpLabel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(configureLabelMaxTextWidth(title));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(HEIGHT / 2 - 5, WIDTH / 2,
                HEIGHT / 2 + 5,WIDTH / 2));
        jpLabel.add(titleLabel, BorderLayout.EAST);

        Image deleteImage = null;
        try {
            deleteImage = ImageIO.read(new File(DELETE_ICON)).
                    getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel jpAux = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        jbDelete = new CustomProjectButton(new ImageIcon(deleteImage), title, index);

        if (isOwner) {
            jbDelete.setBackground(color);
            jbDelete.setBorder(null);
            jbDelete.setActionCommand(DELETE_AC);
            jpAux.add(jbDelete);
        }

        jpAux.setBackground(color);

        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(color);
        jpLabel.setBackground(color);
        this.add(jpAux, BorderLayout.NORTH);
        this.add(jpLabel,BorderLayout.CENTER);

        addMouseListener(controller);

    }

    /**
     * Getter del titol
     * @return Titol
     */
    public String getTitle() {
        return title;
    }

    /**
     * Metode encarregat de registrar el controlador
     * @param controller Controlador
     */
    public void registerButtonListener (ActionListener controller){
        this.controller = controller;
        jbDelete.addActionListener(controller);
    }

    /**
     * Getter del controlador
     * @return
     */
    public ActionListener getController() {
        return controller;
    }

    /**
     * Funcio que detecta si un text es molt llarg. Si ho es, subtitueix els ultims caracters per "..."
     * @param text Text
     * @return Text amb la mida correcta
     */
    private String configureLabelMaxTextWidth (String text) {
        if (text.length() > MAX_CHARS) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(text.substring(0, MAX_CHARS - 4)).append("...");
            return stringBuilder.toString();
        }
        return text;
    }

}