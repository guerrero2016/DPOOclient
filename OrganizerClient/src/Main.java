import Controller.MainViewController;
import Network.Communicators.ProjectAddedCommunicator;
import Network.Communicators.ProjectDeletedCommunicator;
import Network.Communicators.ProjectDetailCommunicator;
import com.sun.corba.se.spi.activation.Server;
import model.ServerObjectType;
import model.project.Category;
import model.project.Project;
import Network.NetworkManager;
import View.MainView;
import model.user.UserLogIn;
import model.user.UserRegister;

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
                nm.startCommunication();
                //    public Project(String id, String name, String color, ArrayList<Category> categories, ArrayList<String> membersName, String background) {
                nm.addCommunicator(new ProjectDeletedCommunicator(), ServerObjectType.DELETE_PROJECT);
                try {
                    nm.sendToServer(ServerObjectType.DELETE_PROJECT, "fd08aab1-1793-423a-8b9b-2818a654e872");
                    System.out.println("sent");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}