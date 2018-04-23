package Controller.edition;

import Controller.MainViewController;
import Controller.edition.project.NewCategoryDocumentController;
import Controller.edition.project.NewTaskDocumentController;
import Controller.edition.project.ProjectActionController;
import Controller.edition.project.ProjectMouseController;
import Controller.edition.task.TaskActionController;
import Controller.edition.task.TaskDocumentController;
import Controller.edition.user.UserActionController;
import Controller.edition.user.UserDocumentListener;
import Controller.edition.user.UserMouseController;
import Model.Project;
import Model.Task;
import View.edition.EditionPanel;
import View.edition.project.ProjectPanel;
import View.edition.task.TaskPanel;
import View.edition.user.UserPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EditionController {

    private final static String IMG_PATH = "img";
    private final static String EDITOR_ICON_FILE = "editor_icon.png";
    private final static String BACKGROUND_ICON_FILE = "background_icon.png";
    private final static String DELETE_ICON_FILE = "delete_icon.png";
    private final static String LEFT_ICON_FILE = "left_icon.png";
    private final static String RIGHT_ICON_FILE = "right_icon.png";
    private final static String BACK_ICON_FILE = "back_icon.png";
    private final static String CHECK_ICON_FILE = "check_icon.png";

    private final static String PROJECT_USERS_TITLE = "Project Users";
    private final static String TASK_USERS_TITLE = "Task Users";

    private MainViewController mainController;
    private EditionPanel editionPanel;

    private Image backIcon;
    private Image editorIcon;
    private Image backgroundIcon;
    private Image deleteIcon;
    private Image leftIcon;
    private Image rightIcon;
    private Image checkIcon;

    public EditionController (MainViewController mainController, EditionPanel editionPanel) throws IOException {
        this.mainController = mainController;
        this.editionPanel = editionPanel;
        loadIcons();
    }

    public void setProjectContent(Project project) {

        if(project.getBackground() != null) {
            editionPanel.setBackground(project.getBackground());
        }

        //Project
        ProjectPanel projectPanel = new ProjectPanel(project, editorIcon, backgroundIcon, deleteIcon, leftIcon, rightIcon);
        projectPanel.registerActionController(new ProjectActionController(this, projectPanel));
        projectPanel.registerMouseController(new ProjectMouseController(this, projectPanel));
        projectPanel.registerDocumentController(new NewCategoryDocumentController(projectPanel));

        for(int i = 0; i < projectPanel.getTotalCategories(); i++) {
            projectPanel.getCategoryPanel(i).registerDocumentController(new NewTaskDocumentController(projectPanel.
                    getCategoryPanel(i)));
        }

        editionPanel.setMainPanel(projectPanel);

        //User
        UserPanel userPanel = new UserPanel(project.getUsers(), PROJECT_USERS_TITLE);
        userPanel.registerActionController(new UserActionController(this, userPanel));
        userPanel.registerMouseListener(new UserMouseController(this, userPanel));
        userPanel.registerDocumentListener(new UserDocumentListener(userPanel));
        editionPanel.setLateralPanel(userPanel);

    }

    public void setTaskContent(Task task) {

        //TODO: Link task controllers
        TaskPanel taskPanel = new TaskPanel(task, backIcon, editorIcon, deleteIcon, checkIcon);
        taskPanel.registerActionController(new TaskActionController(this, taskPanel));
        taskPanel.registerDocumentController(new TaskDocumentController(taskPanel));
        editionPanel.setMainPanel(taskPanel);

        //User
        UserPanel userPanel = new UserPanel(task.getUsers(), TASK_USERS_TITLE);
        userPanel.registerActionController(new UserActionController(this, userPanel));
        userPanel.registerMouseListener(new UserMouseController(this, userPanel));
        userPanel.registerDocumentListener(new UserDocumentListener(userPanel));
        editionPanel.setLateralPanel(userPanel);

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

}