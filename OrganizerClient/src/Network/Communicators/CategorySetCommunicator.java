package Network.Communicators;

import Controller.MainViewController;
import View.edition.project.category.CategoryPanel;
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
            boolean exists = false;
            int i;
            for(i = 0; i < DataManager.getSharedInstance().getSelectedProject().getCategories().size(); i++) {
                if(DataManager.getSharedInstance().getSelectedProject().getCategories().get(i).getId().equals(category.getId())) {
                    exists = true;
                    break;
                }
            }
            if(exists) {
                DataManager.getSharedInstance().getSelectedProject().getCategories().set(i, category);
            } else {
                System.out.println("XDDDDD");
                controller.getEditionController().addCategory(category);
                DataManager.getSharedInstance().getSelectedProject().getCategories().add(category);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
