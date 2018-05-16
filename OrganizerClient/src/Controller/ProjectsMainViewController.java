package Controller;

import Network.NetworkManager;
import View.ProjectsMainView;
import model.project.Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjectsMainViewController implements ActionListener {

    final ProjectsMainView view;
    final ProjectSelectionController ownerSelectionController;
    final ProjectSelectionController sharedSelectionController;
    final ProjectCreationController projectCreationController;
    NetworkManager networkManager;

    public ProjectsMainViewController(ProjectsMainView view, ProjectSelectionController ownerSelectionController, ProjectSelectionController sharedSelectionController) {
        this.view = view;
        this.ownerSelectionController = ownerSelectionController;
        this.sharedSelectionController = sharedSelectionController;
        ownerSelectionController.createProjects(new ArrayList<>());
        sharedSelectionController.createProjects(new ArrayList<>());
        projectCreationController = new ProjectCreationController(ownerSelectionController);
    }

    public ProjectSelectionController getOwnerSelectionController() {
        return ownerSelectionController;
    }

    public ProjectSelectionController getSharedSelectionController() {
        return sharedSelectionController;
    }

    public void createOwnerProjects (ArrayList<Project> projects) {
        ownerSelectionController.createProjects(projects);
    }

    public void createSharedProjects (ArrayList<Project> projects) {
        sharedSelectionController.createProjects(projects);
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
        ownerSelectionController.setNetworkManager(networkManager);
        sharedSelectionController.setNetworkManager(networkManager);
        projectCreationController.setNetworkManager(networkManager);
    }

    public void addOwnerProject (Project project) {
        ownerSelectionController.createProject(project);
    }

    public void addSharedProject (Project project) {
        sharedSelectionController.createProject(project);
    }

    public void deleteOwnerProject (int index) {
        ownerSelectionController.deleteProject(index);
    }

    public void deleteSharedProject (int index) {
        sharedSelectionController.deleteProject(index);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        projectCreationController.createAddProjectView();
    }
}

