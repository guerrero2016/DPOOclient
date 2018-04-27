package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ProjectDetailCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Project p = (Project) objectIn.readObject();
            DataManager.getSharedInstance().setSelectedProject(p);
            //TODO avisar al controooolller
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
