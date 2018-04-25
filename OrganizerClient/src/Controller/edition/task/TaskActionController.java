package Controller.edition.task;

import Controller.edition.EditionController;
import ModelAEliminar.Task;
import View.edition.task.TaskPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskActionController implements ActionListener {

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
            //TODO: Add tag
        }
    }

    private void closeTask() {
        view.setDescription(task.getDescription());
        view.setDescriptionEditable(false);
        view.setTaskNameEditable(false, task.getName());
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

}