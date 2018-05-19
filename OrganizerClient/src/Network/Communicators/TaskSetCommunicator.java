package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Task;
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
            System.out.println(categoryID + task.getName() + task.getID());
            dataManager.setTask(task, categoryID);
            controller.updateTask(dataManager.getProjectID(), categoryID, task);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
