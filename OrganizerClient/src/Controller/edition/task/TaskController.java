package Controller.edition.task;

import Controller.edition.EditionController;
import Controller.edition.color.ColorPreviewController;
import ModelAEliminar.Tag;
import ModelAEliminar.Task;
import View.edition.color.ColorChooserPanel;
import View.edition.task.TaskPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskController implements ActionListener {

    private final static String TASK_DELETE_MESSAGE = "Do you want to delete";
    private final static String TASK_DELETE_TITLE = "Task Delete";
    private final static String EDITING_ON_MESSAGE = "You should finish editing before doing something else";
    private final static String EDITING_ON_TITLE = "Information";
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
            } else {
                JOptionPane.showMessageDialog(null, EDITING_ON_MESSAGE, EDITING_ON_TITLE, JOptionPane.
                        WARNING_MESSAGE);
            }
        }
    }

    private void deleteTask() {

        int result = JOptionPane.showConfirmDialog(null,TASK_DELETE_MESSAGE + " '" +
                task.getName() + "'?", TASK_DELETE_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
            mainController.deleteTask();
            mainController.showProjectContent();
        }

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
            } else {
                JOptionPane.showMessageDialog(null, EDITING_ON_MESSAGE, EDITING_ON_TITLE, JOptionPane.
                        WARNING_MESSAGE);
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
                    mainController.updateTaskList();
                    view.cleanNewTagName();
                    view.addTag(tag);
                    mainController.updatedTask(task);
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, EDITING_ON_MESSAGE, EDITING_ON_TITLE, JOptionPane.
                    WARNING_MESSAGE);
        }
    }

}