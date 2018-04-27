package Controller.edition.task;

import Controller.edition.EditionController;
import Controller.edition.task.tag.PickColorController;
import ModelAEliminar.Tag;
import ModelAEliminar.Task;
import View.edition.ColorChooserPanel;
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
        view.setDescription(task.getDescription());
        view.setDescriptionEditable(false);
        view.setTaskNameEditable(false, task.getName());
        view.cleanNewTagName();
        mainController.showProjectContent();
    }

    private void descriptionManagement() {

        if(view.isDescriptionEditable()) {
            task.setDescription(view.getDescription());
            mainController.updateTask(task);
        }

        view.setDescriptionEditable(!view.isDescriptionEditable());

    }

    private void taskNameManagement() {
        if(view.isTaskNameEditable()) {
            task.setName(view.getTaskName());
            view.setTaskNameEditable(!view.isTaskNameEditable(), task.getName());
        } else {
            view.setTaskNameEditable(!view.isTaskNameEditable(), task.getName());
        }
    }

    private void deleteTask() {
        mainController.deleteTask(task);
    }

    private void addTag() {
        if(!view.getNewTagName().isEmpty()) {

            ColorChooserPanel colorChooserPanel = new ColorChooserPanel();
            PickColorController pickColorController = new PickColorController(colorChooserPanel);
            colorChooserPanel.registerActionController(pickColorController);
            int result = JOptionPane.showConfirmDialog(null, colorChooserPanel, TAG_CREATION_TITLE,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {

                if (pickColorController.getCurrentColor() != null) {
                    Tag tag = new Tag(view.getNewTagName(), pickColorController.getCurrentColor());
                    view.cleanNewTagName();
                    view.addTag(tag);
                    task.addTag(tag);
                    mainController.addTag(task, tag);
                }

            }

        }
    }

}