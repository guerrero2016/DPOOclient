package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que s'encarrega de controlar l'eliminació d'un projecte
 */
public class ProjectDeletedCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            DataManager dataManager = DataManager.getSharedInstance();
            final Project p = (Project) objectIn.readObject();

            if (dataManager.getUserName().equals(p.getOwnerName())) {
                p.setOwner(true);
            }

            if (p.isOwner()) {
                int i = dataManager.getOwnerProjectIndex(p);
                System.out.println(i);
                dataManager.deleteOwnerProjectByID(p.getId());
                controller.getProjectsMainViewController().getOwnerSelectionController().deleteProject(i);
            }else {
                int i = dataManager.getSharedProjectIndex(p);
                dataManager.deleteSharedProjectByID(p.getId());
                controller.getProjectsMainViewController().getSharedSelectionController().deleteProject(i);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
