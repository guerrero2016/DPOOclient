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
                    //MOSTRAR ERRORS OPTIONPANEEEL
                    break;
                case 2:
                    break;
                case 3:
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
