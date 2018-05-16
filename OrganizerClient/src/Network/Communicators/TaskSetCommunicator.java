package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Model.project.Task;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que s'encarrega de escoltar si algun usuari ha afegit o modificat alguna tasca.
 */
public class TaskSetCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            DataManager dataManager = DataManager.getSharedInstance();
            Task task = (Task) objectIn.readObject();
            String categoryID = objectIn.readObject().toString();

            dataManager.setTask(task, categoryID);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
