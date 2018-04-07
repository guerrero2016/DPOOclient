import Model.Task;
import View.project.TaskView;

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
                    TaskView taskView = new TaskView(new Task("Task name", "Task description", null, null));
                    taskView.setVisible(true);
                } catch (IOException e) {}
            }
        });
    }

}