package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.project.Category;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Classe que representa el comunicador que controla el canvi d'ordre d'una categoria
 */
public class CategorySwapCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan una categoria canvia d'ordre
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            final Category fromCategory = (Category) objectIn.readObject();
            final Category toCategory = (Category) objectIn.readObject();
            controller.getEditionController().swapCategoriesInView(fromCategory.getOrder(), toCategory.getOrder());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
