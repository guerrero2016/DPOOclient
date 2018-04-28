package Controller.edition;

import Controller.MainViewController;
import Controller.document.DocumentController;
import Controller.edition.project.ProjectActionController;
import Controller.edition.project.category.CategoryActionController;
import Controller.edition.project.category.CategoryMouseController;
import Controller.edition.task.TaskActionController;
import Controller.edition.task.tag.TagController;
import Controller.edition.task.user.TaskAddUserController;
import Controller.edition.task.user.TaskRemoveUserController;
import ModelAEliminar.*;
import View.edition.EditionPanel;
import View.edition.project.CategoryPanel;
import View.edition.project.ProjectPanel;
import View.edition.task.TaskPanel;
import View.edition.task.tag.TagPanel;
import View.edition.user.UserPanel;

public class EditionController {

    private final static String PROJECT_USERS_TITLE = "Project Users";
    private final static String TASK_USERS_TITLE = "Task Users";

    private MainViewController mainController;

    private EditionPanel editionPanel;
    private ProjectPanel projectPanel;
    private UserPanel projectUserPanel;
    private TaskPanel taskPanel;
    private UserPanel taskUserPanel;

    private Project project;
    private Category category;
    private Task task;

    private boolean isEditing;

    public EditionController(MainViewController mainController, EditionPanel editionPanel) {

        //Link variables
        this.mainController = mainController;
        this.editionPanel = editionPanel;
        projectPanel = editionPanel.getProjectPanel();
        projectUserPanel = editionPanel.getProjectUserPanel();
        taskPanel = editionPanel.getTaskPanel();
        taskUserPanel = editionPanel.getTaskUserPanel();

        //Config project panel
        projectPanel.registerActionController(new ProjectActionController(this, projectPanel));
        projectPanel.registerDocumentController(new DocumentController(projectPanel));

        //Config project users panel
        projectUserPanel.setTitle(PROJECT_USERS_TITLE);
        projectUserPanel.registerDocumentListener(new DocumentController(projectUserPanel));

        //Config task panel
        taskPanel.registerDocumentController(new DocumentController(taskPanel));

        //Config task users panel
        taskUserPanel.setTitle(TASK_USERS_TITLE);
        taskUserPanel.registerDocumentListener(new DocumentController(taskUserPanel));

    }

    public void loadProject(Project project) {

        //Default config
        this.project = project;
        category = null;
        task = null;

        //Config project content
        editionPanel.setBackground(project.getBackground());
        projectPanel.setProjectName(project.getName());

        for(int i = 0; i < project.getTotalCategories(); i++) {
            projectPanel.addCategory(project.getCategory(i));
            CategoryPanel categoryPanel = projectPanel.getCategoryPanel(i);
            categoryPanel.registerActionController(new CategoryActionController(this, categoryPanel,
                    project.getCategory(i)));
            categoryPanel.registerDocumentController(new DocumentController(categoryPanel));
            categoryPanel.registerMouseController(new CategoryMouseController(this, project.getCategory(i)));
        }

        //Config users panel
        projectUserPanel.setUserList(project.getUsers());
        //TODO: Users controllers

    }

    public void showProjectContent() {
        isEditing = false;
        editionPanel.showProjectPanel();
    }

    public void setTaskContent(Category category, Task task) {

        //Default config
        this.category = category;
        this.task = task;

        //Config task content
        taskPanel.setTaskName(task.getName());
        taskPanel.setDescription(task.getDescription());
        taskPanel.cleanTagsList();
        taskPanel.setTagsList(task.getTags());

        //Link controllers
        editionPanel.registerTaskActionController(new TaskActionController(this, editionPanel.getTaskPanel(),
                task));

        for(int i = 0; i < task.getTotalTags(); i++) {
            TagPanel tagPanel = editionPanel.getTaskPanel().getTagPanel(i);
            tagPanel.resetActionController();
            tagPanel.registerActionController(new TagController(this, tagPanel, task, task.getTag(i)));
        }

        //Config task users controllers
        taskUserPanel.setUserList(task.getUsers());
        taskUserPanel.resetActionController();
        taskUserPanel.registerActionController(new TaskAddUserController(this, taskUserPanel, task));
        taskUserPanel.resetMouseController();
        taskUserPanel.registerMouseController(new TaskRemoveUserController(this, taskUserPanel, task));

    }

    public void showTaskContent() {
        isEditing = false;
        editionPanel.showTaskPanel();
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditingState(boolean enableState) {
        isEditing = enableState;
    }

    public void updatedTask(Task task) {
        mainController.updateTask(project, category, task);
    }

    public void deleteTask() {
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoryIndex(category));
        categoryPanel.removeTask(task);
        category.removeTask(task);
        mainController.updateCategory(project, category);
    }

    public void removeTag(Tag tag) {
        taskPanel.removeTag(task.getTagIndex(tag));
    }

    public User getProjectUser(String userName) {

        for(int i = 0; i < project.getUsers().size(); i++) {
            if(userName.equals(project.getUser(i).getName())) {
                return project.getUser(i);
            }
        }

        return null;

    }

}