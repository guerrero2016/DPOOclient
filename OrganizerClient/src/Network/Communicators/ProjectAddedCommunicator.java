package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ProjectAddedCommunicator implements Communicable {

    //TODO canviar els final string per una classe data manager
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            final String hash = objectIn.readUTF();
            final String title = objectIn.readUTF();
            final String color = objectIn.readUTF();
            final boolean isOwner = objectIn.readBoolean();

            if (isOwner) {
                controller.addOwnerProjectBox(title, Color.decode(color));
            }else {
                controller.addSharedProjectBox(title, Color.decode(color));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO avisar al controller
    }

}
