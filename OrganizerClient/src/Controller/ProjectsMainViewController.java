package Controller;

import View.ProjectsMainView;
import model.project.Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe que controla les dos pantalles de seleccio de projectes.
 */
public class ProjectsMainViewController implements ActionListener {

    private final ProjectSelectionController ownerSelectionController;
    private final ProjectSelectionController sharedSelectionController;
    private final ProjectCreationController projectCreationController;

    public ProjectsMainViewController(ProjectSelectionController ownerSelectionController,
                                      ProjectSelectionController sharedSelectionController) {
        this.ownerSelectionController = ownerSelectionController;
        this.sharedSelectionController = sharedSelectionController;
        ownerSelectionController.createProjects(new ArrayList<>());
        sharedSelectionController.createProjects(new ArrayList<>());
        projectCreationController = new ProjectCreationController(ownerSelectionController);
    }

    /**
     * Funcio encarregda d'eliminar tots els projectes de la vista. Nomes projectes on l'usuari sigui el propietari
     */
    public void resetOwnerProjects () {
        ownerSelectionController.resetProjectViews();
    }

    /**
     * Funcio encarregada d'eliminar tots els projectes compartits de la vista.
     */
    public void resetSharedProjects () {
        sharedSelectionController.resetProjectViews();
    }

    public ProjectSelectionController getOwnerSelectionController() {
        return ownerSelectionController;
    }

    public ProjectSelectionController getSharedSelectionController() {
        return sharedSelectionController;
    }

    /**
     * Funcio encarregada d'inicialitzar els projectes en els quals l'usuari es propietari
     * @param projects
     */
    public void createOwnerProjects (ArrayList<Project> projects) {
        ownerSelectionController.createProjects(projects);
    }

    /**
     * Funcio encarregada d'inicialitzar projectes compartits
     * @param projects
     */
    public void createSharedProjects (ArrayList<Project> projects) {
        sharedSelectionController.createProjects(projects);
    }

    public void setController(MainViewController controller) {
        ownerSelectionController.setController(controller);
        sharedSelectionController.setController(controller);
    }

    /**
     * Funcio encarregada d'afegir un projecte a la vista de projectes del propietari
     * @param project
     */
    public void addOwnerProject (Project project) {
        ownerSelectionController.addProject(project);
    }

    /**
     * Funcio encarregada d'afegir projectes compartits
     * @param project
     */
    public void addSharedProject (Project project) {
        sharedSelectionController.addProject(project);
    }

    /**
     * Funcio encarregada de borrar projectes propis del usuari
     * @param index
     */
    public void deleteOwnerProject(int index) {
        ownerSelectionController.deleteProject(index);
    }

    /**
     * Funcio encarregada d'eliminar projectes compartits
     * @param index
     */
    public void deleteSharedProject(int index) {
        sharedSelectionController.deleteProject(index);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        projectCreationController.createAddProjectView();
    }

}

