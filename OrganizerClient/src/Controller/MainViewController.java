package Controller;

import Controller.edition.EditionController;
import ModelAEliminar.*;
import View.MainView;
import View.edition.BackgroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainViewController {

    private MainView view;
    private LogInController logInController;
    private SignInController signInController;
    private ProjectSelectionController projectSelectionController;
    private EditionController editionController;

    public MainViewController(MainView view) {
        this.view = view;
        logInController = new LogInController(this);
        signInController = new SignInController(this);
        projectSelectionController = new ProjectSelectionController(this);
        editionController = new EditionController(this, view.getEditionPanel());
    }

    public void registerControllers(MainView view) {
        view.addControllerButton(logInController, signInController);
        view.addProjectSelectionController(projectSelectionController);
    }

    public void swapPanel (int whatPanel) {
        view.swapPanel(whatPanel);
    }

    public void createOwnerBoxProjects (String [] titles, Color [] colors) {
        view.createOwnerBoxProjects(titles, colors);
    }

    public void createSharedBoxProjects (String [] titles, Color [] colors) {
        view.createSharedBoxProjects(titles, colors);
    }

    public void addOwnerProjectBox(String title, Color color) {
        view.addOwnerProjectBox(title, color);
    }

    public void addSharedProjectBox(String title, Color color) {
        view.addSharedProjectBox(title, color);
    }

    public void removeProject(int index) {
        view.removeProjectAtIndex(index);
    }

    public void createProjectInfoWindow (String title, String memebers) {
        projectSelectionController.createProjectInfoWindow(title, memebers);
    }

    public void loadProject(Project project, boolean isAdmin) {

        //TODO: Delete
        Project newProject = new Project("Project Name");

        try {
            Image image = ImageIO.read(new File("img/background4.jpg"));
            newProject.setBackground(image);
        } catch(IOException e) {
            //Could not load img
        }


        for(int i = 0; i < 4; i++) {

            Category category = new Category("Category " + (i + 1));

            for(int j = 0; j < 20; j++) {

                Task task = new Task();
                task.setName("Task " + (j + 1));
                task.setDescription("Description " + (j + 1));

                for(int k = 0; k < 30; k++) {
                    task.addTag(new Tag((k + 1) + " Tag very long to handle", Color.CYAN));
                    task.addTag(new Tag("Tag " + (k + 1), Color.GREEN));
                }

                for(int k = 0; k < 10; k++) {
                    task.addUser(new User("User " + (k + 1), "Email " + (k + 1)));
                }

                category.addTask(task);

            }

            newProject.addCategory(category);

        }

        for(int i = 0; i < 10; i++) {
            newProject.addUser(new User("User " + (i + 1), "Email " + (i + 1)));
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

}
