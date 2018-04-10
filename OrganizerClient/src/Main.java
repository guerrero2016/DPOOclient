import Controller.LogInController;
import Controller.MainViewController;
import Controller.ProjectSelectionController;
import Controller.SignInController;
import View.MainView;
import View.ProjectSelectionView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ProjectSelectionView projectSelectionView = new ProjectSelectionView();
        ProjectSelectionController projectSelectionController = new ProjectSelectionController(projectSelectionView);
        projectSelectionView.registerController(projectSelectionController);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                /*MainView a = new MainView();
                MainViewController mainViewController = new MainViewController(a);
                LogInController logInController = new LogInController(mainViewController);
                SignInController signInController = new SignInController(mainViewController);
                a.addControllerButton(logInController, signInController);*/
            }
        });
    }

}