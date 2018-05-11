package Network.Communicators;

import Controller.MainViewController;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Communicator encarregat de rebre quin error ha generat el registre o el login
 */
public class AuthCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            int error = (Integer) objectIn.readObject();

            switch (error) {
                case 1:
                    //MOSTRAR ERRORS OPTIONPANEEEL
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
