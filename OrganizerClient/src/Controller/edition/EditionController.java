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
import Network.Communicators.CategorySetCommunicator;
import View.ProjectsMainView;
import model.ServerObjectType;
import model.project.Category;
import model.project.Project;
import model.project.Tag;
import model.project.Task;
import model.user.User;
import View.edition.EditionPanel;
import View.edition.project.category.CategoryPanel;
import View.edition.project.ProjectPanel;
import View.edition.task.TaskPanel;
import View.edition.task.tag.TagPanel;
import View.edition.user.UserPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditionController {

    public final static String EDITING_ON_MESSAGE = "You should finish editing before doing something else";
    public final static String EDITING_ON_TITLE = "Information";

    private final static String PROJECT_USERS_TITLE = "Project Users";
    private final static String TASK_USERS_TITLE = "Task Users";

    private EditionPanel editionPanel;
    private ProjectPanel projectPanel;
    private UserPanel projectUserPanel;
    private TaskPanel taskPanel;
    private UserPanel taskUserPanel;

    private Project project;
    private Category category;
    private Task task;

    private boolean isEditing;

    private MainViewController mainController;

    public EditionController(EditionPanel editionPanel) {

        //Link variables
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

    public MainViewController getMainController() {
        return mainController;
    }

    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }

    public void addCategory(Category category) {
        if(!this.isEditing() && !projectPanel.getNewCategoryName().isEmpty()) {
            projectPanel.cleanNewCategoryName();
            projectPanel.addCategoryToView(category);
            project.setCategory(category);
            System.out.println(project.getCategoriesSize());
            CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoriesSize() - 1);
            categoryPanel.registerActionController(new CategoryActionController(this, categoryPanel, category));
            categoryPanel.registerMouseController(new CategoryMouseController(this, category));
            categoryPanel.registerDocumentController(new DocumentController(categoryPanel));
        } else if(this.isEditing()) {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }
    }

    public void loadProject(Project project) {

        //Default config
        this.project = project;
        category = null;
        task = null;

        //Config project content
        projectPanel.hideDeleteButton(!project.isOwner());
        editionPanel.setBackgroundImage(project.getBackground());
        projectPanel.setProjectName(project.getName());
        projectPanel.cleanCategories();

        for(int i = 0; i < project.getCategoriesSize(); i++) {
            projectPanel.addCategoryToView(project.getCategory(i));
            CategoryPanel categoryPanel = projectPanel.getCategoryPanel(i);
            categoryPanel.resetActionController();
            System.out.println(project.getCategory(i).getId());
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
        taskPanel.resetActionController();
        taskPanel.registerActionController(new TaskController(this, editionPanel.getTaskPanel(),
                task));

        for(int i = 0; i < task.getTagsSize(); i++) {
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

    public void updateProject() {
        if(mainController != null) {
            try {
                System.out.println("Se envia" + project.getName());
                mainController.sendToServer(ServerObjectType.SET_PROJECT, project);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateTask(Task task) {
        if(mainController != null) {
            try {
                mainController.sendToServer(ServerObjectType.SET_TASK, category.getId());
                mainController.sendToServer(null, task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCategory(Category category) {
        if(mainController != null) {
            try {
                category.setOrder(project.getCategoriesSize());
                mainController.addComunicator(new CategorySetCommunicator(), ServerObjectType.SET_CATEGORY);
                mainController.sendToServer(ServerObjectType.SET_CATEGORY, category);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTask() {

        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoryIndex(category));
        categoryPanel.removeTask(task);
        category.deleteTask(task);

        if(mainController != null) {
            //TODO: Delete task in database
        }

    }

    public void deleteTag(Tag tag) {

        taskPanel.removeTag(task.getTagIndex(tag));

        if(mainController != null) {
            //TODO: Delete tag in database
        }

    }

    public User getProjectUser(String userName) {

        for(int i = 0; i < project.getUsers().size(); i++) {
            if(userName.equals(project.getUser(i).getUserName())) {
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

        if(index >= 0 && index < project.getCategoriesSize()) {

            project.deleteCategory(category);
            projectPanel.removeCategory(index);

            if(mainController != null) {
                //TODO: Delete category in database
            }

        }

    }

    public void updateTaskList() {
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoryIndex(category));
        categoryPanel.updateTask(category.getTaskIndex(task), task);
    }

    public void showProjectSelection() {
        if (mainController != null) {
            //TODO: Save changes
            mainController.swapPanel(ProjectsMainView.VIEW_TAG);
        } else {
            System.exit(0);
        }
    }

    public void deleteProject() {
        if(mainController != null) {
            //TODO: Delete project in database
        } else {
            System.exit(0);
        }
    }

    public void setBackgroundImage(Image image) {
        editionPanel.setBackgroundImage(image);
    }

    public User getUserFromDB(String userName) {
        if(mainController != null) {
            return mainController.getUserFromDB(userName);
        } else {
            return new User(userName);
        }
    }

    public void registerMainController(MainViewController mainController) {
        this.mainController = mainController;
    }

}