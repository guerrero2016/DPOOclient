package Network;

import Controller.MainViewController;
import model.ServerObjectType;
import Utils.Configuration;
import model.project.Project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * Classe encarregada de controlar la conexió entre client i servidor
 */
public class NetworkManager extends Thread {

    private boolean isOn;
    private MainViewController controller;
    private Socket socketToServer;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private HashMap<ServerObjectType, Communicable> communicables;

    /**
     * Crea el network manager iniciant aquells atributs necessaris per a la comunicació
     */
    public NetworkManager() {
        try {
            this.isOn = false;
            this.controller = controller;
            this.communicables = new HashMap<>();
            this.socketToServer = new Socket(Configuration.getInstance().getIPAddress(), Configuration.getInstance().
                    getCommunicationPort());
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procediment per a assignar un controlador.
     * @param controller controlador a assignar
     */
    public void setController(MainViewController controller) {
        this.controller = controller;
    }

    /**
     * Funció encarregada de començar la conexió
     */
    public void startCommunication() {
        isOn = true;
        this.start();
    }

    /**
     * Funció encarregada de desconectar el client del servidor
     */
    public void stopCommunication() {
        this.isOn = false;
        this.interrupt();
    }

    @Override
    public void run() {
        //Mentres estigui conectat comprovarem si algun comunicador es cridat desde el servidor
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

    /**
     * Funció encarregada d'enviar dades al servidor
     * @param type Tipus de dades a enviar
     * @param object Objecte a enviar
     * @throws IOException Error
     */
    public void sendToServer(ServerObjectType type, Object object) throws IOException {
        objectOut.flush();
        if (type != null) {
            objectOut.writeInt(type.getValue());
        }
        objectOut.writeObject(object);
    }

    /**
     * Funció encarregada d'afegir un comunicador
     * @param communicable Comunicador
     * @param type Tipus de dada associada al comunicador
     */
    public void addCommunicator(Communicable communicable, ServerObjectType type) {
        communicables.put(type, communicable);
    }

    /**
     * Funció encarregada d'eliminar un comunicador
     * @param type Tipus de dades associada al comunicador
     */
    public void removeCommunicator (ServerObjectType type) {
        communicables.remove(type);
    }
}
