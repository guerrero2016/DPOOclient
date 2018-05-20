package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.user.User;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que controla l'acció d'afegir un membre al projecte
 */
public class MemberAddedCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryId = objectIn.readUTF();
            String taskId = objectIn.readUTF();
            User user = (User) objectIn.readObject();
            controller.addMemberInProject(categoryId, taskId, user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
