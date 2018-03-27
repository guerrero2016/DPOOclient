import Controller.ProjectSelectionController;
import View.ProjectInfoView;
import View.ProjectSelectionView;

public class Main {

    public static void main(String[] args) {
        ProjectSelectionView projectSelectionView = new ProjectSelectionView();
        ProjectSelectionController projectSelectionController = new ProjectSelectionController(projectSelectionView);
        projectSelectionView.registerController(projectSelectionController);
    }


}
