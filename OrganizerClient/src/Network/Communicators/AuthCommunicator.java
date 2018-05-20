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

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            int error = (Integer) objectIn.readObject();

            System.out.println(error);

            switch (error) {
                case 0:
                    DataManager dataManager = DataManager.getSharedInstance();

                    dataManager.setProjectOwnerList(readProjects(objectIn));
                    controller.getProjectsMainViewController().createOwnerProjects(projects);

                    dataManager.setProjectSharedList(readProjects(objectIn));
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

    private ArrayList<Project> readProjects(ObjectInputStream objectIn) throws IOException, ClassNotFoundException {

        projects = new ArrayList<>();

        int totalProjects = objectIn.readInt();

        for (int i = 0; i < totalProjects; i++) {
            //TODO save hash
            final Project p = (Project) objectIn.readObject();
            projects.add(p);
        }

        return projects;
    }

}
