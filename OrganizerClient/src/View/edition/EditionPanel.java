package View.edition;

import View.edition.project.ProjectPanel;
import View.edition.task.TaskPanel;
import View.edition.user.UserPanel;
import View.utils.BackgroundPanel;
import View.utils.TransparentPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe encarregada de generar un panell on es mostraran tots els detalls d'un projecte
 */
public class EditionPanel extends BackgroundPanel {

    private final static String PROJECT_PANEL = "ProjectPanel";
    private final static String PROJECT_USER_PANEL = "ProjectUserPanel";
    private final static String TASK_PANEL = "TaskPanel";
    private final static String TASK_USER_PANEL = "TaskUserPanel";

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

    private final TransparentPanel tpCenter;
    private final TransparentPanel tpRight;
    private final ProjectPanel projectPanel;
    private final UserPanel projectUserPanel;
    private final TaskPanel taskPanel;
    private final UserPanel taskUserPanel;

    /**
     * Constructor de la vista buida
     */
    public EditionPanel() {

        //Main config
        loadIcons();
        setLayout(new BorderLayout());

        //Center panel
        tpCenter = new TransparentPanel();
        tpCenter.setLayout(new CardLayout());
        add(tpCenter, BorderLayout.CENTER);

        //Link add panel
        projectPanel = new ProjectPanel(editorIcon, backgroundIcon, deleteIcon, leftIcon, rightIcon, checkIcon, backIcon);
        tpCenter.add(projectPanel, PROJECT_PANEL);

        //Link remove panel
        taskPanel = new TaskPanel(backIcon, editorIcon, deleteIcon, checkIcon);
        tpCenter.add(taskPanel, TASK_PANEL);

        //Lateral panel
        tpRight = new TransparentPanel();
        tpRight.setLayout(new CardLayout());
        add(tpRight, BorderLayout.LINE_END);

        //Link add user panel
        projectUserPanel = new UserPanel(0);
        tpRight.add(projectUserPanel, PROJECT_USER_PANEL);

        //Link remove user panel
        taskUserPanel = new UserPanel(1);
        tpRight.add(taskUserPanel, TASK_USER_PANEL);

    }

    /**
     * Metode encarregat de carregar els icones per evitar futres carregues
     */
    private void loadIcons() {

        //Back icon
        try {
            backIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + BACK_ICON_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Editor icon
        try {
            editorIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + EDITOR_ICON_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Background icon
        try {
            backgroundIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + BACKGROUND_ICON_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Delete icon
        try {
            deleteIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + DELETE_ICON_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Left icon
        try {
            leftIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + LEFT_ICON_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Left icon
        try {
            rightIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + RIGHT_ICON_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Check icon
        try {
            checkIcon = ImageIO.read(new File(IMG_PATH + System.getProperty("file.separator") + CHECK_ICON_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metode que permet establir una imatge de fons
     * @param background Imatge de fons
     */
    public void setBackgroundImage(Image background) {
        super.setBackgroundImage(background);
    }

    /**
     * Getter del panell del projecte
     * @return Panell del projecte
     */
    public ProjectPanel getProjectPanel() {
        return projectPanel;
    }

    /**
     * Getter del panell d'usuaris del projecte
     * @return Panell d'usuaris del projecte
     */
    public UserPanel getProjectUserPanel() {
        return projectUserPanel;
    }

    /**
     * Metode encarregat de mostrar el panell del projecte
     */
    public void showProjectPanel() {
        ((CardLayout) tpCenter.getLayout()).show(tpCenter, PROJECT_PANEL);
        ((CardLayout) tpRight.getLayout()).show(tpRight, PROJECT_USER_PANEL);
    }

    /**
     * Getter del panell de la tasca
     * @return Panel de la tasca
     */
    public TaskPanel getTaskPanel() {
        return taskPanel;
    }

    /**
     * Getter del panell d'suaris de la tasca
     * @return Panell d'suaris de la tasca
     */
    public UserPanel getTaskUserPanel() {
        return taskUserPanel;
    }

    /**
     * Metode encarregat de mostrar el panell de la tasca
     */
    public void showTaskPanel() {
        ((CardLayout) tpCenter.getLayout()).show(tpCenter, TASK_PANEL);
        ((CardLayout) tpRight.getLayout()).show(tpRight, TASK_USER_PANEL);
    }

}