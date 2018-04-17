package Controller;

import View.CustomProjectButton;
import View.ProjectBoxView;
import View.ProjectsMainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectSelectionController implements MouseListener, ActionListener {

    private final MainViewController controller;

    public ProjectSelectionController (MainViewController controller) {
        String[] names = new String []{"AAA", "BBB", "CCC","AAA", "BBB", "CCC", "TTT", "OOO", "PPP", "AAA", "BBB", "CCC","AAA", "BBB", "CCC", "TTT", "OOO", "PPP"};
        Color[] colors = new Color[] {Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN, Color.gray, Color.BLUE, Color.CYAN};
        controller.createOwnerBoxProjects(names, colors);
        controller.createSharedBoxProjects(names, colors);
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //AQUI S'HA D'OBRIR EL PROJECTE
    }

    public void createProject (String title, Color color) {
        controller.addOwnerProjectBox(title, color);
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
        String actionCommand = ((JButton)e.getSource()).getActionCommand();

        switch (actionCommand){
            case ProjectBoxView.DELETE_AC:
                CustomProjectButton button = (CustomProjectButton) e.getSource();
                controller.removeProject(button.getProjectIndex());
                break;
            case ProjectsMainView.ADD_PROJECT_ACTION_COMMAND:
                createAddProjectWindow();
                break;
            case ProjectBoxView.INFO_AC:
                CustomProjectButton button2 = (CustomProjectButton) e.getSource();
                createProjectInfoWindow(button2.getProjectName());
        }
    }

    private void createAddProjectWindow () {
        final ProjectCreationController  projectCreationController =
                new ProjectCreationController(this);
        projectCreationController.createAddProjectView();
    }

    private void createProjectInfoWindow (String title) {
        final String projectName = title;
        final ProjectInfoController projectInfoController = new ProjectInfoController();
        projectInfoController.createView(projectName, "Lactosito");
    }
}
