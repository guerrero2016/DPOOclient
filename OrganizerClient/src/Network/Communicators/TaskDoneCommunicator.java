package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * S'encarrega de la comunicació quan una tasca està acabada.
 */
public class TaskDoneCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryId = objectIn.readObject().toString();
            final String taskId = objectIn.readObject().toString();
            controller.setTaskDoneInProject(categoryId, taskId);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
