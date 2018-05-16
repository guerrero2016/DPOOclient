package Controller;

import Network.NetworkManager;
import View.AddProjectView;
import model.project.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectCreationController implements MouseListener, ActionListener {

    private ProjectSelectionController projectSelectionController;
    private AddProjectView view;
    private Color color;
    NetworkManager networkManager;

    public ProjectCreationController(ProjectSelectionController projectSelectionController) {
        this.projectSelectionController = projectSelectionController;
    }

    public void createAddProjectView () {
        view = new AddProjectView();
        view.registerMouseListener(this);
        view.setDialogVisible(true);
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getProjectName() == null || view.getProjectName().isEmpty()) {
            System.out.println("Missing parameters");
        } else {
            Project newProject = new Project("", view.getProjectName(), view.getProjectColor(), true);
            projectSelectionController.createProject(newProject);
            view.setDialogVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel colorPanel = (JPanel) e.getSource();
        color = colorPanel.getBackground();
        view.selectColor(colorPanel);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
