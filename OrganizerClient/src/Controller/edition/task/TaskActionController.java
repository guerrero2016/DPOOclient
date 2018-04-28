package Controller.edition.task;

import Controller.edition.EditionController;
import Controller.color.ColorPreviewController;
import ModelAEliminar.Tag;
import ModelAEliminar.Task;
import View.color.ColorChooserPanel;
import View.edition.task.TaskPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskActionController implements ActionListener {

    private final static String TAG_CREATION_TITLE = "Tag Color";

    private EditionController mainController;
    private TaskPanel view;
    private Task task;


    public TaskActionController(EditionController mainController, TaskPanel view, Task task) {
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
        view.setDescriptionEditable(false);
        view.setDescription(task.getDescription());
        view.setTaskNameEditable(false, task.getName());
        view.cleanNewTagName();
        mainController.showProjectContent();
    }

    private void taskNameManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setTaskNameEditable(true, task.getName());
        } else {
            if(view.isTaskNameEditable()) {
                view.setTaskNameEditable(false, view.getTaskName());
                task.setName(view.getTaskName());
                mainController.updatedTask(task);
                mainController.setEditingState(false);
            }
        }
    }

    private void deleteTask() {
        mainController.deleteTask();
        mainController.showProjectContent();
    }

    private void descriptionManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setDescriptionEditable(true);
        } else {
            if(view.isDescriptionEditable()) {
                view.setDescriptionEditable(false);
                task.setDescription(view.getDescription());
                mainController.updatedTask(task);
                mainController.setEditingState(false);
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

                if (colorPreviewController.getColor() != null) {
                    Tag tag = new Tag(view.getNewTagName(), colorPreviewController.getColor());
                    task.addTag(tag);
                    view.cleanNewTagName();
                    view.addTag(tag);
                    mainController.updatedTask(task);
                }

            }

        }
    }

}