package Network.Communicators;

import Controller.MainViewController;
import View.MainView;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ProjectDetailCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Project p = (Project) objectIn.readObject();
            DataManager.getSharedInstance().setSelectedProject(p);
            controller.resetSelectionView();
            controller.swapPanel(MainView.PROJECT_ID);
            controller.loadProject(DataManager.getSharedInstance().getSelectedProject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
