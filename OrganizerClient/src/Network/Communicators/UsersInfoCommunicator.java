package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class UsersInfoCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String[] members = (String[]) objectIn.readObject();
            DataManager.getSharedInstance().setMembers(members);

            String title = DataManager.getSharedInstance().getTitle();
            String memberString = DataManager.getSharedInstance().getMembersAsString();

            controller.createProjectInfoWindow(title, memberString);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
