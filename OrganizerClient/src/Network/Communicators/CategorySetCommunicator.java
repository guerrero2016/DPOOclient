package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Category;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Comunicador que escolta si algun usuari ha afegit o modificat una categoria
 */
public class CategorySetCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan una categoria es editada o creada
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Category category = (Category) objectIn.readObject();
            boolean exists = false;
            int i;
            for(i = 0; i < DataManager.getSharedInstance().getSelectedProject().getCategories().size(); i++) {
                String idAux = DataManager.getSharedInstance().getSelectedProject().getCategories().get(i).getId();
                if(idAux.equals(category.getId())) {
                    exists = true;
                    break;
                }
            }
            if(exists) {
                ArrayList<Category> categories = DataManager.getSharedInstance().getSelectedProject().getCategories();
                Category existentCategory = categories.get(i);
                existentCategory.update(category);
                controller.getEditionController().updateCategoryInView(existentCategory);
            } else {
                DataManager.getSharedInstance().getSelectedProject().getCategories().add(category);
                controller.getEditionController().addCategory(category);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
