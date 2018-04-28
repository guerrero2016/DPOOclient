package View.edition;

import ModelAEliminar.Category;
import ModelAEliminar.Tag;
import ModelAEliminar.User;
import View.edition.project.CategoryPanel;
import View.edition.project.ProjectPanel;
import View.edition.task.TaskPanel;
import View.edition.user.UserPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditionPanel extends BackgroundPanel {

    public final static String PROJECT_PANEL = "ProjectPanel";
    public final static String PROJECT_USER_PANEL = "ProjectUserPanel";
    public final static String TASK_PANEL = "TaskPanel";
    public final static String TASK_USER_PANEL = "TaskUserPanel";

    private final static String IMG_PATH = "img";
    private final static String EDITOR_ICON_FILE = "editor_icon.png";
    private final static String BACKGROUND_ICON_FILE = "background_icon.png";
    private final static String DELETE_ICON_FILE = "delete_icon.png";
    private final static String LEFT_ICON_FILE = "left_icon.png";
    private final static String RIGHT_ICON_FILE = "right_icon.png";
    private final static String BACK_ICON_FILE = "back_icon.png";
    private final static String CHECK_ICON_FILE = "check_icon.png";

    private Image backIcon;
    private Image editorIcon;
    private Image backgroundIcon;
    private Image deleteIcon;
    private Image leftIcon;
    private Image rightIcon;
    private Image checkIcon;

    private final JPanel jpCenter;
    private final JPanel jpRight;
    private final ProjectPanel projectPanel;
    private final UserPanel projectUserPanel;
    private final TaskPanel taskPanel;
    private final UserPanel taskUserPanel;

    public EditionPanel() throws IOException {

        //Main config
        loadIcons();
        setLayout(new BorderLayout());

        //Center panel
        jpCenter = new JPanel(new CardLayout());
        add(jpCenter, BorderLayout.CENTER);

        //Link add panel
        projectPanel = new ProjectPanel(editorIcon, backgroundIcon, deleteIcon, leftIcon, rightIcon);
        jpCenter.add(projectPanel, PROJECT_PANEL);

        //Link remove panel
        taskPanel = new TaskPanel(backIcon, editorIcon, deleteIcon, checkIcon);
        jpCenter.add(taskPanel, TASK_PANEL);

        //Lateral panel
        jpRight = new JPanel(new CardLayout());
        add(jpRight, BorderLayout.LINE_END);

        //Link add user panel
        projectUserPanel = new UserPanel();
        jpRight.add(projectUserPanel, PROJECT_USER_PANEL);

        //Link remove user panel
        taskUserPanel = new UserPanel();
        jpRight.add(taskUserPanel, TASK_USER_PANEL);

    }

    private void loadIcons() throws IOException {
        //Back icon
        backIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + BACK_ICON_FILE));
        //Editor icon
        editorIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + EDITOR_ICON_FILE));
        //Background icon
        backgroundIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + BACKGROUND_ICON_FILE));
        //Delete icon
        deleteIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + DELETE_ICON_FILE));
        //Left icon
        leftIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + LEFT_ICON_FILE));
        //Left icon
        rightIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + RIGHT_ICON_FILE));
        //Check icon
        checkIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + CHECK_ICON_FILE));
    }

    public void setBackground(Image background) {
        setBackgroundImage(background);
    }

    public void setProjectName(String projectName) {
        projectPanel.setProjectName(projectName);
    }

    public int getTotalCategories() {
        return projectPanel.getTotalCategories();
    }

    public void addCategory(Category category) {
        projectPanel.addCategory(category);
    }

    public ProjectPanel getProjectPanel() {
        return projectPanel;
    }

    public void registerProjectActionController(ActionListener actionListener) {
        projectPanel.registerActionController(actionListener);
    }

    public void registerProjectDocumentController(DocumentListener documentListener) {
        projectPanel.registerDocumentController(documentListener);
    }

    public CategoryPanel getCategoryPanel(int categoryIndex) {

        if(categoryIndex < projectPanel.getTotalCategories()) {
            return projectPanel.getCategoryPanel(categoryIndex);
        }

        return null;

    }

    public void registerCategoryActionController(ActionListener actionListener, int categoryIndex) {
        if(categoryIndex < projectPanel.getTotalCategories()) {
            projectPanel.getCategoryPanel(categoryIndex).registerActionController(actionListener);
        }
    }

    public void registerCategoryDocumentController(DocumentListener documentListener, int categoryIndex) {
        if(categoryIndex < projectPanel.getTotalCategories()) {
            projectPanel.getCategoryPanel(categoryIndex).registerDocumentController(documentListener);
        }
    }

    public void registerCategoryMouseController(MouseListener mouseListener, int categoryIndex) {
        if(categoryIndex < projectPanel.getTotalCategories()) {
            projectPanel.getCategoryPanel(categoryIndex).registerMouseController(mouseListener);
        }
    }

    public void setProjectUsersTitle(String userTitle) {
        projectUserPanel.setTitle(userTitle);
    }

    public void setProjectUsersList(ArrayList<User> users) {
        projectUserPanel.setUserList(users);
    }

    public UserPanel getProjectUserPanel() {
        return projectUserPanel;
    }

    public void registerProjectUserActionController(ActionListener actionListener) {
        projectUserPanel.registerActionController(actionListener);
    }

    public void registerProjectUserDocumentController(DocumentListener documentListener) {
        projectUserPanel.registerDocumentListener(documentListener);
    }

    public void registerProjectUserMouseController(MouseListener mouseListener) {
        projectUserPanel.registerMouseController(mouseListener);
    }

    public void showProjectPanel() {
        ((CardLayout) jpCenter.getLayout()).show(jpCenter, PROJECT_PANEL);
        ((CardLayout) jpRight.getLayout()).show(jpRight, PROJECT_USER_PANEL);
    }

    public void setTaskName(String taskName) {
        taskPanel.setTaskName(taskName);
    }

    public void setTaskDescription(String taskDescription) {
        taskPanel.setDescription(taskDescription);
    }

    public void cleanTagsList() {
        taskPanel.cleanTagsList();
    }

    public void setTagsList(ArrayList<Tag> tags) {
        taskPanel.setTagsList(tags);
    }

    public TaskPanel getTaskPanel() {
        return taskPanel;
    }

    public void registerTaskActionController(ActionListener actionListener) {
        taskPanel.resetActionController();
        taskPanel.registerActionController(actionListener);
    }

    public void registerTaskDocumentController(DocumentListener documentListener) {
        taskPanel.registerDocumentController(documentListener);
    }

    public void setTaskUsersTitle(String userTitle) {
        taskUserPanel.setTitle(userTitle);
    }

    public void setTaskUsersList(ArrayList<User> users) {
        taskUserPanel.setUserList(users);
    }

    public int getTotalTags() {
        return taskPanel.getTotalTags();
    }

    public void registerTaskUserActionController(ActionListener actionListener) {
        taskUserPanel.registerActionController(actionListener);
    }

    public void registerTaskUserDocumentController(DocumentListener documentListener) {
        taskUserPanel.registerDocumentListener(documentListener);
    }

    public void registerTaskUserMouseController(MouseListener mouseListener) {
        taskUserPanel.registerMouseController(mouseListener);
    }

    public UserPanel getTaskUserPanel() {
        return taskUserPanel;
    }

    public void showTaskPanel() {
        ((CardLayout) jpCenter.getLayout()).show(jpCenter, TASK_PANEL);
        ((CardLayout) jpRight.getLayout()).show(jpRight, TASK_USER_PANEL);
    }

}