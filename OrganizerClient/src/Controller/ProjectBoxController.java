package Controller;

import Network.NetworkManager;
import View.ProjectBoxView;
import model.project.Project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectBoxController implements MouseListener{

    ProjectBoxView view;
    final Project project;
    final NetworkManager networkManager;

    public ProjectBoxController(Project project, NetworkManager networkManager) {
        this.project = project;
        this.networkManager = networkManager;
    }

    public void setView(ProjectBoxView view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {

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

}
