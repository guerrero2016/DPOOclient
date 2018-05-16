package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Model.project.Task;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * AQUEST COMUNICATOR NO CALDRA FER-LO, NO TINDREM AQUESTA FUNCIONALITAT
 */
public class TaskCategoryCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Task task = (Task) objectIn.readObject();
            String fromCategory = objectIn.readObject().toString();
            String toCategory = objectIn.readObject().toString();
            DataManager dataManager = DataManager.getSharedInstance();

            dataManager.deleteTask(task, fromCategory);
            dataManager.setTask(task, toCategory);

            //TODO avisar al controller

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
