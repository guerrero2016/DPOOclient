package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que escolta si algun usuari ha afegit un projecte
 */
public class ProjectAddedCommunicator extends Thread implements Communicable {

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
