package Controller;

import View.AddProjectView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectCreationController implements MouseListener{

    private AddProjectView view;

    public ProjectCreationController() {
    }

    public void createAddProjectView () {
        view = new AddProjectView();
        view.registerMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel colorPanel = (JPanel) e.getSource();
        System.out.println(colorPanel.getBackground());
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
