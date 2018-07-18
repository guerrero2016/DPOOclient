package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import View.project.ProjectsMainView;
import model.DataManager;
import model.project.Project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Communicator encarregat d'escoltar quan el Servidor envia la llista de projectes a les quals esta l'usuari.
 * Rep els projectes separats depenent de si es propietari o no
 */

public class GetAllProjectsCommunicator implements Communicable {

    private ArrayList<Project> projects;

    /**
     * Metode usat com a resposta del servidor per obtenir els projectes en de l'usuari (inclos compartits)
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        DataManager dataManager = DataManager.getSharedInstance();
        try {

            DataManager.getSharedInstance().setSelectedProject(null);
            Object username = objectIn.readObject();
            if (username == null) {
                controller.showDialog("El projecte en el que estaves s'ha eliminat.");
                controller.getEditionController().removeCommunicators();
            } else {
                dataManager.setUserName(username.toString());
            }

            controller.resetSelectionView();

            dataManager.setProjectOwnerList(readProjects(objectIn));
            controller.getProjectsMainViewController().createOwnerProjects(projects);
            dataManager.setProjectSharedList(readProjects(objectIn));
            controller.getProjectsMainViewController().createSharedProjects(projects);

            controller.swapPanel(ProjectsMainView.VIEW_TAG);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera una llista de projectes
     * @param objectIn L'ObjectInputStream del socket que es comunica amb el servidor
     * @return Llista de projectes
     * @throws IOException Error
     * @throws ClassNotFoundException Error
     */
    private ArrayList<Project> readProjects(ObjectInputStream objectIn) throws IOException, ClassNotFoundException {
        projects = new ArrayList<>();
        int totalProjects = (Integer) objectIn.readObject();
        for (int i = 0; i < totalProjects; i++) {
            final Project p = (Project) objectIn.readObject();
            projects.add(p);
        }
        return projects;
    }

}
