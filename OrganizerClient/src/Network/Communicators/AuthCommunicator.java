package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Communicator encarregat de rebre quin error ha generat el registre o el login
 */
public class AuthCommunicator implements Communicable {

    private ArrayList<Project> projects;

    /**
     * Metode usat com a resposta del servidor per manegar la connecxio d'un usuari
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            int error = (Integer) objectIn.readObject();
            projects = new ArrayList<>();
            switch (error) {
                case 0:
                    DataManager dataManager = DataManager.getSharedInstance();

                    readProjects(objectIn);
                    dataManager.setProjectOwnerList(projects);
                    controller.getProjectsMainViewController().createOwnerProjects(projects);

                    readProjects(objectIn);
                    dataManager.setProjectSharedList(projects);
                    controller.getProjectsMainViewController().createSharedProjects(projects);
                    break;
                case 1:
                    controller.showDialog("Error, aquest nom d'usuari ja està registrat.");
                    break;
                case 2:
                    controller.showDialog("Error, aquest correu d'usuari ja està registrat.");
                    break;
                case 3:
                    controller.showDialog("Error, dades incorrectes.");
                    break;
                case 4:
                    controller.showDialog("Error, aquest usuari ja ha iniciat sessió.");
                    break;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio encarregada de llegir els projectes que rep del servidor
     * @param objectIn InputStream que s'utilitza per a la comunicacio
     * @throws IOException Error
     * @throws ClassNotFoundException Error
     */
    private void readProjects(ObjectInputStream objectIn) throws IOException, ClassNotFoundException {

        int totalProjects = objectIn.readInt();

        for (int i = 0; i < totalProjects; i++) {
            final Project p = (Project) objectIn.readObject();
            projects.add(p);
        }
    }

}
