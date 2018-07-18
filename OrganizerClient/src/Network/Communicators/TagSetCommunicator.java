package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.DataManager;
import model.project.Project;
import model.project.Tag;
import model.project.Task;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que s'encarrega de escoltar si algun usuari ha afegit o modificat alguna tasca
 */
public class TagSetCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan una etiqueta es editada o creada
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {

            Tag tag = (Tag) objectIn.readObject();
            String taskID = objectIn.readObject().toString();
            String categoryID = objectIn.readObject().toString();

            Task targetTask = DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID)
                    .getTaskWithId(taskID);
            Tag t = targetTask.getTagWithId(tag.getId());

            if(t != null) {
                t.setName(tag.getName());
                t.setColor(tag.getColor());
                controller.editTagInProject(categoryID, taskID, tag);
                return;
            }

            //New tag
            controller.addTagInProject(categoryID, taskID, tag);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
