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
            Category category = DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID);
            controller.getEditionController().deleteTaskInView(task, category);
            DataManager.getSharedInstance().updateTasksOrder(task.getOrder());
            controller.getEditionController().showProjectContent();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
