package Network.Communicators;

import Controller.MainViewController;
import View.MainView;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que s'encarrega de rebre l'informació detallada d'un projecte
 */
public class ProjectDetailCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Project p = (Project) objectIn.readObject();
            DataManager dataManager = DataManager.getSharedInstance();
            p.setOwner(p.getOwnerName().equals(dataManager.getUserName()));
            dataManager.setSelectedProject(p);
            controller.resetSelectionView();
            controller.swapPanel(MainView.PROJECT_ID);
            controller.loadProject(DataManager.getSharedInstance().getSelectedProject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
