import Controller.MainViewController;
import Model.ServerObjectType;
import Model.project.Category;
import Model.project.Project;
import Network.NetworkManager;
import View.MainView;

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
                //6. Finalment durant la creació del controlador gran se li ha de passar aquest TaskAddUserController tots
                //   els petits controladors.

                     MainView a = new MainView();
                     MainViewController mainViewController = new MainViewController(a);
                     mainViewController.registerControllers(a);
                     a.setVisible(true);

                NetworkManager nm = new NetworkManager(mainViewController);
                //    public Project(String id, String name, String color, ArrayList<Category> categories, ArrayList<String> membersName, String background) {
                try {
                    nm.sendToServer(ServerObjectType.SET_PROJECT, new Project("123", "Lactosioto", "Bermell", new ArrayList<Category>(0), new ArrayList<String>(0),"Bacccc"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}