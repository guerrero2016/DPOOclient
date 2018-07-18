package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.project.Tag;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que s'encarrega de controlar l'eliminacio d'una etiqueta
 */
public class TagDeletedCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan una etiqueta es eliminada
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryId = objectIn.readObject().toString();
            String taskId = objectIn.readObject().toString();
            Tag tag = (Tag) objectIn.readObject();
            controller.removeTagInProject(categoryId, taskId, tag);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
