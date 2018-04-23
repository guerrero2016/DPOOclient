package Controller.edition.task;

import Controller.edition.EditionController;
import View.edition.task.TagPanel;
import View.edition.task.TaskPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskActionController implements ActionListener {

    private EditionController mainController;
    private TaskPanel view;

    public TaskActionController(EditionController mainController, TaskPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: 7 actions click
        if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_BACK)) {

        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_EDIT_NAME)) {

        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_DELETE)) {

        } else if(e.getActionCommand().equals(TaskPanel.ACTION_DESCRIPTION_EDITION)) {

        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TAG_ADD)) {

        } else if(e.getActionCommand().equals(TagPanel.ACTION_TAG_NAME_EDIT)) {

        } else if(e.getActionCommand().equals(TagPanel.ACTION_TAG_DELETE)) {

        }
    }

}