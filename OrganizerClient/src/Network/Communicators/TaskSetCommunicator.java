package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Category;
import model.project.Task;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Comunicador que s'encarrega de escoltar si algun usuari ha afegit o modificat alguna tasca.
 */
public class TaskSetCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            Task task = (Task) objectIn.readObject();
            String categoryID = objectIn.readObject().toString();
            int i;
            int j;
            boolean exists = false;
            Category c = null;

            for(j = 0; j < DataManager.getSharedInstance().getSelectedProject().getCategories().size(); j++) {
                if(DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getId().equals(categoryID)) {
                    c = DataManager.getSharedInstance().getSelectedProject().getCategories().get(j);
                    break;
                }
            }
            for(i = 0; i < c.getTasksSize(); i++) {
                if(task.getID().equals(c.getTasks().get(i).getID())) {
                    exists = true;
                    break;
                }
            }
            if(exists) {
                DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getTasks().set(i, task);
                controller.getEditionController().updateTaskInView(categoryID, task);
            } else {
                DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getTasks().add(task);
                controller.getEditionController().addTask(c.getOrder(), task);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
