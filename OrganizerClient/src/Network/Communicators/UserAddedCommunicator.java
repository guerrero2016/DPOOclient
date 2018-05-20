package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.user.User;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class UserAddedCommunicator implements Communicable {
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
