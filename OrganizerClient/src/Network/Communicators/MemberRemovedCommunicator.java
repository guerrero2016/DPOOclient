package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.DataManager;
import model.user.User;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * S'encarrega de la comunicacio quan s'elimina l'assignacio d'un membre a una tasca
 */
public class MemberRemovedCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan un membre es eliminat de la participacio en una tasca
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryId = objectIn.readObject().toString();
            String taskId = objectIn.readObject().toString();
            User user = (User) objectIn.readObject();
            controller.removeMemberInProject(categoryId, taskId, user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
