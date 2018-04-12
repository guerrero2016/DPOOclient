package Controller;

import View.MainView;

public class MainViewController {
    private MainView view;
    private LogInController logInController;
    private SignInController signInController;

    public MainViewController(MainView view) {
        this.view = view;
        logInController = new LogInController(this);
        signInController = new SignInController(this);
    }

    public void registerControllers(MainView view) {
        view.addControllerButton(logInController, signInController);
    }

    public void swapPanel (int whatPanel) {
        view.swapPanel(whatPanel);
    }



}
