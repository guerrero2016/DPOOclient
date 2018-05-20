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
            int categoryIndex = DataManager.getSharedInstance().getSelectedProject().
                    getCategoryIndex(DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID));
            int taskIndex = DataManager.getSharedInstance().getSelectedProject().getCategories().get(categoryIndex).
                    getTaskIndex(DataManager.getSharedInstance().getSelectedProject().getCategories().get(categoryIndex).getTaskWithId(taskID));
            Task targetTask = DataManager.getSharedInstance().getSelectedProject().getCategories().get(categoryIndex).getTasks().get(taskIndex);

            //Check if tag exists
            for(int i = 0; i < targetTask.getTagsSize(); i++) {
                if(tag.getId().equals(targetTask.getTags().get(i).getId())) {
                    controller.editTagInProject(categoryID, taskID, tag);
                    return;
                }
            }

            //New tag
            controller.addTagInProject(categoryID, taskID, tag);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
