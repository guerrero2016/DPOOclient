package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ProjectEditor extends JFrame {

    private final static String IMG_PATH = "img/";
    private final static String EDITOR_ICON = "editor_icon.png";
    private final static String BACKGROUND_ICON = "background_icon.png";

    private final static String WINDOW_TITLE = "Project Editor";
    private final static String CATEGORIES_TITLE = "Categories";
    private final static String NEW_CATEGORY = "New Category";
    private final static String ADD = "ADD";

    private final JButton jbProjectEditor;
    private final JButton jbBackgroundEditor;
    private final JPanel jpCenter;
    private final JTextField jtfNewCategory;
    private final JButton jbNewCategory;

    public ProjectEditor(String projectName) throws IOException {

        //Load icons
        Image editorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON)).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image backgroundIcon = ImageIO.read(new File(IMG_PATH + BACKGROUND_ICON)).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        //Load fonts
        Font bigFont = new Font(Font.DIALOG, Font.BOLD, 20);
        Font mediumFont = new Font(Font.DIALOG, Font.BOLD, 16);
        Font smallFont = new Font(Font.DIALOG, Font.BOLD, 12);

        //Load JFrame
        setSize(600, 400);
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //TODO: Change close operation
        setLocationRelativeTo(null);

        //Main view
        final JPanel jpMainView = new JPanel(new BorderLayout());
        setContentPane(jpMainView);

        //NORTH
        final JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpMainView.add(jpNorth, BorderLayout.PAGE_START);

        //Project name
        final JLabel jlProject = new JLabel(projectName);
        jlProject.setFont(bigFont);
        jlProject.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpNorth.add(jlProject);

        //Project editor
        jbProjectEditor = new JButton(new ImageIcon(editorIcon));
        jbProjectEditor.setBorder(BorderFactory.createEmptyBorder());
        jpNorth.add(jbProjectEditor);

        //Project background editor
        jbBackgroundEditor = new JButton(new ImageIcon(backgroundIcon));
        jbBackgroundEditor.setBorder(BorderFactory.createEmptyBorder());
        jpNorth.add(jbBackgroundEditor);

        //Categories panel
        jpCenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpCenter.setBorder(BorderFactory.createTitledBorder(CATEGORIES_TITLE));
        jpMainView.add(jpCenter, BorderLayout.CENTER);

        //New category panel
        final JPanel jpSouth = new JPanel(new BorderLayout());
        jpMainView.add(jpSouth, BorderLayout.PAGE_END);

        //New category
        final JLabel jlNewCategory = new JLabel(NEW_CATEGORY);
        jlNewCategory.setFont(smallFont);
        jlNewCategory.setBorder(BorderFactory.createEmptyBorder(0,5, 0, 5));
        jpSouth.add(jlNewCategory, BorderLayout.LINE_START);

        jtfNewCategory = new JTextField();
        jpSouth.add(jtfNewCategory, BorderLayout.CENTER);

        jbNewCategory = new JButton(ADD);
        jpSouth.add(jbNewCategory, BorderLayout.LINE_END);

    }

}