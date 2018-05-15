package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Model.user.User;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class UsersInfoCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            User[] users = (User[]) objectIn.readObject();
            DataManager.getSharedInstance().setUsers(users);

            String title = DataManager.getSharedInstance().getTitle();
            String memberString = DataManager.getSharedInstance().getMembersAsString();

            controller.createProjectInfoWindow(title, memberString);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
