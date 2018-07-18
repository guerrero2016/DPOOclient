package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.DataManager;
import model.project.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Comunicador que escolta quan es canvia d'ordre una tasca
 */
public class TaskSwapCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan una tasca canvia d'ordre
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            final ArrayList<Task> tasks = (ArrayList<Task>) objectIn.readObject();
            final String categoryID = (String) objectIn.readObject();
            DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID).setTasks(tasks);
            controller.getEditionController().swapTasksInView(categoryID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
