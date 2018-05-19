package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.project.Category;

import java.io.IOException;
import java.io.ObjectInputStream;

public class CategorySwapCommunicator implements Communicable {
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
