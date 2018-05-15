package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Model.project.Project;
import Network.Communicable;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ProjectAddedCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            DataManager dataManager = DataManager.getSharedInstance();
            final Project p = (Project) objectIn.readObject();


            if (p.isOwner()) {
                dataManager.addProjectToOwnerList(p);
                controller.addOwnerProjectBox(p);
            }else {
                dataManager.addProjectToSharedList(p);
                controller.addSharedProjectBox(p);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
