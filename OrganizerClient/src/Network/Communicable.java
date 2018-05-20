package Network;

import Controller.MainViewController;

import java.io.ObjectInputStream;

/**
 * Representació d'una classe capaç de comunicar-se amb el servidor
 */
public interface Communicable {
    /**
     * Aquesta funció conté l'acció per dur a terme quan el comunicador és cridat
     * @param controller
     * @param objectIn
     */
    void communicate(MainViewController controller, ObjectInputStream objectIn);
}
