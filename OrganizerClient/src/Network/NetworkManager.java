package Network;

import Controller.MainViewController;
import model.ServerObjectType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
            this.communicables = new HashMap<>();
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
                if (communicables.get(serverObjectType) != null) {
                    communicables.get(serverObjectType).communicate(controller, objectIn);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendToServer(ServerObjectType type, Object object) throws IOException {
        if (type != null) {
            objectOut.writeInt(type.getValue());
        }
        objectOut.writeObject(object);
    }

    public void addCommunicator(Communicable communicable, ServerObjectType type) {
        communicables.put(type, communicable);
    }
}
