package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.DataManager;
import model.ServerObjectType;
import model.user.User;

import java.io.IOException;
import java.io.ObjectInputStream;

public class UserDeletedCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            User user = (User) objectIn.readObject();
            DataManager dataManager = DataManager.getSharedInstance();
            if (!user.getUserName().equals(dataManager.getUserName())) {
                controller.userLeftProject(user);
            } else {
                dataManager.setSelectedProject(null);
                controller.showDialog("T'han expulsat del projecte");
                controller.sendToServer(ServerObjectType.EXIT_PROJECT, null);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
