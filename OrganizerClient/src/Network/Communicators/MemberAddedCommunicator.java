package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.user.User;

import java.io.IOException;
import java.io.ObjectInputStream;

public class MemberAddedCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            User user = (User) objectIn.readObject();
            controller.addMemberInCharge(user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
