package View.edition.task.tag;

import model.project.Tag;
import View.edition.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe que representa una etiqueta a la vista.
 */
public class TagPanel extends JPanel {

    public final static String ACTION_TAG_NAME_EDIT = "TagNameEdit";
    public final static String ACTION_TAG_DELETE = "TagDelete";

    private final int PANEL_WIDTH = 150;
    private final int PANEL_HEIGHT = 50;

    private final static int MAX_TAG_LENGTH = 20;

    private final static String EDIT_BUTTON = "Edit";
    private final static String DELETE_BUTTON = "Delete";

    private final JLabel jlTagName;
    private final JButton jbTagEditor;
    private final JButton jbTagDelete;

    private ActionListener actionListener;

    /**
     * Crea el panell de la tasca amb els diferents botons i amb el nom de la tasca correcsponent.
     * @param editorIcon icona d'edició
     * @param deleteIcon icona d'eliminar
     * @param tag tag que representa
     */
    public TagPanel(Image editorIcon, Image deleteIcon, Tag tag) {

        //Panel config
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setTagColor(tag.getColor());

        //Buttons panel
        final TransparentPanel tpButtonsPanel = new TransparentPanel();
        tpButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(tpButtonsPanel, BorderLayout.PAGE_START);

        //Editor button
        if(editorIcon != null) {
            jbTagEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(12, 12,
                    Image.SCALE_SMOOTH)));
            jbTagEditor.setBorder(null);
        } else {
            jbTagEditor = new JButton(EDIT_BUTTON);
            jbTagEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 4));
        }

        jbTagEditor.setActionCommand(ACTION_TAG_NAME_EDIT);
        tpButtonsPanel.add(jbTagEditor);

        //Delete icon
        if(deleteIcon != null) {
            jbTagDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(12, 12,
                    Image.SCALE_SMOOTH)));
            jbTagDelete.setBorder(null);
        } else {
            jbTagDelete = new JButton(DELETE_BUTTON);
            jbTagDelete.setFont(new Font(Font.DIALOG, Font.BOLD, 4));
        }

        jbTagDelete.setActionCommand(ACTION_TAG_DELETE);
        tpButtonsPanel.add(jbTagDelete);

        //Tag name
        jlTagName = new JLabel();
        jlTagName.setHorizontalAlignment(JTextField.CENTER);
        jlTagName.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        jlTagName.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        setTagName(tag.getName());
        add(jlTagName, BorderLayout.CENTER);

    }

    /**
     * Procediment que assigna el nom de la tasca. Si és massa llarg l'escurça
     * @param tagName Nom de l'etiqueta
     */
    public void setTagName(String tagName) {
        if(tagName.length() <= MAX_TAG_LENGTH) {
            jlTagName.setText(tagName);
        } else {
            String shortTagName = tagName.substring(0, MAX_TAG_LENGTH) + "...";
            jlTagName.setText(shortTagName);
        }
    }

    /**
     * Procediment que assigna el color del panell.
     * @param tagColor color de la tasca que representa
     */
    public void setTagColor(Color tagColor) {
        setBackground(tagColor);
    }

    /**
     * Funció que recupera l'ActionListener
     * @return ActionListener del panell
     */
    public ActionListener getActionListener() {
        return actionListener;
    }

    /**
     * Procediment que reseteja el Listener
     */
    public void resetActionController() {
        jbTagEditor.addActionListener(actionListener);
        jbTagDelete.addActionListener(actionListener);
        actionListener = null;
    }

    /**
     * Procediment que registra un ActionListener als botons.
     * @param actionListener Controlador
     */
    public void registerActionController(ActionListener actionListener) {
        this.actionListener = actionListener;
        jbTagEditor.addActionListener(actionListener);
        jbTagDelete.addActionListener(actionListener);
    }

}