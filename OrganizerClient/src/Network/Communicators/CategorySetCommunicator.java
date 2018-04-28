package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Model.project.Category;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class CategorySetCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Category category = (Category) objectIn.readObject();
            DataManager.getSharedInstance().setCategory(category);
            //TODO avisar al controller
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}