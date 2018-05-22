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
        Configuration.getInstance().loadConfiguration();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
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
                        new ProjectsMainViewController(ownerSelectionController, sharedSelectionController);

                ownerSelectionView.registerController(ownerSelectionController);
                sharedSelectionView.registerController(sharedSelectionController);
                projectsMainView.registerController(projectsMainViewController);

                //EDITION CONTROLLER

                NetworkManager network = new NetworkManager();

                network.addCommunicator(new AuthCommunicator(), ServerObjectType.AUTH);
                network.addCommunicator(new GetAllProjectsCommunicator(), ServerObjectType.GET_PROJECT_LIST);
                network.addCommunicator(new ProjectDetailCommunicator(), ServerObjectType.GET_PROJECT);
                network.addCommunicator(new MemberAddedCommunicator(), ServerObjectType.SET_MEMBER);
                network.addCommunicator(new MemberRemovedCommunicator(), ServerObjectType.DELETE_MEMBER);
                network.addCommunicator(new TagDeletedCommunicator(), ServerObjectType.DELETE_TAG);
                network.addCommunicator(new UserAddedCommunicator(), ServerObjectType.JOIN_PROJECT);
                network.addCommunicator(new TagSetCommunicator(), ServerObjectType.SET_TAG);
                network.addCommunicator(new UserDeletedCommunicator(), ServerObjectType.DELETE_USER);
                network.addCommunicator(new TaskSwapCommunicator(), ServerObjectType.SWAP_TASK);
                network.addCommunicator(new TaskDoneCommunicator(), ServerObjectType.TASK_DONE);
                network.addCommunicator(new TaskNotDoneCommunicator(), ServerObjectType.TASK_NOT_DONE);
                network.addCommunicator(new ProjectEditedCommunicator(), ServerObjectType.SET_PROJECT);
                network.addCommunicator(new ProjectDeletedCommunicator(), ServerObjectType.DELETE_PROJECT);


                EditionController editionController = new EditionController(editionPanel);

                MainViewController mainViewController = new MainViewController(network, mainView,
                        projectsMainViewController, editionController);

                mainView.addController(mainViewController, mainViewController);

                editionController.setMainController(mainViewController);
                signInController.setController(mainViewController);
                logInController.setController(mainViewController);
                projectsMainViewController.setController(mainViewController);

                //NETWORK
                network.setController(mainViewController);

                network.startCommunication();
                mainView.setVisible(true);
            }
        });
    }

}