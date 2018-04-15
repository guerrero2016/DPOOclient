<<<<<<< HEAD
import Model.Category;
import Model.User;
import View.project.MemberView;
import View.project.ProjectView;
=======
import Controller.MainViewController;
import View.MainView;
>>>>>>> 7d9a79e12440da218480741cf96f3fd45d30198b

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /*ProjectsMainView projectsMainView = new ProjectsMainView();
        ProjectSelectionController projectSelectionController = new ProjectSelectionController(projectsMainView);
        projectsMainView.registerAddProjectViewController(projectSelectionController);
        projectsMainView.registerProjectSelectionController(projectSelectionController);*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //1. Crear tots els JPanels
                //2. Crear tots els controllers de cada JPanel
                //3. Registres els JPanels amb els controladors
                //4. Creem la MainView passant-li TOTS els JPanels
                //5. Creem el controlador general passant-li tots els controllers i la MainView
                //6. Finalment durant la creació del controlador gran se li ha de passar aquest a tots
                //   els petits controladors.

                MainView a = new MainView();
                MainViewController mainViewController = new MainViewController(a);
                mainViewController.registerControllers(a);
                a.setVisible(true);

            }
        });
    }

}