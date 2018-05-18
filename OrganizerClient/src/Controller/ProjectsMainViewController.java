package Controller;

import Network.NetworkManager;
import View.ProjectsMainView;
import model.project.Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjectsMainViewController implements ActionListener {

    private final ProjectsMainView view;
    private final ProjectSelectionController ownerSelectionController;
    private final ProjectSelectionController sharedSelectionController;
    private final ProjectCreationController projectCreationController;
    private MainViewController controller;

    public ProjectsMainViewController(ProjectsMainView view, ProjectSelectionController ownerSelectionController,
                                      ProjectSelectionController sharedSelectionController) {
        this.view = view;
        this.ownerSelectionController = ownerSelectionController;
        this.sharedSelectionController = sharedSelectionController;
        ownerSelectionController.createProjects(new ArrayList<>());
        sharedSelectionController.createProjects(new ArrayList<>());
        projectCreationController = new ProjectCreationController(ownerSelectionController);
    }

    public void resetOwnerProjects () {
        ownerSelectionController.resetProjectViews();
    }

    public void resetSharedProjects () {
        sharedSelectionController.resetProjectViews();
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

    public void setController(MainViewController controller) {
        this.controller = controller;
        ownerSelectionController.setController(controller);
        sharedSelectionController.setController(controller);
    }

    public void addOwnerProject (Project project) {
        ownerSelectionController.addProject(project);
    }

    public void addSharedProject (Project project) {
        sharedSelectionController.addProject(project);
    }

    public void deleteOwnerProject (int index) {
        ownerSelectionController.deleteProject(index);
    }

    public void deleteSharedProject (int index) {
        sharedSelectionController.deleteProject(index);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ProjectsMainView.ADD_PROJECT_ACTION_COMMAND:
                projectCreationController.createAddProjectView();
                break;
            case ProjectsMainView.LOG_OUT_ACTION_COMMAND:
                ownerSelectionController.resetProjectViews();
                break;
            default:
        }
    }
}

