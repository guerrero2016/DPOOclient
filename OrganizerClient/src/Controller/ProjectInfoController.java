package Controller;

import View.ProjectInfoView;

public class ProjectInfoController {

    private ProjectInfoView view;

    public ProjectInfoController() {
    }

    public void createView (String title, String members) {
        this.view = new ProjectInfoView();
        view.setMembers(members);
        view.setProjectName(title);
        view.setDialogVisible(true);
    }
}
