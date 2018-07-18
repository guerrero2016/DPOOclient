package Controller.project;

import View.project.AddProjectView;
import model.project.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe que controla la pantalla de creacio de projectes
 */
public class ProjectCreationController implements MouseListener, ActionListener {

    private ProjectSelectionController projectSelectionController;
    private AddProjectView view;

    /**
     * Constructor a partir del controlador de seleccio de projectes
     * @param projectSelectionController Controlador de seleccio de projectes
     */
    public ProjectCreationController(ProjectSelectionController projectSelectionController) {
        this.projectSelectionController = projectSelectionController;
    }

    /**
     * Funcio encarregada de crear la vista
     */
    public void createAddProjectView () {
        view = new AddProjectView();
        view.registerMouseListener(this);
        view.setDialogVisible(true);
    }

    /**
     * Metode encarregat de manegar ActionEvents
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton)e.getSource()).getText();
        if (button.equals("Add")) {
            if (view.getProjectName() == null || view.getProjectName().isEmpty()) {
                System.out.println("Missing parameters");
            } else {
                Project newProject = new Project(null, view.getProjectName(), view.getProjectColor(), true);
                projectSelectionController.requestProject(newProject);
                view.setDialogVisible(false);
            }
        } else {
            projectSelectionController.requestProject(view.getProjectID());
            view.setDialogVisible(false);
        }
    }

    /**
     * Metode encarregat de controlar els clicks del ratoli
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel colorPanel = (JPanel) e.getSource();
        view.selectColor(colorPanel);
    }

    /**
     * Metode no usat
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Metode no usat
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Metode no usat
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Metode no usat
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {}

}
