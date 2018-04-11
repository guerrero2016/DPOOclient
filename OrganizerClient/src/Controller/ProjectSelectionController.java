package Controller;

import View.ProjectBoxView;
import View.ProjectsMainView;

import javax.swing.*;
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
        String button = ((JButton)e.getSource()).getActionCommand();

        switch (button){
            case ProjectBoxView.INFO_AC:
                System.out.print("hey");
                final String projectName = ((ProjectBoxView) e.getSource()).getTitle();
                final ProjectInfoController projectInfoController = new ProjectInfoController();
                projectInfoController.createView(projectName, "Lactosito");
                break;

            case ProjectBoxView.DELETE_AC:

                break;

            case ProjectsMainView.ADD_PROJECT_ACTION_COMMAND:
                final ProjectCreationController  projectCreationController =
                        new ProjectCreationController(this);
                projectCreationController.createAddProjectView();
                break;
        }
        /*
        if (e.getActionCommand().equals(ProjectsMainView.ADD_PROJECT_ACTION_COMMAND)) {
            final ProjectCreationController  projectCreationController = new ProjectCreationController(this);
            projectCreationController.createAddProjectView();
        }*/
    }
}
