package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.user.User;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que controla l'accio d'afegir un membre al projecte
 */
public class MemberAddedCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan un membre es afegit a una tasca
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryId = objectIn.readObject().toString();
            String taskId = objectIn.readObject().toString();
            User user = (User) objectIn.readObject();
            controller.addMemberInProject(categoryId, taskId, user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
