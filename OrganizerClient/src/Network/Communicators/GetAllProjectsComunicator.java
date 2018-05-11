package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import View.ProjectsMainView;
import model.DataManager;
import model.project.Project;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Communicator encarregat d'escoltar quan el Servidor envia la llista de projectes a les quals està l'usuari.
 * Rep els projectes separats depenent de si es propietari o no.
 */

public class GetAllProjectsComunicator implements Communicable {

    private String[] titles;
    private Color[] colors;

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        DataManager dataManager = DataManager.getSharedInstance();
        try {
            dataManager.setProjectOwnerList(readProjects(objectIn));
            controller.createOwnerBoxProjects(titles, colors);
            dataManager.setProjectSharedList(readProjects(objectIn));
            controller.createSharedBoxProjects(titles, colors);

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
        ArrayList<Project> projects = new ArrayList<>();
        int numProj = (Integer) objectIn.readObject();

        titles = new  String[numProj];
        colors = new Color[numProj];

        for (int i = 0; i < numProj; i++) {
            final Project p = (Project) objectIn.readObject();
            projects.add(p);
            titles[i] = p.getName();
            colors[i] = Color.decode(p.getColor());
        }
        return projects;
    }

}
