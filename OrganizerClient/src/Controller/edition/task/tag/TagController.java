package Controller.edition.task.tag;

import Controller.edition.EditionController;
import ModelAEliminar.Tag;
import ModelAEliminar.Task;
import View.edition.ColorChooserPanel;
import View.edition.PalettePanel;
import View.edition.TagEditionPanel;
import View.edition.task.TagPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TagController implements ActionListener {

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
            if(!mainController.isEditing()) {

                TagEditionPanel tagEditionPanel = new TagEditionPanel(tag.getName());
                ColorChooserPanel colorChooserPanel = tagEditionPanel.getColorChooserPanel();
                ColorPreviewController colorPreviewController = new ColorPreviewController(colorChooserPanel);
                colorChooserPanel.getPalettePanel().registerActionController(colorPreviewController);
                int result = JOptionPane.showConfirmDialog(null, tagEditionPanel, TAG_EDITION_TITLE,
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {

                    tag.setName(tagEditionPanel.getTagName());
                    view.setTagName(tag.getName());

                    if(colorPreviewController.getCurrentColor() != null) {
                        tag.setColor(colorPreviewController.getCurrentColor());
                        view.setTagColor(tag.getColor());
                    }

                    mainController.updateTag(task, tag);

                }

            }
        } else if (e.getActionCommand().equals(TagPanel.ACTION_TAG_DELETE)) {
            if(!mainController.isEditing()) {
                mainController.removeTag(task, tag);
                task.removeTag(tag);
            }
        }
    }

}