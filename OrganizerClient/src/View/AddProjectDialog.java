package View;

import Controller.ProjectCreationController;

import javax.swing.*;
import java.awt.*;

public class AddProjectDialog extends JDialog {

    private final AddProjectView view;

    public AddProjectDialog () {

        this.view = new AddProjectView();

        this.getContentPane().add(view);
        this.setTitle("Creat nou projecte: ");
        this.setSize(new Dimension(300, 300));
        this.setVisible(true);
        this.setMinimumSize(new Dimension(300, 300));
        this.setModal(false);
    }

    public void blockOtherFrames () {
        this.setModal(true);
    }

    public void registerMouseListener (ProjectCreationController controller) {
        view.registerMouseListener(controller);
    }

    public String getProjectName () {
        return view.getProjectName();
    }

    public void selectColor (JPanel colorPanel) {
        view.selectColor(colorPanel);
    }
}
