package Controller.edition.task.tag;

import Controller.edition.color.ColorPreviewController;
import Controller.edition.EditionController;
import model.project.Tag;
import View.edition.color.ColorChooserPanel;
import View.edition.task.tag.TagEditionPanel;
import View.edition.task.tag.TagPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de gestionar els Action Event d'un TagPanel
 */
public class TagController implements ActionListener {

    private final static String TAG_REMOVE_TITLE = "Tag Remove";
    private final static String TAG_REMOVE_MESSAGE = "Do you want to remove";
    private final static String TAG_EDITION_TITLE = "Tag Edition";

    private EditionController mainController;
    private Tag tag;
    private JFrame dialogJFrame;
    private boolean isRemoving;

    /**
     * Constructor que requereix d'un controlador extern i de l'etiqueta origen
     * @param mainController Controlador extern
     * @param tag Etiqueta origen
     */
    public TagController(EditionController mainController, Tag tag) {
        this.mainController = mainController;
        this.tag = tag;
    }

    /**
     * Mètode encarregat de distingir la'cció detectada
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(TagPanel.ACTION_TAG_NAME_EDIT)) {
            manageTagEdit();
        } else if(e.getActionCommand().equals(TagPanel.ACTION_TAG_DELETE)) {
            tagDelete();
        }
    }

    /**
     * Mètode encarregat d'actualitzar una etiqueta
     */
    private void manageTagEdit() {
        if(!mainController.isEditing()) {
            //Create panel
            TagEditionPanel tagEditionPanel = new TagEditionPanel(tag.getName());
            ColorChooserPanel colorChooserPanel = tagEditionPanel.getColorChooserPanel();
            ColorPreviewController colorPreviewController = new ColorPreviewController(colorChooserPanel);
            colorChooserPanel.getPalettePanel().registerActionController(colorPreviewController);

            //Show panel
            dialogJFrame = new JFrame();
            dialogJFrame.setLocationRelativeTo(null);
            int result = JOptionPane.showConfirmDialog(null, tagEditionPanel, TAG_EDITION_TITLE,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            dialogJFrame = null;

            if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
                if (colorPreviewController.getColor() != null) {
                    Tag aux = new Tag(tag.getId(), tagEditionPanel.getTagName(), colorPreviewController.getColor());
                    mainController.editTagInDB(aux);
                } else {
                    Tag aux = new Tag(tag.getId(), tagEditionPanel.getTagName(), tag.getColor());
                    mainController.editTagInDB(aux);
                }
            }

        } else {
            dialogJFrame = new JFrame();
            dialogJFrame.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
            dialogJFrame = null;
        }
    }

    /**
     * Mètode encarregat d'eliminar l'etiqueta
     */
    private void tagDelete() {
        if(!mainController.isEditing()) {
            isRemoving = true;
            dialogJFrame = new JFrame();
            dialogJFrame.setLocationRelativeTo(null);
            int result = JOptionPane.showConfirmDialog(dialogJFrame, TAG_REMOVE_MESSAGE + " '" + tag.
                    getName() + "'?", TAG_REMOVE_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            dialogJFrame = null;
            isRemoving = false;

            if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
                mainController.removeTagInDB(tag);
            }
        }
    }

    /**
     * Mètode encarregat d'indicar si s'està eliminant l'etiqueta
     * @param tag Etiqueta a comparar
     * @return Si s'està eliminat
     */
    public boolean isRemovingTag(Tag tag) {
        return this.tag.equals(tag) && dialogJFrame != null && isRemoving;
    }

    /**
     * Mètode encarregat d'indicar si s'està modificant l'etiqueta
     * @param tag Etiqueta a comparar
     * @return Si s'està modificant
     */
    public boolean isEditingTag(Tag tag) {
        return this.tag.equals(tag) && dialogJFrame != null && !isRemoving;
    }

    /**
     * Mètode encarregat de tancar els dialog oberts pel controlador
     */
    public void closeDialog() {
        if(dialogJFrame != null) {
            dialogJFrame.dispose();
            dialogJFrame = null;
        }
    }

}