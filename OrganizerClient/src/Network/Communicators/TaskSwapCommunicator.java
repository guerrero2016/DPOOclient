package Network.Communicators;

import Controller.MainViewController;
import ModelAEliminar.Task;
import Network.Communicable;
import model.DataManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Comunicador que escolta quan es canvia d'ordre una tasca
 */
public class TaskSwapCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            ArrayList<Task> tasks = (ArrayList<Task>) objectIn.readObject();
            //TODO: Avisar al datamanager
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
