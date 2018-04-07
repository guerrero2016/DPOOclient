import View.project.ProjectView;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //ProjectSelectionView projectSelectionView = new ProjectSelectionView();
        //ProjectSelectionController projectSelectionController = new ProjectSelectionController(projectSelectionView);
        //projectSelectionView.registerController(projectSelectionController);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    ProjectView projectView = new ProjectView("Project name");

                    for (int i = 0; i < 10; i++) {
                        projectView.addNewCategory(null);
                    }

                    projectView.setVisible(true);
                    projectView.setCategoryName("Long category rename", 0);
                    projectView.setCategoryName("Category rename", 2);
                    projectView.removeCategory(1);

                    projectView.swapCategoriesPosition(0, 2);
                    projectView.addNewCategory(null);

                } catch(IOException e) {
                    //Image icons not loaded properly
                }
            }
        });
    }

}