package Controller;

import View.AddProjectView;
import model.project.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe que controla la pantalla de creacio de projectes.
 */
public class ProjectCreationController implements MouseListener, ActionListener {

    private ProjectSelectionController projectSelectionController;
    private AddProjectView view;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton)e.getSource()).getText();
        if (button.equals("Afegir")) {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel colorPanel = (JPanel) e.getSource();
        view.selectColor(colorPanel);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

}
