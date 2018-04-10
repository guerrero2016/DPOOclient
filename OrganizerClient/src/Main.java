import Controller.LogInController;
import Controller.MainViewController;
import Controller.ProjectSelectionController;
import Controller.SignInController;
import View.MainView;
import View.ProjectSelectionView;
import View.ProjectsMainView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ProjectsMainView projectsMainView = new ProjectsMainView();
        ProjectSelectionController projectSelectionController = new ProjectSelectionController(projectsMainView);
        projectsMainView.registerAddProjectViewController(projectSelectionController);
        projectsMainView.registerProjectSelectionController(projectSelectionController);

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