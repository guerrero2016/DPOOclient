package Controller.edition.task.tag;

import Controller.edition.color.ColorPreviewController;
import Controller.edition.EditionController;
import model.project.Tag;
import model.project.Task;
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
    private Task task;
    private Tag tag;

    public TagController(EditionController mainController, TagPanel view, Task task, Tag tag) {
        this.mainController = mainController;
        this.view = view;
        this.task = task;
        this.tag = tag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(TagPanel.ACTION_TAG_NAME_EDIT)) {
            manageTagNameEdit();
        } else if(e.getActionCommand().equals(TagPanel.ACTION_TAG_DELETE)) {
            tagDelete();
        }
    }

    private void manageTagNameEdit() {
        if(!mainController.isEditing()) {

            //Create panel
            TagEditionPanel tagEditionPanel = new TagEditionPanel(tag.getName());
            ColorChooserPanel colorChooserPanel = tagEditionPanel.getColorChooserPanel();
            ColorPreviewController colorPreviewController = new ColorPreviewController(colorChooserPanel);
            colorChooserPanel.getPalettePanel().registerActionController(colorPreviewController);

            //Show panel
            int result = JOptionPane.showConfirmDialog(null, tagEditionPanel, TAG_EDITION_TITLE,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {

                tag.setName(tagEditionPanel.getTagName());

                if (colorPreviewController.getColor() != null) {
                    tag.setColor(colorPreviewController.getColor());
                }

                view.setTagName(tag.getName());
                view.setTagColor(tag.getColor());
                mainController.updatedTask(task);

            }

        } else {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }
    }

    private void tagDelete() {
        if(!mainController.isEditing()) {

            int result = JOptionPane.showConfirmDialog(null, TAG_REMOVE_MESSAGE + " '" + tag.
                    getName() + "'?", TAG_REMOVE_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
                mainController.removeTag(tag);
                task.removeTag(tag);
                mainController.updatedTask(task);
            }

        }
    }

}