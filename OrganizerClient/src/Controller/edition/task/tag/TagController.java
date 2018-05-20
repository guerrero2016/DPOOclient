package Controller.edition.task.tag;

import Controller.edition.color.ColorPreviewController;
import Controller.edition.EditionController;
import model.project.Tag;
import View.edition.color.ColorChooserPanel;
import View.edition.task.tag.TagEditionPanel;
import View.edition.task.tag.TagPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TagController implements ActionListener {

    private final static String TAG_REMOVE_TITLE = "Tag Remove";
    private final static String TAG_REMOVE_MESSAGE = "Do you want to remove";
    private final static String TAG_EDITION_TITLE = "Tag Edition";

    private EditionController mainController;
    private TagPanel view;
    private Tag tag;
    private JFrame dialogJFrame;
    private boolean isRemoving;

    public TagController(EditionController mainController, TagPanel view, Tag tag) {
        this.mainController = mainController;
        this.view = view;
        this.tag = tag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(TagPanel.ACTION_TAG_NAME_EDIT)) {
            manageTagEdit();
        } else if(e.getActionCommand().equals(TagPanel.ACTION_TAG_DELETE)) {
            tagDelete();
        }
    }

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
                    tag.setName(tagEditionPanel.getTagName());
                    tag.setColor(colorPreviewController.getColor());
                    mainController.editTagInDB(tag);
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

    public boolean isRemovingTag(Tag tag) {
        return this.tag.equals(tag) && dialogJFrame != null && isRemoving;
    }

    public boolean isEditingTag(Tag tag) {
        return this.tag.equals(tag) && dialogJFrame != null && !isRemoving;
    }

    public void closeDialog() {
        if(dialogJFrame != null) {
            dialogJFrame.dispose();
            dialogJFrame = null;
        }
    }

}