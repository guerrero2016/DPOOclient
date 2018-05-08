package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Task;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class TaskSetCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            DataManager dataManager = DataManager.getSharedInstance();
            Task task = (Task) objectIn.readObject();
            String categoryID = objectIn.readUTF();

            dataManager.setTask(task, categoryID);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
