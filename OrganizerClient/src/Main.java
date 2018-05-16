import Controller.LogInController;
import Controller.MainViewController;
import Controller.SignInController;
import Network.Communicators.*;
import Utils.Configuration;
import View.*;
import Model.ServerObjectType;
import Network.NetworkManager;
import Model.user.UserRegister;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Configuration.loadConfiguration();
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

                try {
                    network.sendToServer(ServerObjectType.REGISTER, new UserRegister("1","2","3","4"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}