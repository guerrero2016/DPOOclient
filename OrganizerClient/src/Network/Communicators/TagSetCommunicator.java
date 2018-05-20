package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.DataManager;
import model.project.Category;
import model.project.Tag;
import model.project.Task;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que s'encarrega de escoltar si algun usuari ha afegit o modificat alguna tasca.
 */
public class TagSetCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {

            Tag tag = (Tag) objectIn.readObject();
            String taskID = objectIn.readObject().toString();
            String categoryID = objectIn.readObject().toString();

            Task targetTask = DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID)
                    .getTaskWithId(taskID);

            Tag t = targetTask.getTagWithId(tag.getId());

            if (t != null) {
                t.setName(tag.getName());
                t.setColor(tag.getColor());
                controller.editTagInProject(categoryID, taskID, tag);
                return;
            }

            //New tag
            DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID).
                    getTaskWithId(taskID).addTag(tag);
            controller.addTagInProject(categoryID, taskID, tag);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
