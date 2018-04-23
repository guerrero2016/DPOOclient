package Controller;

import Controller.edition.EditionController;
import Model.*;
import View.MainView;

import javax.imageio.ImageIO;
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
        try {
            editionController = new EditionController(this, view.getEditionPanel());
        } catch(IOException e) {
            e.printStackTrace();
        }
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

    public void loadProject(Project project) {

        //TODO: Delete
        Project newProject = new Project("Project Name");

        try {
            newProject.setBackground(ImageIO.read(new File("img" + System.getProperty("file.separator") + "background4.jpg")));
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
                    task.addTag(new Tag("Tag " + (k + 1), Color.CYAN));
                    task.addTag(new Tag("Tag " + (k + 1), Color.GREEN));
                }

                for(int k = 0; k < 10; k++) {
                    task.addUser(new User("User " + (k + 1), "Email " + (k + 1), "Password " + (k + 1)));
                }

                category.addTask(task);

            }

            newProject.addCategory(category);

        }

        for(int i = 0; i < 10; i++) {
            newProject.addUser(new User("User " + (i + 1), "Email " + (i + 1), "Password " + (i + 1)));
        }

        //TODO: End delete

        //TODO: Replace project and delete taskContent
        editionController.setProjectContent(newProject);
//        editionController.setTaskContent(newProject.getCategory(0).getTask(0));

    }

}
