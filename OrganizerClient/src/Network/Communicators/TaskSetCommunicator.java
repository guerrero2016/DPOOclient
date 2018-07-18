package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Category;
import model.project.Task;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que s'encarrega d'escoltar si algun usuari ha afegit o modificat alguna tasca
 */
public class TaskSetCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan s'edita o afegeix una tasca
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
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
                if(task.getId().equals(c.getTasks().get(i).getId())) {
                    exists = true;
                    break;
                }
            }
            if(exists) {
                DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getTask(i).
                        setName(task.getName());
                DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getTask(i).
                        setDescription(task.getDescription());
                controller.getEditionController().updateTaskInView(categoryID, task);
            } else {
                controller.getEditionController().addTask(c.getOrder(), task);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}