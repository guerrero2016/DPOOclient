package Controller;

import Controller.edition.EditionController;
import Controller.project.ProjectsMainViewController;
import Network.Communicable;
import View.user.LogInPanel;
import model.DataManager;
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

/**
 * Classe encarrageada de comunica-se amb el servidor i amb els controladors
 */
public class MainViewController extends WindowAdapter implements ActionListener{

    final private NetworkManager network;
    private MainView view;
    private EditionController editionController;
    final private ProjectsMainViewController projectsMainViewController;

    /**
     * Constructor a partir dels parametres de control de les vistes i de les connexions amb el servidor
     * @param network Manager de les connexions
     * @param view Vista a controlar
     * @param projectsMainViewController Controlador de la vista
     * @param editionController Controlador de l'edicio de projectes
     */
    public MainViewController(NetworkManager network, MainView view, ProjectsMainViewController projectsMainViewController,
                              EditionController editionController) {
        this.view = view;
        this.projectsMainViewController = projectsMainViewController;
        this.editionController = editionController;
        this.network = network;
    }

    /**
     * Getter del ProjectsMainViewController
     * @return ProjectsMainViewController assignat
     */
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

    /**
     * Metode encarregat de canviar la vista a mostrar
     * @param whatPanel Identificador numeric
     */
    public void swapPanel(int whatPanel) {
        view.swapPanel(whatPanel);
    }

    /**
     * Metode encarregat de mostrar el contingut d'un projecte
     * @param project Projecte a mostrar
     * @param user Usuari del client
     */
    public void loadProject(Project project, User user) {
        editionController.loadProject(project, user);
        editionController.showProjectContent();
    }

    /**
     * Metode encarregat d'actualitzar el contingut d'un projecte que es mostrat
     * @param project Projecte a actualitzar
     */
    public void updateProject(Project project) {
        editionController.updateProjectView(project);
    }

    /**
     * Metode encarregat de comunicar al servidor que s'ha afegit un membre a una tasca
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
     * Metode encarregat d'afegir un membre a una tasca en concret
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la categoria on pertany el membre a afegir
     * @param user Membre a afegir
     */
    public void addMemberInProject(String categoryId, String taskId, User user) {
        editionController.addMemberInProject(categoryId, taskId, user);
    }

    /**
     * Procediment que s'encarrega d'afegir un usuari al projecte
     * @param user Usuari a afegir
     */
    public void userJoinedProject(User user) {
        editionController.userJoinedProject(user);
    }

    /**
     * Metode encarregat de comunicar al servidor que s'ha eliminat un membre a una tasca
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
     * Metode encarregat d'eliminar un membre d'una tasca en concret
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la categoria on pertany el membre a eliminar
     * @param user Membre a eliminar
     */
    public void removeMemberInProject(String categoryId, String taskId, User user) {
        editionController.removeMemberInProject(categoryId, taskId, user);
    }

    /**
     * Metode encarregat de notificar al servidor que es vol elimina una etiqueta
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
     * Metode encarregat d'eliminar una etiqueta del projecte
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca on pertany l'etiqueta
     * @param tag Etiqueta a eliminar
     */
    public void removeTagInProject(String categoryId, String taskId, Tag tag) {
        editionController.removeTagInProject(categoryId, taskId, tag);
    }

    /**
     * Metode encarregat de comunicar al servidor que s'ha editat una etiqueta
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
     * Metode encarregat d'editar una etiqueta del projecte
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca on pertany l'etiqueta
     * @param tag Etiqueta a editar
     */
    public void editTagInProject(String categoryId, String taskId, Tag tag) {
        editionController.editTagInProject(categoryId, taskId, tag);
    }

    /**
     * Metode encarregat de marcar una tasca com a finalitzada al servidor
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca
     */
    public void setTaskDoneInDB(String categoryId, String taskId) {
        try {
            sendToServer(ServerObjectType.TASK_DONE, categoryId);
            sendToServer(null, taskId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat de marcar una tasca com a finalitzada al projecte
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca
     */
    public void setTaskDoneInProject(String categoryId, String taskId) {
        editionController.setTaskDoneInProject(categoryId, taskId);
    }

    /**
     * Metode encarregat de marcar una tasca com a no finalitzada al servidor
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca
     */
    public void setTaskNotDoneInDB(String categoryId, String taskId) {
        try {
            sendToServer(ServerObjectType.TASK_NOT_DONE, categoryId);
            sendToServer(null, taskId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat de marcar una tasca com a no finalitzada al projecte
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca
     */
    public void setTaskNotDoneInProject(String categoryId, String taskId) {
        editionController.setTaskNotDoneInProject(categoryId, taskId);
    }

    /**
     * Metode encarregat d'afegir una etiqueta al projecte
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca on es vol afegir la etiqueta
     * @param tag Etiqueta a afegir
     */
    public void addTagInProject(String categoryId, String taskId, Tag tag) {
        editionController.addTagInProject(categoryId, taskId, tag);
    }

    /**
     * Procediment que avisa al EditionController que un usuari ha estat expulsat
     * @param user Usuari eliminat
     */
    public void userLeftProject(User user) {
        editionController.userLeftProject(user);
    }

    /**
     * Procediment encarregat de netejar els projectes del panell de seleccio
     */
    public void resetSelectionView(){
        projectsMainViewController.resetOwnerProjects();
        projectsMainViewController.resetSharedProjects();
    }

    /**
     * Procediment que envia dades al servidor
     * @param type Tipus de peticio que es fa. Si es <code>null</code> no s'envia aquest parametre
     * @param o Objecte a enviar
     * @throws IOException Error
     */
    public void sendToServer(ServerObjectType type, Object o) throws IOException {
        network.sendToServer(type, o);
    }

    /**
     * Procediment que elimina un communicator
     * @param serverObjectType Clau del communicator
     */
    public void removeCommunicator(ServerObjectType serverObjectType) {
        network.removeCommunicator(serverObjectType);
    }

    /**
     * Afegeix un communicator
     * @param communicator Communicator a afegir
     * @param type Clau del communicator
     */
    public void addCommunicator(Communicable communicator, ServerObjectType type){
        network.addCommunicator(communicator, type);
    }

    /**
     * Procediment que s'encarrega de mostrar un JOptionPane d'error amb un missatge
     * @param errorMSG Missatge que es mostrara
     */
    public void showDialog(String errorMSG) {
        view.showErrorDialog(errorMSG);
    }

    /**
     * Metode encarregat de tancar correctament el programa
     * @param e WindowEvent
     */
    @Override
    public void windowClosing(WindowEvent e) {
        if(JOptionPane.showConfirmDialog(view, "Exit?", "Exit",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            view.dispose();
            view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            try {
                sendToServer(ServerObjectType.LOGOUT, null);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Metode encarregat de detectar events de butons
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (JOptionPane.showConfirmDialog(view, "Log out?", "Log out",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                sendToServer(ServerObjectType.LOGOUT, null);
                DataManager.getSharedInstance().setSelectedProject(null);
                DataManager.getSharedInstance().setProjectOwnerList(null);
                DataManager.getSharedInstance().setProjectSharedList(null);
                DataManager.getSharedInstance().setUserName(null);
                swapPanel(LogInPanel.LOGIN);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}