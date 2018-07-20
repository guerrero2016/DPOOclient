package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Category;
import model.project.Task;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * S'encarrega de la comunicacio quan s'elimina una tasca
 */
public class TaskDeletedCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan una tasca es eliminada
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Task task = (Task)objectIn.readObject();
            String categoryID = objectIn.readObject().toString();
            controller.getEditionController().deleteTaskInProject(task, categoryID);
            DataManager.getSharedInstance().updateTasksOrder(categoryID, task.getOrder());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
