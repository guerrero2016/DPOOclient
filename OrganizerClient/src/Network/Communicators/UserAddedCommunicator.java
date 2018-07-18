package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.user.User;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que controla l'accio d'afegir un usuari a un projecte
 */
public class UserAddedCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan s'afegeix un usuari al projecte
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            User user = (User) objectIn.readObject();
            DataManager.getSharedInstance().addUser(user);
            controller.userJoinedProject(user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
