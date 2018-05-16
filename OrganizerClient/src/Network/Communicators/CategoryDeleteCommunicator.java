package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que escolta si algun usuari ha borrat alguna categoria.
 */
public class CategoryDeleteCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryID = objectIn.readObject().toString();
            DataManager.getSharedInstance().deleteCategory(categoryID);
            //TODO avisar al controller
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
