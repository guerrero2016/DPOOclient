package Controller;

import View.*;
import model.DataManager;
import model.project.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ProjectSelectionController implements MouseListener, ActionListener {

    //TODO: Project box panels (admin and shared)
    final ProjectSelectionView view;

    public ProjectSelectionController (ProjectSelectionView view) {
        this.view = view;
        //ArrayList<Project> projects = new ArrayList<>(Arrays.asList(controller.getProjects()));
       // controller.createOwnerBoxProjects(projects);
       //controller.createSharedBoxProjects(projects);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO: Recover project data from server and know if it is a project user or shared
        if(e.getClickCount() == 2) {
            ProjectBoxView projectBoxView = (ProjectBoxView) e.getSource();
            //controller.loadProject(projectBoxView.getProject(), projectBoxView.isOwner());
            //controller.swapPanel(MainView.PROJECT_ID);
        }
    }

    public void createProject (Project project) {
        view.addProjectBox(project);
    }

    public void createProjects (ArrayList<Project> projects) {
        view.createProjectBoxes(projects);
    }

    public void deleteProject (int index) {
        view.removeProject(index);
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
                deleteProject(button.getProjectIndex());
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
