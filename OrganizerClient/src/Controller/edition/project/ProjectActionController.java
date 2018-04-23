package Controller.edition.project;

import Controller.edition.EditionController;
import View.edition.project.CategoryPanel;
import View.edition.project.ProjectPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectActionController implements ActionListener {

    private EditionController mainController;
    private ProjectPanel view;

    public ProjectActionController(EditionController mainController, ProjectPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: 9 actions click
        if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_EDIT_NAME)) {

        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_BACKGROUND)) {

        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_DELETE)) {

        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_CATEGORY_ADD)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_EDIT_NAME)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_LEFT)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_RIGHT)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_DELETE)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_TASK_ADD)) {

        }
    }

}