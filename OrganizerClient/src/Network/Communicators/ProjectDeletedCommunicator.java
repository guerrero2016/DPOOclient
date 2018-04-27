package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ProjectDeletedCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            DataManager dataManager = DataManager.getSharedInstance();
            final Project p = (Project) objectIn.readObject();

            if (p.isOwner()) {
                dataManager.deleteOwnerProjectByID(p.getId());
                //controller.removeProject();
            }else {
                dataManager.deleteSharedProjectByID(p.getId());
            }

            //TODO avisar al controller
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
