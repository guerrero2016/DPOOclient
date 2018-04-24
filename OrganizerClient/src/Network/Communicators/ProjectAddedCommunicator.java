package Network;

import Controller.MainViewController;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
