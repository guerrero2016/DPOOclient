package View;

import Model.Category;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectEditor extends JFrame {

    private final static String IMG_PATH = "img/";
    private final static String EDITOR_ICON = "editor_icon.png";
    private final static String BACKGROUND_ICON = "background_icon.png";
    private final static String DELETE_ICON = "delete_icon.png";
    private final static String LEFT_ICON = "left_icon.png";
    private final static String RIGHT_ICON = "right_icon.png";
    private final static String UP_ICON = "up_icon.png";
    private final static String DOWN_ICON = "down_icon.png";

    private final static String WINDOW_TITLE = "Project Editor";
    private final static String CATEGORIES_TITLE = "Categories";
    private final static String NEW_CATEGORY = "New Category";
    private final static String ADD = "ADD";
    private final static String DESCRIPTION_TITLE = "Description";

    private final Image mediumEditorIcon;
    private final Image deleteIcon;
    private final Image leftIcon;
    private final Image rightIcon;
    private final Image upIcon;
    private final Image downIcon;

    private final Font bigFont;
    private final Font mediumFont;
    private final Font smallFont;

    private final JButton jbProjectEditor;
    private final JButton jbBackgroundEditor;
    private final CategoryPanel cpCenter;
    private final JTextField jtfNewCategory;
    private final JButton jbNewCategory;

    private JPanel jpCategory;
    private JPanel jpCategoryTitle;
    private JPanel jpTaskTitle;
    private final Image smallEditorIcon;
    private JTextArea jtaDescription;
    private JPanel jpTask;

    public ProjectEditor(String projectName) throws IOException {

        //Load icons
        Image bigEditorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON)).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        mediumEditorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        smallEditorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON)).
                getScaledInstance(12, 12, Image.SCALE_SMOOTH);
        Image backgroundIcon = ImageIO.read(new File(IMG_PATH + BACKGROUND_ICON)).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        deleteIcon = ImageIO.read(new File(IMG_PATH + DELETE_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        leftIcon = ImageIO.read(new File(IMG_PATH + LEFT_ICON)).
                getScaledInstance(14, 16, Image.SCALE_SMOOTH);
        rightIcon = ImageIO.read(new File(IMG_PATH + RIGHT_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        upIcon = ImageIO.read(new File(IMG_PATH + UP_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        downIcon = ImageIO.read(new File(IMG_PATH + DOWN_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);

        //Load fonts
        bigFont = new Font(Font.DIALOG, Font.BOLD, 20);
        mediumFont = new Font(Font.DIALOG, Font.BOLD, 16);
        smallFont = new Font(Font.DIALOG, Font.BOLD, 12);

        //Load JFrame
        setSize(600, 400);
        setResizable(false);
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
        jbProjectEditor = new JButton(new ImageIcon(bigEditorIcon));
        jbProjectEditor.setBorder(BorderFactory.createEmptyBorder());
        jpNorth.add(jbProjectEditor);

        //Project background editor
        jbBackgroundEditor = new JButton(new ImageIcon(backgroundIcon));
        jbBackgroundEditor.setBorder(BorderFactory.createEmptyBorder());
        jpNorth.add(jbBackgroundEditor);

        //CENTER
        cpCenter = new CategoryPanel();
        cpCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
        cpCenter.setBorder(BorderFactory.createTitledBorder(CATEGORIES_TITLE));
        jpMainView.add(cpCenter, BorderLayout.CENTER);

        //SOUTH
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

    public void cleanNewCategoryField() {
        jtfNewCategory.setText(null);
    }

    public void setBackground(Image background) {
        cpCenter.setBackground(background);
        repaint();
    }

    //TODO: Need Category implementation and check if works
    public void updateCategories(ArrayList<Category> categories) {

        cpCenter.removeAll();

        //Category panel
        jpCategory = new JPanel();
        jpCategory.setLayout(new BoxLayout(jpCategory, BoxLayout.PAGE_AXIS));
        jpCategory.setMinimumSize(new Dimension(150, 150));

        //Category title panel
        jpCategoryTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpCategory.add(jpCategoryTitle);

        //TODO: Change params
        //Category title
        final JLabel jlCategoryTitle = new JLabel("Category name");
        jlCategoryTitle.setFont(mediumFont);
        jpCategoryTitle.add(jlCategoryTitle);

        //Editor category button
        final JButton jbCategoryEditor = new JButton(new ImageIcon(mediumEditorIcon));
        jbCategoryEditor.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryEditor);

        //Delete category button
        final JButton jbCategoryDelete = new JButton(new ImageIcon(deleteIcon));
        jbCategoryDelete.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryDelete);

        //Left category button
        final JButton jbCategoryLeft = new JButton(new ImageIcon(leftIcon));
        jbCategoryLeft.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryLeft);

        //Right category button
        final JButton jbCategoryRight = new JButton(new ImageIcon(rightIcon));
        jbCategoryRight.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryRight);





        //Tasks
        jpTask = new JPanel();
        jpTask.setLayout(new BoxLayout(jpTask, BoxLayout.PAGE_AXIS));

        //Task title panel
        jpTaskTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JScrollPane jspTaskTitle = new JScrollPane(jpTaskTitle);
        jpTask.add(jspTaskTitle);

        //TODO: Change params
        //Task title
        final JLabel jlTaskTitle = new JLabel("Task name");
        jlTaskTitle.setFont(mediumFont);
        jpTaskTitle.add(jlTaskTitle);

        //Editor task button
        final JButton jbTaskEditor = new JButton(new ImageIcon(mediumEditorIcon));
        jbTaskEditor.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskEditor);

        //Delete task button
        final JButton jbTaskDelete = new JButton(new ImageIcon(deleteIcon));
        jbTaskDelete.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskDelete);

        //Up task button
        final JButton jbTaskUp = new JButton(new ImageIcon(upIcon));
        jbTaskUp.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskUp);

        //Down task button
        final JButton jbTaskDown = new JButton(new ImageIcon(downIcon));
        jbTaskDown.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskDown);

        //Task description
        final JPanel jpDescription = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpTask.add(jpDescription);

        //Description title
        final JLabel jlDescription = new JLabel(DESCRIPTION_TITLE);
        jlDescription.setFont(smallFont);
        jpDescription.add(jlDescription);

        //Description editor
        final JButton jbDescriptionEditor = new JButton(new ImageIcon(smallEditorIcon));
        jbDescriptionEditor.setBorder(BorderFactory.createEmptyBorder());
        jpDescription.add(jbDescriptionEditor);

        //Description text
        jtaDescription = new JTextArea();
        jtaDescription.setEditable(false);
        jpTask.add(jtaDescription);

        //Task padding panel
        final JPanel jpTaskPadding = new JPanel();
        jpTaskPadding.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jpCategory.add(jpTaskPadding);

        //Task scrollable panel
        final JScrollPane jspTask = new JScrollPane(jpTask);
        jspTask.setBorder(BorderFactory.createLineBorder(Color.black));
        jpTaskPadding.add(jspTask);





        //Category scrollable panel
        final JScrollPane jspCategory = new JScrollPane(jpCategory);
        jspCategory.setBorder(BorderFactory.createLineBorder(Color.black));
        cpCenter.add(jspCategory);

        for(int i = 0; i < categories.size(); i++) {
            //for(int j = 0; j < categories.get(i).getTotalTasks(); j++) {
            //}
        }

    }

    @Override
    public void setVisible(boolean b) {

        super.setVisible(b);

        if(b) {
            jpCategory.setSize(jpCategoryTitle.getWidth(), cpCenter.getHeight() - 35);
            jtaDescription.setPreferredSize(new Dimension(jpTask.getWidth(), 50));
        }

    }

}