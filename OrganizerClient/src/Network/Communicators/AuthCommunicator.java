package Network.Communicators;

import Controller.MainViewController;
import Network.Communicable;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;

public class AuthCommunicator implements Communicable {

    private String[] titles;
    private Color[] colors;

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            int error = objectIn.readInt();

            switch (error) {
                case 0:
                    readProjects(objectIn);
                    controller.createOwnerBoxProjects(titles, colors);
                    readProjects(objectIn);
                    controller.createSharedBoxProjects(titles, colors);

                    break;

                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readProjects(ObjectInputStream objectIn) throws IOException {

        int numProj = objectIn.readInt();
        titles = new String[numProj];
        colors = new Color[numProj];

        for (int i = 0; i < numProj; i++) {
            //TODO save hash
            final String hash = objectIn.readUTF();
            titles[i] = objectIn.readUTF();
            String color = objectIn.readUTF();
            colors[i] = Color.decode(color);
        }
    }


}
