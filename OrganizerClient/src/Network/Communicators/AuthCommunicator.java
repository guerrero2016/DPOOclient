package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class AuthCommunicator implements Communicable {

    private String[] titles;
    private Color[] colors;


    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            int error = objectIn.readInt();

            switch (error) {
                case 0:
                    DataManager dataManager = DataManager.getSharedInstance();

                    dataManager.setProjectOwnerList(readProjects(objectIn));
                    controller.createOwnerBoxProjects(titles, colors);

                    dataManager.setProjectSharedList(readProjects(objectIn));
                    controller.createSharedBoxProjects(titles, colors);
                    break;

                case 1:
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

        ArrayList<Project> projects = new ArrayList<>();

        int numProj = objectIn.readInt();

        for (int i = 0; i < numProj; i++) {
            //TODO save hash
            final Project p = (Project) objectIn.readObject();
            projects.add(p);
            titles[i] = p.getName();
            colors[i] = Color.decode(p.getColor());
        }
        return projects;
    }


}
