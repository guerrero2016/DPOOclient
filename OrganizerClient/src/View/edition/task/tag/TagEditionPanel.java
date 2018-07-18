package View.edition.task.tag;

import View.utils.color.ColorChooserPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa el panell d'edicio d'etiquetes
 */
public class TagEditionPanel extends JPanel {

    private final static String TAG_NAME_TITLE = "Tag Name";

    private final JTextField jtfTagName;
    private final ColorChooserPanel colorChooserPanel;

    /**
     * Crea un el panell i assigna el nom de l'etiqueta
     * @param tagName Nom de l'etiqueta a editar
     */
    public TagEditionPanel(String tagName) {

        //Main config
        setLayout(new BorderLayout());

        //Tag name panel
        final JPanel jpTagName = new JPanel(new BorderLayout());
        jpTagName.setBorder(BorderFactory.createTitledBorder(TAG_NAME_TITLE));
        add(jpTagName, BorderLayout.PAGE_START);

        //Tag name
        jtfTagName = new JTextField(tagName);
        jtfTagName.setEditable(true);
        jpTagName.add(jtfTagName, BorderLayout.CENTER);

        //Color chooser panel
        colorChooserPanel = new ColorChooserPanel();
        add(colorChooserPanel, BorderLayout.CENTER);

    }

    /**
     * Funcio que recupera el nom de l'etiqueta
     * @return Nom de l'etiqueta
     */
    public String getTagName() {
        return jtfTagName.getText();
    }

    /**
     * Funcio que recupera el color escollit
     * @return Color escollit
     */
    public ColorChooserPanel getColorChooserPanel() {
        return colorChooserPanel;
    }

}