import Controller.MainViewController;
import Model.*;
import View.edition.EditionPanel;
import View.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /*ProjectsMainView projectsMainView = new ProjectsMainView();
        ProjectSelectionController projectSelectionController = new ProjectSelectionController(projectsMainView);
        projectsMainView.registerAddProjectViewController(projectSelectionController);
        projectsMainView.registerProjectSelectionController(projectSelectionController);*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //1. Crear tots els JPanels
                //2. Crear tots els controllers de cada JPanel
                //3. Registres els JPanels amb els controladors
                //4. Creem la MainView passant-li TOTS els JPanels
                //5. Creem el controlador general passant-li tots els controllers i la MainView
                //6. Finalment durant la creació del controlador gran se li ha de passar aquest a tots
                //   els petits controladors.

                     MainView a = new MainView();
                     MainViewController mainViewController = new MainViewController(a);
                     mainViewController.registerControllers(a);
                     a.setVisible(true);

//                try {

                    Project project = new Project("Project Name");

                    for(int i = 0; i < 4; i++) {

                        Category category = new Category("Category " + (i + 1));

                        for(int j = 0; j < 20; j++) {

                            Task task = new Task();
                            task.setName("Task " + (j + 1));
                            task.setDescription("Description " + (j + 1));

                            for(int k = 0; k < 30; k++) {
                                task.addTag(new Tag("Tag " + (k + 1), Color.CYAN));
                                task.addTag(new Tag("Tag " + (k + 1), Color.GREEN));
                            }

                            category.addTask(task);

                        }

                        project.addCategory(category);

                    }

                    for(int i = 0; i < 50; i++) {
                        project.addUser(new User("User " + (i + 1), "Email " + (i +1),
                                "Password " + (i+ 1)));
                    }

                    try {
                        Image image = ImageIO.read(new File("img/background4.jpg"));
                        project.setBackground(image);
                    } catch(IOException e) {
                        //Could not load file
                        System.out.println("Missing background");
                    }

                    ArrayList<Tag> tags = new ArrayList<>();

                    Task task = new Task("New task", "No description", tags, null);

                    try {
                        JFrame jFrame = new JFrame();
                        EditionPanel editionPanel = new EditionPanel(project);
//                        editionPanel.showTaskPanel(new Task("Task name"));
                        jFrame.setContentPane(editionPanel);
                        jFrame.setSize(1024, 600);
                        jFrame.setVisible(true);
                        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    } catch(IOException e) {
                        //Not working
                    }
//                    projectView.addNewTask(task, 0);
//                    projectView.removeTask(1, 0);

//                    projectView.showTaskPanel(task);

//                } catch(IOException | NullPointerException e) {
//                    //Error
//                }

            }
        });
    }

}