package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * S'encarrega de la comunicacio quan una tasca esta acabada
 */
public class TaskDoneCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan una tasca es marcada com a finalitzada
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
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
