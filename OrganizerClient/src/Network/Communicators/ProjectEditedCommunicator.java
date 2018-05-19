package Network.Communicators;

import Controller.MainViewController;
import View.MainView;
import View.ProjectSelectionView;
import View.ProjectsMainView;
import model.DataManager;
import model.project.Project;
import Network.Communicable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Comunicador que escolta si algun usuari ha afegit un projecte
 */
public class ProjectEditedCommunicator implements Communicable {

    @Override
    public void communicate(MainViewController controller, ObjectInputStream objectIn) {
        try {
            DataManager dataManager = DataManager.getSharedInstance();
            final Project p = (Project) objectIn.readObject();

            if (dataManager.getWhatPanel() == MainView.PROJECT_ID) {
                controller.updateProject(p);
            } else if(dataManager.getWhatPanel() == ProjectsMainView.VIEW_TAG) {
                if (p.isOwner()) {
                    if (dataManager.getOwnerProjectIndex(p) == -1) {
                        dataManager.addProjectToOwnerList(p);
                        controller.getProjectsMainViewController().addOwnerProject(p);
                    } else {
                        dataManager.deleteOwnerProjectByID(p.getId());
                        dataManager.addProjectToOwnerList(p);
                        controller.getProjectsMainViewController().addOwnerProject(p);
                    }

                }else {
                    if (dataManager.getSharedProjectIndex(p) == -1) {
                        dataManager.addProjectToSharedList(p);
                        controller.getProjectsMainViewController().addSharedProject(p);
                    } else {
                        dataManager.deleteSharedProjectByID(p.getId());
                        dataManager.addProjectToSharedList(p);
                        controller.getProjectsMainViewController().addSharedProject(p);
                    }
                }
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
