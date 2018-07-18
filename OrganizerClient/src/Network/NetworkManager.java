package Network;

import Controller.MainViewController;
import model.ServerObjectType;
import Utils.Configuration;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * Classe encarregada de controlar la connexio entre client i servidor
 */
public class NetworkManager extends Thread {

    private boolean isOn;
    private MainViewController controller;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private HashMap<ServerObjectType, Communicable> communicables;

    /**
     * Crea el network manager iniciant aquells atributs necessaris per a la comunicacio
     */
    public NetworkManager() {
        try {
            this.isOn = false;
            this.communicables = new HashMap<>();
            Socket socketToServer = new Socket(Configuration.getInstance().getIPAddress(), Configuration.getInstance().
                    getCommunicationPort());
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procediment per a assignar un controlador
     * @param controller Controlador a assignar
     */
    public void setController(MainViewController controller) {
        this.controller = controller;
    }

    /**
     * Funcio encarregada de comencar la conexio
     */
    public void startCommunication() {
        isOn = true;
        this.start();
    }

    /**
     * Funcio encarregada de desconnectar el client del servidor
     */
    public void stopCommunication() {
        this.isOn = false;
        this.interrupt();
    }

    /**
     * Metode encarregat d'iniciar la comunicacio
     */
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
     * Funcio encarregada d'enviar dades al servidor
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
     * Funcio encarregada d'afegir un comunicador
     * @param communicable Comunicador
     * @param type Tipus de dada associada al comunicador
     */
    public void addCommunicator(Communicable communicable, ServerObjectType type) {
        communicables.put(type, communicable);
    }

    /**
     * Funcio encarregada d'eliminar un comunicador
     * @param type Tipus de dades associada al comunicador
     */
    public void removeCommunicator (ServerObjectType type) {
        communicables.remove(type);
    }

}