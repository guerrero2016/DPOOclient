package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;
import model.project.Tag;

import java.io.IOException;
import java.io.ObjectInputStream;

public class TagEditedCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String categoryId = objectIn.readUTF();
            String taskId = objectIn.readUTF();
            Tag tag = (Tag) objectIn.readObject();
            controller.editTagInProject(categoryId, taskId, tag);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
