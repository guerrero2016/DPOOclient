package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.user.User;

import java.io.IOException;
import java.io.ObjectInputStream;

public class MemberRemovedComunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryId = objectIn.readUTF();
            String taskId = objectIn.readUTF();
            User user = (User) objectIn.readObject();
            controller.removeMemberInProject(categoryId, taskId, user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
