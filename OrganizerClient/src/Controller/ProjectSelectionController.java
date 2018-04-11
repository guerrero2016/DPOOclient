package Controller;

import View.ProjectBoxView;
import View.ProjectsMainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectSelectionController implements MouseListener, ActionListener {

    private final ProjectsMainView view;

    public ProjectSelectionController (ProjectsMainView view) {
        this.view = view;
        String[] names = new String []{"AAA", "BBB", "CCC","AAA", "BBB", "CCC", "TTT", "OOO", "PPP", "AAA", "BBB", "CCC","AAA", "BBB", "CCC", "TTT", "OOO", "PPP"};
        Color[] colors = new Color[] {Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN};
        view.createOwnerBoxProjects(names, colors);
        view.createSharedBoxProjects(names, colors);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        final String projectName = ((ProjectBoxView) e.getSource()).getTitle();
        final ProjectInfoController projectInfoController = new ProjectInfoController();
        projectInfoController.createView(projectName, "Lactosito");
    }

    public void createProject (String title, Color color) {
        view.addOwnerProjectBox(title, color);
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
        if (e.getActionCommand().equals(ProjectsMainView.ADD_PROJECT_ACTION_COMMAND)) {
            final ProjectCreationController  projectCreationController = new ProjectCreationController(this);
            projectCreationController.createAddProjectView();
        }
    }
}
