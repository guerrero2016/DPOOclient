package Controller;

import Controller.edition.EditionController;
import model.project.Category;
import model.project.Project;
import model.project.Tag;
import model.project.Task;
import model.user.User;
import View.MainView;
import Network.NetworkManager;
import model.ServerObjectType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainViewController {

    private MainView view;
    private NetworkManager network;
    private LogInController logInController;
    private SignInController signInController;
    private EditionController editionController;
    final private ProjectsMainViewController projectsMainViewController;
//    public MainViewController(MainView view, NetworkManager network,) {
//        this.view = view;
//        this.network = network;
//        logInController = new LogInController(this);
//        signInController = new SignInController(this);
//        projectSelectionController = new ProjectSelectionController(this);
//        editionController = new EditionController(this, view.getEditionPanel());
//    }

    public MainViewController(MainView view, LogInController logInController, SignInController signInController,
                              ProjectsMainViewController projectsMainViewController, EditionController editionController) {
        this.view = view;
        this.logInController = logInController;
        this.signInController = signInController;
        this.projectsMainViewController = projectsMainViewController;
        this.editionController = editionController;
    }

    public ProjectsMainViewController getProjectsMainViewController() {
        return projectsMainViewController;
    }

    public void setNetwork(NetworkManager network) {
        //projectsMainViewController.setNetworkManager(network);
        this.network = network;
    }

    public void setControllerCommunication() {
        logInController.setController(this);
        signInController.setController(this);
    }

    public void registerControllers(MainView view) {
        view.addControllerButton(logInController, signInController);
    }

    public void swapPanel(int whatPanel) {
        view.swapPanel(whatPanel);
    }

    public Project[] getProjects() {

        Project[] projects = new Project[10];

        for(int i = 0; i < 10; i++) {
            Project project = new Project(String.valueOf(i), "Name " + i, Color.CYAN, i % 2 == 0);
        }

        return projects;

    }

    public void loadProject(Project project) {
        editionController.loadProject(project);
        editionController.showProjectContent();
    }

    public void updateProject(Project project) {
        //TODO: Update project from database
    }

    public void updateCategory(Project project, Category category) {
        //TODO: Update category from database
    }

    public void updateTask(Project project, Category category, Task task) {
        //TODO: Update task from database
    }

    public void deleteProject() {
        //TODO: Remove user from project
    }

    public User getUserFromDB(String userName) {
        //TODO: Check if user exists in the database
        return new User(userName);
    }

    public void shareProject(Project project, User user) {
        //TODO: Share project
    }

    public void sendToServer(ServerObjectType type, Object o) throws IOException {
        network.sendToServer(type, o);
    }

    public void showDialog(String errorMSG) {
        view.showErrorDialog(errorMSG);
    }

}
