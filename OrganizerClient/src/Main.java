import Controller.*;
import Controller.edition.EditionController;
import Network.Communicators.*;
import Utils.Configuration;
import View.*;
import View.edition.EditionPanel;
import model.ServerObjectType;
import Network.NetworkManager;

import javax.swing.*;

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

                //AUTH View
                SignInPanel signInPanel = new SignInPanel();
                LogInPanel logInPanel = new LogInPanel();

                //PROJECT SELECTION View
                ProjectSelectionView ownerSelectionView = new ProjectSelectionView(true);
                ProjectSelectionView sharedSelectionView = new ProjectSelectionView(false);
                ProjectsMainView projectsMainView = new ProjectsMainView(ownerSelectionView, sharedSelectionView);

                //EDITION View
                EditionPanel editionPanel = new EditionPanel();

                //MAIN View
                MainView mainView = new MainView(logInPanel, signInPanel, projectsMainView, editionPanel);

                //AUTH CONTROLLERS
                SignInController signInController = new SignInController(signInPanel);
                LogInController logInController = new LogInController(logInPanel);

                signInPanel.addControllerButton(signInController);
                logInPanel.addControllerButton(logInController);

                //PROJECT SELECTION CONTROLLERS
                ProjectSelectionController ownerSelectionController =
                        new ProjectSelectionController(ownerSelectionView);
                ProjectSelectionController sharedSelectionController =
                        new ProjectSelectionController(sharedSelectionView);
                ProjectsMainViewController projectsMainViewController =
                        new ProjectsMainViewController(projectsMainView, ownerSelectionController,
                                sharedSelectionController);

                ownerSelectionView.registerController(ownerSelectionController);
                sharedSelectionView.registerController(sharedSelectionController);
                projectsMainView.registerController(projectsMainViewController);

                //EDITION CONTROLLER
                EditionController editionController = new EditionController(editionPanel);

                MainViewController mainViewController = new MainViewController(mainView, logInController,
                        signInController, projectsMainViewController, editionController);

                signInController.setController(mainViewController);
                logInController.setController(mainViewController);
                editionController.registerMainController(mainViewController);
                projectsMainViewController.setController(mainViewController);


                //NETWORK

                NetworkManager network = new NetworkManager(mainViewController);

                network.addCommunicator(new AuthCommunicator(), ServerObjectType.AUTH);
                network.addCommunicator(new GetAllProjectsComunicator(), ServerObjectType.GET_PROJECT_LIST);
                network.addCommunicator(new ProjectAddedCommunicator(), ServerObjectType.SET_PROJECT);
                network.addCommunicator(new ProjectDetailCommunicator(), ServerObjectType.GET_PROJECT);
                network.addCommunicator(new MemberAddedCommunicator(), ServerObjectType.SET_MEMBER);

                mainViewController.setNetwork(network);

                network.startCommunication();
                mainView.setVisible(true);

            }
        });
    }

}