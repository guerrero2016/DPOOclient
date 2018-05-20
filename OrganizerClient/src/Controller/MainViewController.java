package Controller;

import Controller.edition.EditionController;
import Network.Communicable;
import View.LogInPanel;
import model.project.Project;
import model.project.Tag;
import model.user.User;
import View.MainView;
import Network.NetworkManager;
import model.ServerObjectType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainViewController extends WindowAdapter implements ActionListener{

    final private NetworkManager network;
    private MainView view;
    private EditionController editionController;
    final private ProjectsMainViewController projectsMainViewController;

    public MainViewController(NetworkManager network, MainView view, ProjectsMainViewController projectsMainViewController,
                              EditionController editionController) {
        this.view = view;
        this.projectsMainViewController = projectsMainViewController;
        this.editionController = editionController;
        this.network = network;
    }

    public ProjectsMainViewController getProjectsMainViewController() {
        return projectsMainViewController;
    }

    /**
     * Geter del controlador de la vista que mostra els detalls d'un projecte
     * @return Controlador de la vista que mostra els detalls d'un projecte
     */
    public EditionController getEditionController() {
        return editionController;
    }

    public void swapPanel(int whatPanel) {
        view.swapPanel(whatPanel);
    }

    /**
     * Mètode encarregat de mostrar el contingut d'un projecte
     * @param project Projecte a mostrar
     */
    public void loadProject(Project project) {
        editionController.loadProject(project);
        editionController.showProjectContent();
    }

    /**
     * Mètode encarregat d'actualitzar el contingut d'un projecte que és mostrat
     * @param project Projecte a actualitzar
     */
    public void updateProject(Project project) {
        editionController.updateProjectView(project);
    }

    public User getUserFromDB(String userName) {
        //TODO: Check if user exists in the database
        return new User(userName);
    }

    /**
     * Mètode encarregat de comunicar al servidor que s'ha afegit un membre a una tasca
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la categoria on pertany el membre a afegir
     * @param user Membre a afegir
     */
    public void addMemberInDB(String categoryId, String taskId, User user) {
        try {
            sendToServer(ServerObjectType.SET_MEMBER, categoryId);
            sendToServer(null, taskId);
            sendToServer(null, user);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Métode encarregat d'afegir un membre a una tasca en concret
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la categoria on pertany el membre a afegir
     * @param user Membre a afegir
     */
    public void addMemberInProject(String categoryId, String taskId, User user) {
        editionController.addMemberInProject(categoryId, taskId, user);
    }

    public void userJoinedProject(User user) {
        editionController.userJoinedProject(user);
    }

    /**
     * Mètode encarregat de comunicar al servidor que s'ha eliminat un membre a una tasca
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la categoria on pertany el membre a eliminar
     * @param user Membre a eliminar
     */
    public void removeMemberInDB(String categoryId, String taskId, User user) {
        try {
            sendToServer(ServerObjectType.DELETE_MEMBER, categoryId);
            sendToServer(null, taskId);
            sendToServer(null, user);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode encarregat d'eliminar un membre d'una tasca en concret
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la categoria on pertany el membre a eliminar
     * @param user Membre a eliminar
     */
    public void removeMemberInProject(String categoryId, String taskId, User user) {
        editionController.removeMemberInProject(categoryId, taskId, user);
    }

    /**
     * Mètode encarregat de notificar al servidor que es vol elimina una etiqueta
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca on pertany l'etiqueta
     * @param tag Etiqueta a eliminar
     */
    public void removeTagInDB(String categoryId, String taskId, Tag tag) {
        try {
            sendToServer(ServerObjectType.DELETE_TAG, categoryId);
            sendToServer(null, taskId);
            sendToServer(null, tag);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode encarregat d'eliminar una etiqueta del projecte
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca on pertany l'etiqueta
     * @param tag Etiqueta a eliminar
     */
    public void removeTagInProject(String categoryId, String taskId, Tag tag) {
        editionController.removeTagInProject(categoryId, taskId, tag);
    }

    /**
     * Mètode encarregat de comunicar al servidor que s'ha editat una etiqueta
     * @param taskId Id de la tasca on pertany l'etiqueta
     * @param tag Etiqueta a editar
     */
    public void editTagInDB(String taskId, Tag tag) {
        try {
            sendToServer(ServerObjectType.EDIT_TAG, taskId);
            sendToServer(null, tag);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode encarregat d'editar una etiqueta del projecte
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca on pertany l'etiqueta
     * @param tag Etiqueta a editar
     */
    public void editTagInProject(String categoryId, String taskId, Tag tag) {
        editionController.editTagInProject(categoryId, taskId, tag);
    }

    /**
     * Mètode encarregat d'afegir una etiqueta al projecte
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca on es vol afegir la etiqueta
     * @param tag Etiqueta a afegir
     */
    public void addTagInProject(String categoryId, String taskId, Tag tag) {
        editionController.addTagInProject(categoryId, taskId, tag);
    }

    public void resetSelectionView(){
        projectsMainViewController.resetOwnerProjects();
        projectsMainViewController.resetSharedProjects();
    }

    public void sendToServer(ServerObjectType type, Object o) throws IOException {
        network.sendToServer(type, o);
    }

    public void removeCommunicator(ServerObjectType serverObjectType) {
        network.removeCommunicator(serverObjectType);
    }

    public void addCommunicator(Communicable communicator, ServerObjectType type){
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
