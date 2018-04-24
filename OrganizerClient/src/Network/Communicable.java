package Network;

import Controller.MainViewController;

import java.io.ObjectInputStream;

public interface Communicable {
    void communicate(MainViewController controller, ObjectInputStream objectIn);
}
