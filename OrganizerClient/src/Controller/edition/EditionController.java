package Controller.edition;

import Controller.MainViewController;
import Controller.edition.document.DocumentController;
import Controller.edition.project.ProjectActionController;
import Controller.edition.project.category.CategoryActionController;
import Controller.edition.project.category.CategoryMouseController;
import Controller.edition.project.category.task.TaskListController;
import Controller.edition.project.user.ProjectAddUserController;
import Controller.edition.project.user.ProjectRemoveUserController;
import Controller.edition.task.TaskController;
import Controller.edition.task.tag.TagController;
import Controller.edition.task.user.TaskAddUserController;
import Controller.edition.task.user.TaskRemoveUserController;
import ModelAEliminar.*;
import View.ProjectsMainView;
import View.edition.EditionPanel;
import View.edition.project.category.CategoryPanel;
import View.edition.project.ProjectPanel;
import View.edition.task.TaskPanel;
import View.edition.task.tag.TagPanel;
import View.edition.user.UserPanel;

import java.awt.*;

public class EditionController {

    public final static String EDITING_ON_MESSAGE = "You should finish editing before doing something else";
    public final static String EDITING_ON_TITLE = "Information";

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

    public void loadProject(Project project, boolean isAdmin) {

        //Default config
        this.project = project;
        category = null;
        task = null;

        //Config project content
        projectPanel.hideDeleteButton(!isAdmin);
        editionPanel.setBackgroundImage(project.getBackground());
        projectPanel.setProjectName(project.getName());
        projectPanel.cleanCategories();

        for(int i = 0; i < project.getTotalCategories(); i++) {
            projectPanel.addCategory(project.getCategory(i));
            CategoryPanel categoryPanel = projectPanel.getCategoryPanel(i);
            categoryPanel.resetActionController();
            categoryPanel.registerActionController(new CategoryActionController(this, categoryPanel,
                    project.getCategory(i)));
            categoryPanel.resetMouseController();
            categoryPanel.registerMouseController(new CategoryMouseController(this, project.
                    getCategory(i)));
            categoryPanel.registerDocumentController(new DocumentController(categoryPanel));
            categoryPanel.resetDnDController();
            categoryPanel.registerDnDController(new TaskListController(this, project.getCategory(i),
                    categoryPanel.getListComponent()));
        }

        //Config project controllers
        projectPanel.resetActionController();
        projectPanel.registerActionController(new ProjectActionController(this, projectPanel, project));

        //Config users panel
        projectUserPanel.setUserList(project.getUsers());
        projectUserPanel.resetActionController();
        projectUserPanel.registerActionController(new ProjectAddUserController(this, projectUserPanel,
                project));
        projectUserPanel.resetMouseController();
        projectUserPanel.registerMouseController(new ProjectRemoveUserController(this, projectUserPanel,
                project));
        projectUserPanel.registerDocumentListener(new DocumentController(projectUserPanel));

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
        editionPanel.registerTaskActionController(new TaskController(this, editionPanel.getTaskPanel(),
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

    public void updatedProject() {
        mainController.updateProject(project);
    }

    public void updatedTask(Task task) {
        mainController.updateTask(project, category, task);
    }

    public void updatedCategory(Category category) {
        mainController.updateCategory(project, category);
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

    public int getCategoryIndex(Category category) {
        return project.getCategoryIndex(category);
    }

    public void swapCategories(int firstCategoryIndex, int secondCategoryIndex) {
        project.swapCategories(firstCategoryIndex, secondCategoryIndex);
        projectPanel.swapCategories(firstCategoryIndex, secondCategoryIndex);
    }

    public void deleteCategory(Category category) {

        int index = project.getCategoryIndex(category);

        if(index >= 0 && index < project.getTotalCategories()) {
            project.removeCategory(category);
            projectPanel.removeCategory(index);
            mainController.updateCategory(project, category);
        }

    }

    public void updateTaskList() {
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoryIndex(category));
        categoryPanel.updateTask(category.getTaskIndex(task), task);
    }

    public void showProjectSelection() {
        mainController.swapPanel(ProjectsMainView.VIEW_TAG);
    }

    public void deleteProject() {
        mainController.deleteProject();
    }

    public void setBackgroundImage(Image image) {
        editionPanel.setBackgroundImage(image);
    }

    public User getUserFromDB(String userName) {
        return mainController.getUserFromDB(userName);
    }

    public void sharedProject(Project project, User user) {
        mainController.shareProject(project, user);
    }

}