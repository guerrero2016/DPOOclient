package Controller.project;

import Controller.MainViewController;
import model.project.Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe que controla les dues pantalles de seleccio de projectes
 */
public class ProjectsMainViewController implements ActionListener {

    private final ProjectSelectionController ownerSelectionController;
    private final ProjectSelectionController sharedSelectionController;
    private final ProjectCreationController projectCreationController;

    /**
     * Constructor a partir dels controladors dels controladors de les pantalles de seleccio de projectes
     * @param ownerSelectionController Controlador dels projectes del propietari
     * @param sharedSelectionController Controlador dels projectes compartits
     */
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
     * Funcio encarregada d'eliminar tots els projectes compartits de la vista
     */
    public void resetSharedProjects () {
        sharedSelectionController.resetProjectViews();
    }

    /**
     * Getter del controlador dels projectes del propietari
     * @return Controlador
     */
    public ProjectSelectionController getOwnerSelectionController() {
        return ownerSelectionController;
    }

    /**
     * Getter del controlador dels projectes compartits
     * @return Controlador
     */
    public ProjectSelectionController getSharedSelectionController() {
        return sharedSelectionController;
    }

    /**
     * Funcio encarregada d'inicialitzar els projectes en els quals l'usuari es propietari
     * @param projects Projectes propis
     */
    public void createOwnerProjects (ArrayList<Project> projects) {
        ownerSelectionController.createProjects(projects);
    }

    /**
     * Funcio encarregada d'inicialitzar els projectes compartits
     * @param projects Projectes compartits
     */
    public void createSharedProjects (ArrayList<Project> projects) {
        sharedSelectionController.createProjects(projects);
    }

    /**
     * Setter del controlador principal
     * @param controller Controlador
     */
    public void setController(MainViewController controller) {
        ownerSelectionController.setController(controller);
        sharedSelectionController.setController(controller);
    }

    /**
     * Funcio encarregada d'afegir un projecte a la vista de projectes del propietari
     * @param project Projecte propi
     */
    public void addOwnerProject (Project project) {
        ownerSelectionController.addProject(project);
    }

    /**
     * Funcio encarregada d'afegir projectes compartits
     * @param project Projecte compartit
     */
    public void addSharedProject (Project project) {
        sharedSelectionController.addProject(project);
    }

    /**
     * Metode encarregat de detectar ActionEvents
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        projectCreationController.createAddProjectView();
    }

}

