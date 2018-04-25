package Controller.edition;

import Controller.MainViewController;
import Controller.edition.project.NewTaskController;
import Controller.edition.project.ProjectActionController;
import Controller.edition.project.category.CategoryActionController;
import Controller.edition.project.category.CategoryMouseController;
import Controller.edition.project.category.NewCategoryController;
import Controller.edition.task.TaskActionController;
import Controller.edition.task.TaskDocumentController;
import Controller.edition.user.UserDocumentListener;
import Controller.edition.user.project.DeleteProjectController;
import Controller.edition.user.project.NewProjectUserController;
import Controller.edition.user.task.DeleteTaskController;
import Controller.edition.user.task.NewTaskUserController;
import ModelAEliminar.Project;
import ModelAEliminar.Task;
import View.edition.EditionPanel;

public class EditionController {

    public final static int NO_CATEGORY = -1;

    private final static String PROJECT_USERS_TITLE = "Project Users";
    private final static String TASK_USERS_TITLE = "Task Users";

    private MainViewController mainController;
    private EditionPanel editionPanel;
    private Project project;
    private int currentCategory;

    public EditionController(MainViewController mainController, EditionPanel editionPanel) {

        //Link variables
        this.mainController = mainController;
        this.editionPanel = editionPanel;

        //Initial config
        this.currentCategory = NO_CATEGORY;

        //Link project controllers
        this.editionPanel.registerProjectActionController(new ProjectActionController(this, this.
                editionPanel.getProjectPanel()));
        this.editionPanel.registerProjectDocumentController(new NewCategoryController(this.editionPanel.
                getProjectPanel()));

        //Config project users
        this.editionPanel.setProjectUsersTitle(PROJECT_USERS_TITLE);

        //Link project users controllers
        this.editionPanel.registerProjectUserActionController(new NewProjectUserController(this,
                this.editionPanel.getProjectUserPanel()));
        this.editionPanel.registerProjectUserDocumentController(new UserDocumentListener(this.editionPanel.
                getProjectUserPanel()));
        this.editionPanel.registerProjectUserMouseController(new DeleteProjectController(this, this.
                editionPanel.getProjectUserPanel()));

        //Link task controllers
        this.editionPanel.registerTaskDocumentController(new TaskDocumentController(this.editionPanel.getTaskPanel()));

        //Config task users
        this.editionPanel.setTaskUsersTitle(TASK_USERS_TITLE);

        //Link task users controllers
        this.editionPanel.registerTaskUserActionController(new NewTaskUserController(this,
                this.editionPanel.getTaskUserPanel()));
        this.editionPanel.registerTaskUserDocumentController(new UserDocumentListener(this.editionPanel.
                getTaskUserPanel()));
        this.editionPanel.registerTaskUserMouseController(new DeleteTaskController(this, this.editionPanel.
                getTaskUserPanel()));

    }

    public void loadProject(Project project) {

        this.project = project;

        //Config project content
        editionPanel.setBackground(project.getBackground());
        editionPanel.setProjectName(this.project.getName());

        for(int i = 0; i < this.project.getTotalCategories(); i++) {
            editionPanel.addCategory(this.project.getCategory(i));
            editionPanel.registerCategoryActionController(new CategoryActionController(this,
                    editionPanel.getCategoryPanel(i)), i);
            editionPanel.registerCategoryDocumentController(new NewTaskController(editionPanel.getCategoryPanel(i)), i);
            editionPanel.registerCategoryMouseController(new CategoryMouseController(this, i), i);
        }

        //Config users panel
        editionPanel.setProjectUsersList(this.project.getUsers());

        showProjectContent();

    }

    public void showProjectContent() {
        editionPanel.showProjectPanel();
    }

    public void showTaskContent() {
        editionPanel.showTaskPanel();
    }

    public void setTaskContent(Task task, int categoryIndex) {

        currentCategory = categoryIndex;

        //Config task content
        editionPanel.setTaskName(task.getName());
        editionPanel.setTaskDescription(task.getDescription());
        editionPanel.setTagsList(task.getTags());
        editionPanel.setTaskUsersList(task.getUsers());

        //Link controllers
        editionPanel.registerTaskActionController(new TaskActionController(this, editionPanel.getTaskPanel(),
                task));

    }

    public void updateTask(Task task) {
        //TODO: Communicate task update
    }

    public void deleteTask(Task task) {
        //TODO: Communicate task delete
        project.getCategory(currentCategory).removeTask(task);
        editionPanel.getProjectPanel().removeTask(currentCategory, task);
        showProjectContent();
    }

}