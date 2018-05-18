package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import View.ProjectsMainView;
import model.DataManager;
import model.project.Project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Communicator encarregat d'escoltar quan el Servidor envia la llista de projectes a les quals està l'usuari.
 * Rep els projectes separats depenent de si es propietari o no.
 */

public class GetAllProjectsComunicator implements Communicable {

    private ArrayList<Project> projects;

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        DataManager dataManager = DataManager.getSharedInstance();
        try {
            dataManager.setUserName(objectIn.readObject().toString());
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
     * Recupera una llista de projectes.
     * @param objectIn L'ObjectInputStream del socket que es comunica amb el servidor.
     * @return llista de projectes
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private ArrayList<Project> readProjects(ObjectInputStream objectIn) throws IOException, ClassNotFoundException {

        projects = new ArrayList<>();
        int numProj = (Integer) objectIn.readObject();


        for (int i = 0; i < numProj; i++) {
            final Project p = (Project) objectIn.readObject();
            System.out.println(p.getName());
            projects.add(p);
        }

        return projects;

    }

}
