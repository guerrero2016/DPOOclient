package Controller;

import model.DataManager;
import model.project.Project;
import View.CustomProjectButton;
import View.MainView;
import View.ProjectBoxView;
import View.ProjectsMainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ProjectSelectionController implements MouseListener, ActionListener {

    private final MainViewController controller;
    //TODO: Project box panels (admin and shared)

    public ProjectSelectionController (MainViewController controller) {
        this.controller = controller;
        ArrayList<Project> projects = new ArrayList<>(Arrays.asList(controller.getProjects()));
        controller.createOwnerBoxProjects(projects);
        controller.createSharedBoxProjects(projects);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO: Recover project data from server and know if it is a project user or shared
        if(e.getClickCount() == 2) {
            ProjectBoxView projectBoxView = (ProjectBoxView) e.getSource();
            controller.loadProject(projectBoxView.getProject(), projectBoxView.isOwner());
            controller.swapPanel(MainView.PROJECT_ID);
        }
    }

    public void createProject (Project project) {
        controller.addOwnerProjectBox(project);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = ((JButton)e.getSource()).getActionCommand();

        switch (actionCommand){
            case ProjectBoxView.DELETE_AC:
                CustomProjectButton button = (CustomProjectButton) e.getSource();
                controller.removeProject(button.getProjectIndex());
                break;
            case ProjectsMainView.ADD_PROJECT_ACTION_COMMAND:
                createAddProjectWindow();
                break;
            case ProjectBoxView.INFO_AC:
                CustomProjectButton button2 = (CustomProjectButton) e.getSource();
                DataManager.getSharedInstance().setTitle(button2.getProjectName());
                //TODO demanar info al server
                //createProjectInfoWindow(button2.getProjectName());
        }
    }

    private void createAddProjectWindow () {
        final ProjectCreationController  projectCreationController =
                new ProjectCreationController(this);
        projectCreationController.createAddProjectView();
    }

    public void createProjectInfoWindow (String title, String memebers) {
        final String projectName = title;
        final ProjectInfoController projectInfoController = new ProjectInfoController();
        projectInfoController.createView(projectName, memebers);
    }
}
