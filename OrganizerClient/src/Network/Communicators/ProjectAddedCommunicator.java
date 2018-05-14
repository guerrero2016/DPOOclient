package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.awt.*;
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
                controller.addOwnerProjectBox(p.getName(), Color.decode(p.getColor()));
            }else {
                dataManager.addProjectToSharedList(p);
                controller.addSharedProjectBox(p.getName(), Color.decode(p.getColor()));
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
