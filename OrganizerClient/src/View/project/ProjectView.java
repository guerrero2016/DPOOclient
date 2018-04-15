package View.project;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ProjectView extends JFrame {

    private final static String IMG_PATH = "img/";
    private final static String EDITOR_ICON_FILE = "editor_icon.png";
    private final static String BACKGROUND_ICON_FILE = "background_icon.png";
    private final static String DELETE_ICON_FILE = "delete_icon.png";
    private final static String LEFT_ICON_FILE = "left_icon.png";
    private final static String RIGHT_ICON_FILE = "right_icon.png";
    private final static String BACK_ICON_FILE = "back_icon.png";

    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 600;

    private final static String WINDOW_TITLE = "Organizer";
    private final static String PROJECT_USERS_LIST_TITLE = "Project Users List";
    private final static String TASK_USERS_LIST_TITLE = "Task Users List";

    private Image editorIcon;
    private Image backgroundIcon;
    private Image deleteIcon;
    private Image leftIcon;
    private Image rightIcon;
    private Image backIcon;

    private final BackgroundPanel bpProjectView;
    private final ProjectPanel projectPanel;
    private final UserPanel userPanel;

    private TaskPanel taskPanel;
    private UserPanel taskUserPanel;

    public ProjectView(Project project) throws IOException {

        //Prepare settings
        loadIcons();
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //TODO: Change close operation
        setLocationRelativeTo(null);

        //Main panel
        bpProjectView = new BackgroundPanel();
        bpProjectView.setLayout(new BorderLayout());
        bpProjectView.setBackgroundImage(project.getBackground());
        add(bpProjectView);

        //Project panel
        projectPanel = new ProjectPanel(project, editorIcon, backgroundIcon, deleteIcon, leftIcon, rightIcon);
        bpProjectView.add(projectPanel, BorderLayout.CENTER);

        //User panel
        userPanel = new UserPanel(project.getUsers(), PROJECT_USERS_LIST_TITLE);
        bpProjectView.add(userPanel, BorderLayout.LINE_END);

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
    }

    public void showProjectPanel() {
        taskPanel = null;
        taskUserPanel = null;
        bpProjectView.add(projectPanel, BorderLayout.CENTER);
        bpProjectView.add(userPanel, BorderLayout.LINE_END);
    }

    public void showTaskPanel(Task task) {
        taskPanel = new TaskPanel(task, backIcon, editorIcon, deleteIcon);
        bpProjectView.add(taskPanel, BorderLayout.CENTER);
        taskUserPanel = new UserPanel(task.getUsers(), PROJECT_USERS_LIST_TITLE);
        bpProjectView.add(taskUserPanel, BorderLayout.LINE_END);
    }

    public void setProjectName(String projectName) {
        projectPanel.setProjectName(projectName);
    }

    public void setBackground(Image background) {
        bpProjectView.setBackgroundImage(background);
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
        userPanel.addUser(user);
    }

    public void removeProjectUser(int userIndex) {
        userPanel.removeUser(userIndex);
    }

    public String getNewProjectUser() {
        return userPanel.getNewUser();
    }

    public void cleanNewProjectUser() {
        userPanel.cleanNewUser();
    }

    public void addTaskUser(User user) {
        taskUserPanel.addUser(user);
    }

    public void removeTaskUser(int userIndex) {
        taskUserPanel.removeUser(userIndex);
    }

    public String getNewTaskUser() {
        return taskUserPanel.getNewUser();
    }

    public void cleanNewTaskUser() {
        taskUserPanel.cleanNewUser();
    }

}