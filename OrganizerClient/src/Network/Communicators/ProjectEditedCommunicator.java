package Network.Communicators;

import Controller.MainViewController;
import View.MainView;
import View.project.ProjectsMainView;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que escolta si algun usuari ha afegit un projecte
 */
public class ProjectEditedCommunicator implements Communicable {
    /**
     * Metode usat com a resposta del servidor quan un projecte es editat o creat
     * @param controller Controlador de la vista general
     * @param objectIn InputStream que comunica amb el servidor
     */
    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {

        try {
            DataManager dataManager = DataManager.getSharedInstance();
            final Project p = (Project) objectIn.readObject();

            if(p == null){
                controller.showDialog("This project doesn't exist or you already joined it");
            } else {

                if(p.getOwnerName().equals(DataManager.getSharedInstance().getUserName())) {
                    p.setOwner(true);
                }

                if (dataManager.getWhatPanel() == MainView.PROJECT_ID) {
                    DataManager.getSharedInstance().setSelectedProject(p);
                    controller.updateProject(p);
                } else if(dataManager.getWhatPanel() == ProjectsMainView.VIEW_TAG) {
                    if(p.isOwner()) {
                        int i = dataManager.getOwnerProjectIndex(p);
                        if(i == -1) {
                            dataManager.addProjectToOwnerList(p);
                            controller.getProjectsMainViewController().addOwnerProject(p);
                        } else {
                            dataManager.deleteOwnerProjectByID(p.getId());
                            controller.getProjectsMainViewController().getOwnerSelectionController().deleteProject(i);
                            dataManager.addProjectToOwnerList(p);
                            controller.getProjectsMainViewController().addOwnerProject(p);
                        }

                    } else {
                        int i = dataManager.getSharedProjectIndex(p);
                        if (i == -1) {
                            dataManager.addProjectToSharedList(p);
                            controller.getProjectsMainViewController().addSharedProject(p);
                        } else {
                            dataManager.deleteSharedProjectByID(p.getId());
                            controller.getProjectsMainViewController().getSharedSelectionController().deleteProject(i);
                            dataManager.addProjectToSharedList(p);
                            controller.getProjectsMainViewController().addSharedProject(p);
                        }
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
