package Network.Communicators;

import Controller.MainViewController;
import Model.DataManager;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

public class UserAddedCommunicator implements Communicable {
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            String memberName = objectIn.readUTF();
            DataManager.getSharedInstance().addMember(memberName);
            //TODO avisar lo commmuuunicator
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
