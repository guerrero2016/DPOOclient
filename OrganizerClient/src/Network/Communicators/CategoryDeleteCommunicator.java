package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que escolta si algun usuari ha esborrat alguna categoria
 */
public class CategoryDeleteCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quna una categoria es eliminada
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryID = objectIn.readObject().toString();
            int i = DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID).getOrder();
            try {
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
