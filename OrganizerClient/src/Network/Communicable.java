package Network;

import Controller.MainViewController;

import java.io.ObjectInputStream;

/**
 * Representacio d'una classe capac de comunicar-se amb el servidor
 */
public interface Communicable {
    /**
     * Aquesta funcio conte l'accio per dur a terme quan el comunicador es cridat
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    void communicate(MainViewController controller, ObjectInputStream objectIn);
}
