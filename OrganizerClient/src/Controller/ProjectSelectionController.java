package Controller;

import Network.NetworkManager;
import View.*;
import model.project.Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjectSelectionController implements ActionListener {

    //TODO: Project box panels (admin and shared)
    final ProjectSelectionView view;
    NetworkManager networkManager;

    public ProjectSelectionController (ProjectSelectionView view) {
        this.view = view;
        //ArrayList<Project> projects = new ArrayList<>(Arrays.asList(controller.getProjects()));
       // controller.createOwnerBoxProjects(projects);
       //controller.createSharedBoxProjects(projects);
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public void createProject (Project project) {
        view.addProjectBox(project.getName(), project.getColor(), new ProjectBoxController(project, networkManager));
    }

    public void createProjects (ArrayList<Project> projects) {
        String [] titles = new String[projects.size()];
        Color[] colors = new Color[projects.size()];
        ProjectBoxController [] controllers = new ProjectBoxController[projects.size()];
        for (int i = 0; i<projects.size(); i++) {
            titles[i] = projects.get(i).getName();
            colors[i] = projects.get(i).getColor();
            controllers[i] = new ProjectBoxController(projects.get(i), networkManager);
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
            default:

        }
    }

    private void createAddProjectWindow () {
        final ProjectCreationController  projectCreationController =
                new ProjectCreationController(this);
        projectCreationController.createAddProjectView();
    }
}
