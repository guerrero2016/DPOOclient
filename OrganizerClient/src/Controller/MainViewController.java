package Controller;

import Controller.edition.EditionController;
import Network.Communicable;
import View.LogInPanel;
import model.project.Category;
import model.project.Project;
import model.project.Tag;
import model.project.Task;
import model.user.User;
import View.MainView;
import Network.NetworkManager;
import model.ServerObjectType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainViewController extends WindowAdapter implements ActionListener{

    final private NetworkManager network;
    private MainView view;
    private LogInController logInController;
    private SignInController signInController;
    private EditionController editionController;
    final private ProjectsMainViewController projectsMainViewController;
//    public MainViewController(MainView view, NetworkManager network,) {
//        this.view = view;
//        this.network = network;
//        logInController = new LogInController(this);
//        signInController = new SignInController(this);
//        projectSelectionController = new ProjectSelectionController(this);
//        editionController = new EditionController(this, view.getEditionPanel());
//    }

    public MainViewController(NetworkManager network, MainView view, LogInController logInController, SignInController signInController,
                              ProjectsMainViewController projectsMainViewController, EditionController editionController) {
        this.view = view;
        this.logInController = logInController;
        this.signInController = signInController;
        this.projectsMainViewController = projectsMainViewController;
        this.editionController = editionController;
        this.network = network;
    }

    public ProjectsMainViewController getProjectsMainViewController() {
        return projectsMainViewController;
    }

    public EditionController getEditionController() {
        return editionController;
    }

    public void swapPanel(int whatPanel) {
        view.swapPanel(whatPanel);
    }

    public Project[] getProjects() {

        Project[] projects = new Project[10];

        for(int i = 0; i < 10; i++) {
            Project project = new Project(String.valueOf(i), "Name " + i, Color.CYAN, i % 2 == 0);
        }

        return projects;

    }

    public void loadProject(Project project) {
        editionController.loadProject(project);
        editionController.showProjectContent();
    }

    public void updateProject(Project project) {
        editionController.updateProjectView(project);
    }

    public void updateCategory(Project project, Category category) {
        //TODO: Update category from database
    }

    public void deleteProject() {
        //TODO: Remove user from project
    }

    public User getUserFromDB(String userName) {
        //TODO: Check if user exists in the database
        return new User(userName);
    }

    public void shareProject(Project project, User user) {
        //TODO: Share project
    }

    public void addMemberInDB(String categoryId, String taskId, User user) {
        try {
            sendToServer(ServerObjectType.SET_MEMBER, categoryId);
            sendToServer(null, taskId);
            sendToServer(null, user);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addMemberInProject(String categoryId, String taskId, User user) {
        editionController.addMemberInProject(categoryId, taskId, user);
    }

    public void userJoinedProject(User user) {
        editionController.userJoinedProject(user);
    }

    public void removeMemberInDB(String categoryId, String taskId, User user) {
        try {
            sendToServer(ServerObjectType.DELETE_MEMBER, categoryId);
            sendToServer(null, taskId);
            sendToServer(null, user);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void removeMemberInProject(String categoryId, String taskId, User user) {
        editionController.removeMemberInProject(categoryId, taskId, user);
    }

    public void removeTagInDB(String categoryId, String taskId, Tag tag) {
        try {
            sendToServer(ServerObjectType.DELETE_TAG, categoryId);
            sendToServer(null, taskId);
            sendToServer(null, tag);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTagInProject(String categoryId, String taskId, Tag tag) {
        editionController.removeTagInProject(categoryId, taskId, tag);
    }

    public void editTagInDB(String taskId, Tag tag) {
        try {
            sendToServer(ServerObjectType.EDIT_TAG, taskId);
            sendToServer(null, tag);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void editTagInProject(String categoryId, String taskId, Tag tag) {
        editionController.editTagInProject(categoryId, taskId, tag);
    }

    public void resetSelectionView(){
        projectsMainViewController.resetOwnerProjects();
        projectsMainViewController.resetSharedProjects();
    }

    public void sendToServer(ServerObjectType type, Object o) throws IOException {
        network.sendToServer(type, o);
    }

    public void removeCommunicator (ServerObjectType serverObjectType) {
        network.removeCommunicator(serverObjectType);
    }

    public void addComunicator (Communicable communicator, ServerObjectType type){
        network.addCommunicator(communicator, type);
    }
    public void showDialog(String errorMSG) {
        view.showErrorDialog(errorMSG);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(JOptionPane.showConfirmDialog(view, "Sortir?") == JOptionPane.OK_OPTION){
            view.dispose();
            try {
                sendToServer(ServerObjectType.LOGOUT, null);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (JOptionPane.showConfirmDialog(view, "Tancar sessió?") == JOptionPane.OK_OPTION) {
                sendToServer(ServerObjectType.LOGOUT, null);
                swapPanel(LogInPanel.LOGIN);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
