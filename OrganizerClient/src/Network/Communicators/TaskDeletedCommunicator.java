package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Task;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class TaskDeletedCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Task task = (Task)objectIn.readObject();
            String categoryID = objectIn.readObject().toString();

            DataManager.getSharedInstance().deleteTask(task, categoryID);
            DataManager.getSharedInstance().updateTasksOrder(task.getOrder());
            controller.getEditionController().deleteTaskInView();
            controller.getEditionController().showProjectContent();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
