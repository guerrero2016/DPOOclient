package Controller.edition.task;

import Controller.edition.EditionController;
import Controller.edition.task.tag.ColorPreviewController;
import ModelAEliminar.Tag;
import ModelAEliminar.Task;
import View.edition.ColorChooserPanel;
import View.edition.task.TaskPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskController implements ActionListener {

    private final static String TAG_CREATION_TITLE = "Tag Color";

    private EditionController mainController;
    private TaskPanel view;
    private Task task;

    public TaskController(EditionController mainController, TaskPanel view, Task task) {
        this.mainController = mainController;
        this.view = view;
        this.task = task;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_BACK)) {
            closeTask();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_EDIT_NAME)) {
            taskNameManagement();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_DELETE)) {
            deleteTask();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_DESCRIPTION_EDITION)) {
            descriptionManagement();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TAG_ADD)) {
            addTag();
        }
    }

    private void closeTask() {
        view.setDescription(task.getDescription());
        view.setDescriptionEditable(false);
        view.setTaskNameEditable(false, task.getName());
        view.cleanNewTagName();
        mainController.showProjectContent();
    }

    private void taskNameManagement() {
        if(!mainController.isEditing()) {
            view.setTaskNameEditable(true, task.getName());
            mainController.setEditingState(true);
        } else {
            if(view.isTaskNameEditable()) {
                task.setName(view.getTaskName());
                view.setTaskNameEditable(false, task.getName());
                mainController.setEditingState(false);
            }
        }
    }

    private void deleteTask() {
        mainController.deleteTask(task);
    }

    private void descriptionManagement() {
        if(!mainController.isEditing()) {
            view.setDescriptionEditable(true);
            mainController.setEditingState(true);
        } else {
            if(view.isDescriptionEditable()) {
                task.setDescription(view.getDescription());
                view.setDescriptionEditable(false);
                mainController.setEditingState(false);
                mainController.updateTask(task);
            }
        }
    }

    private void addTag() {
        if(!mainController.isEditing() && !view.getNewTagName().isEmpty()) {

            ColorChooserPanel colorChooserPanel = new ColorChooserPanel();
            ColorPreviewController colorPreviewController = new ColorPreviewController(colorChooserPanel);
            colorChooserPanel.getPalettePanel().registerActionController(colorPreviewController);
            int result = JOptionPane.showConfirmDialog(null, colorChooserPanel, TAG_CREATION_TITLE,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {

                if (colorPreviewController.getCurrentColor() != null) {
                    Tag tag = new Tag(view.getNewTagName(), colorPreviewController.getCurrentColor());
                    view.cleanNewTagName();
                    view.addTag(tag);
                    task.addTag(tag);
                    mainController.addTag(task, tag);
                }

            }

        }
    }

}