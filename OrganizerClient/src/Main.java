import Model.Category;
import Model.User;
import View.project.ProjectView;
import Controller.MainViewController;
import View.MainView;
import javax.swing.*;
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

            }
        });
    }

}

//Project views demonstration
/*
try {

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
		Image image = ImageIO.read(new File("C:/background4.jpg"));
		project.setBackground(image);
	} catch(IOException e) {
		//Could not load file
		System.out.println("Missing background");
	}

	ProjectView projectView = new ProjectView(project);
	projectView.setVisible(true);

	ArrayList<Tag> tags = new ArrayList<>();

	Task task = new Task("New task", "No description", tags, null);
	projectView.addNewTask(task, 0);
	projectView.removeTask(1, 0);

} catch(IOException | NullPointerException e) {
	//Error
}
*/