
package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.DataManager;
import model.user.User;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UserInfoCommunicator implements  Communicable{
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            User[] users = (User[]) objectIn.readObject();
            DataManager.getSharedInstance().setUsers(users);

            String title = DataManager.getSharedInstance().getTitle();
            String memberString = DataManager.getSharedInstance().getMembersAsString();

            controller.getProjectsMainViewController().getOwnerSelectionController().createProjectInfoWindow(title, memberString);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
