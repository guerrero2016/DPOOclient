package Controller.edition.project;

import Controller.edition.EditionController;
import View.edition.project.ProjectPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectActionController implements ActionListener {

    private final static String EDITING_ON_MESSAGE = "You should finish editing before doing something else";
    private final static String EDITING_ON_TITLE = "Information";

    private EditionController mainController;
    private ProjectPanel view;

    public ProjectActionController(EditionController mainController, ProjectPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: ALL
        if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_EDIT_NAME)) {

        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_BACKGROUND)) {

        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_DELETE)) {

        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_CATEGORY_ADD)) {

        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_BACK)) {
            projectBackManagement();
        }
    }

    private void projectBackManagement() {
        if(!mainController.isEditing()) {
            mainController.showProjectSelection();
        } else {
            JOptionPane.showMessageDialog(null, EDITING_ON_MESSAGE, EDITING_ON_TITLE, JOptionPane.
                    WARNING_MESSAGE);
        }
    }

}