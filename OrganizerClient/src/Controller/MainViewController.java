package Controller;

import Model.Project;
import View.MainView;

import java.awt.*;

public class MainViewController {
    private MainView view;
    private LogInController logInController;
    private SignInController signInController;
    private ProjectSelectionController projectSelectionController;

    public MainViewController(MainView view) {
        this.view = view;
        logInController = new LogInController(this);
        signInController = new SignInController(this);
        projectSelectionController = new ProjectSelectionController(this);
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
}
