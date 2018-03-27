package Controller;

import View.ProjectSelectionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectSelectionController implements MouseListener, ActionListener {

    private final ProjectSelectionView view;

    public ProjectSelectionController (ProjectSelectionView view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getSource().toString());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ProjectSelectionView.ADD_PROJECT_ACTION_COMMAND)) {
            final ProjectCreationController  projectCreationController = new ProjectCreationController();
            projectCreationController.createAddProjectView();
        }
    }
}
