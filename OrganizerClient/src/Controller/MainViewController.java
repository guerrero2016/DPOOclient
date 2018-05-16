package Controller;

import Controller.edition.EditionController;
import Model.project.Category;
import Model.project.Project;
import Model.project.Tag;
import Model.project.Task;
import Model.user.User;
import View.MainView;
import Network.NetworkManager;
import Model.ServerObjectType;

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
    private ProjectSelectionController projectSelectionController;
    private EditionController editionController;

//    public MainViewController(MainView view, NetworkManager network,) {
//        this.view = view;
//        this.network = network;
//        logInController = new LogInController(this);
//        signInController = new SignInController(this);
//        projectSelectionController = new ProjectSelectionController(this);
//        editionController = new EditionController(this, view.getEditionPanel());
//    }

    public MainViewController(MainView view, LogInController logInController, SignInController signInController) {
        this.view = view;
        this.logInController = logInController;
        this.signInController = signInController;
    }

    public void setNetwork(NetworkManager network) {
        this.network = network;
    }

    public void setControllerCommunication() {
        logInController.setController(this);
        signInController.setController(this);
    }

    public void registerControllers(MainView view) {
        view.addControllerButton(logInController, signInController);
        view.addProjectSelectionController(projectSelectionController);
    }

    public void swapPanel(int whatPanel) {
        view.swapPanel(whatPanel);
    }

    public void createOwnerBoxProjects(ArrayList<Project> projects) {
        if(projects != null) {
            view.createOwnerBoxProjects(projects);
        }
    }

    public void createSharedBoxProjects(ArrayList<Project> projects) {
        view.createSharedBoxProjects(projects);
    }

    public void addOwnerProjectBox(Project project) {
        view.addOwnerProjectBox(project);
    }

    public void addSharedProjectBox(Project project) {
        view.addSharedProjectBox(project);
    }

    public void removeProject(int index) {
        view.removeProjectAtIndex(index);
    }

    public void createProjectInfoWindow (String title, String memebers) {
        projectSelectionController.createProjectInfoWindow(title, memebers);
    }

    public Project[] getProjects() {

        Project[] projects = new Project[10];

        for(int i = 0; i < 10; i++) {
            Project project = new Project(String.valueOf(i), "Name " + i, Color.CYAN, i % 2 == 0);
        }

        return projects;

    }

    public void loadProject(Project project, boolean isAdmin) {

        //TODO: Delete
        Project newProject = new Project();
        newProject.setName("Project Name");

        try {
            Image image = ImageIO.read(new File("img/background4.jpg"));
            newProject.setBackground(image);
        } catch(IOException e) {
            //Could not load img
        }


        for(int i = 0; i < 4; i++) {

            Model.project.Category category = new Model.project.Category("Category " + (i + 1));

            for(int j = 0; j < 20; j++) {

                Task task = new Task();
                task.setName("Task " + (j + 1));
                task.setDescription("Description " + (j + 1));

                for(int k = 0; k < 30; k++) {
                    task.addTag(new Tag((k + 1) + " Tag very long to handle", Color.CYAN));
                    task.addTag(new Tag("Tag " + (k + 1), Color.GREEN));
                }

                for(int k = 0; k < 10; k++) {
                    task.addUser(new User("User " + (k + 1)));
                }

                category.addTask(task);

            }

            newProject.setCategory(category);

        }

        for(int i = 0; i < 10; i++) {
            newProject.addUser(new User("User " + (i + 1)));
        }

        //TODO: End delete

        //TODO: Replace add and delete taskContent
        editionController.loadProject(newProject, isAdmin);
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

}
