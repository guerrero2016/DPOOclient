package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import Network.Communicable;
import model.project.Category;

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
            int i = DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID).getOrder();
            try {
                controller.getEditionController().deleteTaskInView();
                controller.getEditionController().showProjectContent();
            }catch (Exception e) {}
            DataManager.getSharedInstance().deleteCategory(categoryID);
            controller.getEditionController().setEditingState(false);
            controller.getEditionController().deleteCategoryInView(i);
            DataManager.getSharedInstance().updateCategoriesOrder(i);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
