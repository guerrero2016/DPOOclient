package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Category;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que escolta si algun usuari ha afegit o modificat una categoria
 */
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
