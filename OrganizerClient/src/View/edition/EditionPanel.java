package View.edition;

import Model.*;
import View.edition.project.ProjectPanel;
import View.edition.task.TaskPanel;
import View.edition.user.UserPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EditionPanel extends BackgroundPanel {

    private final static String IMG_PATH = "img/";
    private final static String EDITOR_ICON_FILE = "editor_icon.png";
    private final static String BACKGROUND_ICON_FILE = "background_icon.png";
    private final static String DELETE_ICON_FILE = "delete_icon.png";
    private final static String LEFT_ICON_FILE = "left_icon.png";
    private final static String RIGHT_ICON_FILE = "right_icon.png";
    private final static String BACK_ICON_FILE = "back_icon.png";
    private final static String CHECK_ICON_FILE = "check_icon.png";

    private final static String PROJECT_USERS_LIST_TITLE = "Project Users List";
    private final static String TASK_USERS_LIST_TITLE = "Task Users List";

    private Image editorIcon;
    private Image backgroundIcon;
    private Image deleteIcon;
    private Image leftIcon;
    private Image rightIcon;
    private Image backIcon;
    private Image checkIcon;

    private final ProjectPanel projectPanel;
    private final UserPanel projectUserPanel;

    private TaskPanel taskPanel;
    private UserPanel taskUserPanel;

    public EditionPanel(Project project) throws IOException {

        //Prepare settings
        loadIcons();

        //Main panel
        setLayout(new BorderLayout());
        setBackgroundImage(project.getBackground());

        //Project panel
        projectPanel = new ProjectPanel(project, editorIcon, backgroundIcon, deleteIcon, leftIcon, rightIcon);
        add(projectPanel, BorderLayout.CENTER);

        //User panel
        projectUserPanel = new UserPanel(project.getUsers(), PROJECT_USERS_LIST_TITLE);
        add(projectUserPanel, BorderLayout.LINE_END);

    }

    private void loadIcons() throws IOException {
        //Back icon
        backIcon = ImageIO.read(new File(IMG_PATH + BACK_ICON_FILE));
        //Editor icon
        editorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON_FILE));
        //Background icon
        backgroundIcon = ImageIO.read(new File(IMG_PATH + BACKGROUND_ICON_FILE));
        //Delete icon
        deleteIcon = ImageIO.read(new File(IMG_PATH + DELETE_ICON_FILE));
        //Left icon
        leftIcon = ImageIO.read(new File(IMG_PATH + LEFT_ICON_FILE));
        //Left icon
        rightIcon = ImageIO.read(new File(IMG_PATH + RIGHT_ICON_FILE));
        //Check icon
        checkIcon = ImageIO.read(new File(IMG_PATH + CHECK_ICON_FILE));
    }

    public void showProjectPanel() {
        removeAll();
        taskPanel = null;
        taskUserPanel = null;
        add(projectPanel, BorderLayout.CENTER);
        add(projectUserPanel, BorderLayout.LINE_END);
    }

    public void showTaskPanel(Task task) {
        removeAll();
        taskPanel = new TaskPanel(task, backIcon, editorIcon, deleteIcon, checkIcon);
        add(taskPanel, BorderLayout.CENTER);
        taskUserPanel = new UserPanel(task.getUsers(), TASK_USERS_LIST_TITLE);
        add(taskUserPanel, BorderLayout.LINE_END);
    }

    public void setProjectName(String projectName) {
        projectPanel.setProjectName(projectName);
    }

    public void setBackground(Image background) {
        setBackgroundImage(background);
    }

    public String getNewCategoryName() {
        return projectPanel.getNewCategoryName();
    }

    public void cleanNewCategoryName() {
        projectPanel.cleanNewCategoryName();
    }

    public void addNewCategory(Category category) {
        projectPanel.addCategory(category);
    }

    public void removeCategory(int categoryIndex) {
        projectPanel.removeCategory(categoryIndex);
    }

    public void setCategoryName(int categoryIndex, String categoryName) {
        projectPanel.setCategoryName(categoryIndex, categoryName);
    }

    public void swapCategories(int firstCategoryIndex, int secondCategoryIndex) {
        projectPanel.swapCategories(firstCategoryIndex, secondCategoryIndex);
    }

    public String getNewTaskName(int categoryIndex) {
        return projectPanel.getNewTaskName(categoryIndex);
    }

    public void cleanNewTaskName(int categoryIndex) {
        projectPanel.cleanNewTaskName(categoryIndex);
    }

    public Task getSelectedTask(int categoryIndex) {
        return projectPanel.getSelectedTask(categoryIndex);
    }

    public void addNewTask(Task task, int categoryIndex) {
        projectPanel.addNewTask(task, categoryIndex);
    }

    public void removeTask(int categoryIndex, int taskIndex) {
        projectPanel.removeTask(categoryIndex, taskIndex);
    }

    public void addProjectUser(User user) {
        projectUserPanel.addUser(user);
    }

    public void removeProjectUser(int userIndex) {
        projectUserPanel.removeUser(userIndex);
    }

    public String getNewProjectUser() {
        return projectUserPanel.getNewUser();
    }

    public void cleanNewProjectUser() {
        projectUserPanel.cleanNewUser();
    }

    public void addTaskUser(User user) {
        if(taskUserPanel != null) {
            taskUserPanel.addUser(user);
        }
    }

    public void removeTaskUser(int userIndex) {
        if(taskUserPanel != null) {
            taskUserPanel.removeUser(userIndex);
        }
    }

    public String getNewTaskUser() {

        if (taskUserPanel != null) {
            return taskUserPanel.getNewUser();
        }

        return null;

    }

    public void cleanNewTaskUser() {
        if(taskUserPanel != null) {
            taskUserPanel.cleanNewUser();
        }
    }

    public void setTaskName(String taskName) {
        if(taskPanel != null) {
            taskPanel.setTaskName(taskName);
        }
    }

    public String getDescription() {

        if(taskPanel != null) {
            return taskPanel.getDescription();
        }

        return null;

    }

    public void setDescription(String description) {
        if(taskPanel != null) {
            taskPanel.setDescription(description);
        }
    }

    public void addNewTag(Tag tag) {
        if(taskPanel != null) {
            taskPanel.addTag(tag);
        }
    }

    public void removeTag(int tagIndex) {
        if(taskPanel != null) {
            taskPanel.removeTag(tagIndex);
        }
    }

    public void setTagName(int tagIndex, String tagName) {
        if(taskPanel != null) {
            taskPanel.setTagName(tagIndex, tagName);
        }
    }

    public void setTagColor(int tagIndex, Color tagColor) {
        if(taskPanel != null) {
            taskPanel.setTagColor(tagIndex, tagColor);
        }
    }

    public String getNewTagName() {

        if(taskPanel != null) {
            return taskPanel.getNewTagName();
        }

        return null;

    }

    public void cleanNewTagName() {
        if(taskPanel != null) {
            taskPanel.cleanNewTagName();
        }
    }

    public void setTagAdderButtonState(boolean buttonState) {
        if(taskPanel != null) {
            taskPanel.setTagAdderButtonState(buttonState);
        }
    }

    public void setCategoryAdderButtonState(boolean buttonState) {
        projectPanel.setCategoryAdderButtonState(buttonState);
    }

    public void setTaskAdderButtonState(int categoryIndex, boolean buttonState) {
        projectPanel.setTaskAdderButtonState(categoryIndex, buttonState);
    }

    public void setProjectUserAddButtonState(boolean buttonState) {
        projectUserPanel.setUserAddButtonState(buttonState);
    }

    public void setTaskUserAddButtonState(boolean buttonState) {
        taskUserPanel.setUserAddButtonState(buttonState);
    }

    public void setTaskDescriptionState(boolean descriptionState) {
        if(taskPanel != null) {
            taskPanel.setDescriptionState(descriptionState);
        }
    }

}