package Controller;

import View.MainView;

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

    public void addOwnerProjects () {

    }

    public void addSharedProjects () {
        
    }

}
