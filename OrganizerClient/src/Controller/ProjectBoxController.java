package Controller;

import Network.NetworkManager;
import View.ProjectBoxView;
import model.ServerObjectType;
import model.project.Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Classe encarregada de controlar cada projecte.
 */
public class ProjectBoxController implements MouseListener, ActionListener {

    private ProjectBoxView view;
    private final Project project;
    private MainViewController controller;

    public ProjectBoxController(Project project, MainViewController controller) {
        this.project = project;
        this.controller = controller;
    }

    public void setView(ProjectBoxView view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            try {
                controller.sendToServer(ServerObjectType.GET_PROJECT, project.getId());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            controller.sendToServer(ServerObjectType.DELETE_PROJECT, project.getId());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
