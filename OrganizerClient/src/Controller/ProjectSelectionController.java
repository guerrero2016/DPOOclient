package Controller;

import View.*;
import model.DataManager;
import model.project.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ProjectSelectionController implements ActionListener {

    //TODO: Project box panels (admin and shared)
    final ProjectSelectionView view;

    public ProjectSelectionController (ProjectSelectionView view) {
        this.view = view;
        //ArrayList<Project> projects = new ArrayList<>(Arrays.asList(controller.getProjects()));
       // controller.createOwnerBoxProjects(projects);
       //controller.createSharedBoxProjects(projects);
    }


    public void createProject (Project project) {
        view.addProjectBox(project.getName(), project.getColor(), new ProjectBoxController(project));
    }

    public void createProjects (ArrayList<Project> projects) {
        String [] titles = new String[projects.size()];
        Color[] colors = new Color[projects.size()];
        ProjectBoxController [] controllers = new ProjectBoxController[projects.size()];
        for (int i = 0; i<projects.size(); i++) {
            titles[i] = projects.get(i).getName();
            colors[i] = projects.get(i).getColor();
            controllers[i] = new ProjectBoxController(projects.get(i));
        }
        view.createProjectBoxes(titles, colors, controllers);
    }

    public void deleteProject (int index) {
        view.removeProject(index);
    }

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
