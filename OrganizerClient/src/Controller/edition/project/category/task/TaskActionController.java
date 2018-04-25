package Controller.edition.project.category.task;

import Controller.edition.EditionController;
import Model.Task;
import View.edition.project.category.task.TaskPanel;

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
        //TODO: 5 actions click
        if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_BACK)) {
            mainController.showProjectContent();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_EDIT_NAME)) {
            taskNameManagement();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_DELETE)) {
            mainController.deleteTask(task);
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_DESCRIPTION_EDITION)) {
            descriptionManagement();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TAG_ADD)) {

        }
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

}