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
            int j = DataManager.getSharedInstance().getSelectedProject().
                    getCategoryIndex(DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID));
            int i = DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).
                    getTaskIndex(DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getTaskWithId(taskID));
            int k;
            boolean exists = false;
            Task t = DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getTasks().get(i);
            for(k = 0; k < t.getTagsSize(); k++) {
                if(tag.getId().equals(t.getTags().get(k).getId())) {
                    exists = true;
                    break;
                }
            }
            if(exists) {
                //DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getTasks().get(i).getTags().set(k, tag);
                controller.editTagInProject(categoryID, taskID, tag);
            } else {
                DataManager.getSharedInstance().getSelectedProject().getCategories().get(j).getTasks().get(i).getTags().add(tag);
                controller.getEditionController().addTag(tag);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
