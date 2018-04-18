package View;

import javax.swing.*;

public class CustomProjectButton extends JButton {

    private final String projectName;
    private final int projectIndex;

    public CustomProjectButton(String projectName, int projectIndex) {
        this.projectName = projectName;
        this.projectIndex = projectIndex;
    }

    public CustomProjectButton(Icon icon, String projectName, int projectIndex) {
        super(icon);
        this.projectName = projectName;
        this.projectIndex = projectIndex;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getProjectIndex() {
        return projectIndex;
    }
}
