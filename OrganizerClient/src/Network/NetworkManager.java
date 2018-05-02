package Network;

import Controller.MainViewController;
import Model.ServerObjectType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class NetworkManager extends Thread {

    private boolean isOn;
    private MainViewController controller;
    private Socket socketToServer;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private HashMap<ServerObjectType, Communicable> communicables;

    public NetworkManager(MainViewController controller) {
        try {
            this.isOn = false;
            this.controller = controller;
            this.socketToServer = new Socket("127.0.0.1", 15001);
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startCommunication() {
        isOn = true;
        this.start();
    }

    public void stopCommunication() {
        this.isOn = false;
        this.interrupt();
    }

    @Override
    public void run() {

        while (isOn) {
            try {
                int typeID = objectIn.readInt();
                ServerObjectType serverObjectType = ServerObjectType.valueOf(typeID);

                communicables.get(serverObjectType).communicate(controller, objectIn);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendToServer(ServerObjectType type, Object object) throws IOException {
        objectOut.writeObject(type);
        objectOut.writeObject(object);
    }
}
