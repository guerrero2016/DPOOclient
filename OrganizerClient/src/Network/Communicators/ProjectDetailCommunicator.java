package Network.Communicators;

import Controller.MainViewController;
import View.MainView;
import model.DataManager;
import model.project.Project;
import Network.Communicable;
import model.user.User;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que s'encarrega de rebre l'informació detallada d'un projecte
 */
public class ProjectDetailCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan un projecte es obert per l'usuari
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {

            Project p = (Project) objectIn.readObject();
            String userName = objectIn.readObject().toString();
            User existentUser = null;

            //Get current user
            for(int i = 0; i < p.getUsersSize(); i++) {
                User user = p.getUser(i);
                if(user.getUserName().equals(userName)) {
                    existentUser = user;
                    break;
                }
            }

            DataManager dataManager = DataManager.getSharedInstance();
            p.setOwner(p.getOwnerName().equals(dataManager.getUserName()));
            dataManager.setSelectedProject(p);
            controller.resetSelectionView();
            controller.swapPanel(MainView.PROJECT_ID);
            controller.getEditionController().addCommunicators();
            controller.loadProject(DataManager.getSharedInstance().getSelectedProject(), existentUser);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
