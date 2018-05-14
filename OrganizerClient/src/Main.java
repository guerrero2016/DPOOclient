import Controller.LogInController;
import Controller.MainViewController;
import Controller.ProjectSelectionController;
import Controller.SignInController;
import Network.Communicators.*;
import View.*;
import model.ServerObjectType;
import model.project.Category;
import model.project.Project;
import Network.NetworkManager;
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

                SignInPanel signInPanel = new SignInPanel();
                LogInPanel logInPanel = new LogInPanel();
                MainView mainView = new MainView(logInPanel, signInPanel);

                SignInController signInController = new SignInController(signInPanel);
                LogInController logInController = new LogInController(logInPanel);

                signInPanel.addControllerButton(signInController);
                logInPanel.addControllerButton(logInController);

                MainViewController mainViewController = new MainViewController(mainView, logInController,
                        signInController);

                signInController.setController(mainViewController);
                logInController.setController(mainViewController);

                NetworkManager network = new NetworkManager(mainViewController);

                network.addCommunicator(new AuthCommunicator(), ServerObjectType.AUTH);
                network.addCommunicator(new GetAllProjectsComunicator(), ServerObjectType.GET_PROJECT_LIST);

                mainViewController.setNetwork(network);

                network.startCommunication();
                mainView.setVisible(true);

                NetworkManager nm = new NetworkManager(mainViewController);
                nm.startCommunication();
                //    public Project(String id, String name, String color, ArrayList<Category> categories, ArrayList<String> membersName, String background) {
                try {
                    nm.sendToServer(ServerObjectType.SET_CATEGORY, new Category("Categoria", 2, new ArrayList<>(0)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}