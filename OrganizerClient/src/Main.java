import Model.Category;
import Model.User;
import View.project.MemberView;
import View.project.ProjectView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //ProjectSelectionView projectSelectionView = new ProjectSelectionView();
        //ProjectSelectionController projectSelectionController = new ProjectSelectionController(projectSelectionView);
        //projectSelectionView.registerController(projectSelectionController);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ProjectView projectView = new ProjectView(null);
                    projectView.setVisible(true);
                } catch(IOException e) {
                    //Error
                }
            }
        });
    }

}