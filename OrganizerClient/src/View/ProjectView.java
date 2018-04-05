package View;

import Model.Category;
import Model.Task;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectView extends JFrame {

    private final static int MAX_PROJECT_LENGTH = 30;
    private final static int MAX_CATEGORY_LENGTH = 15;

    private final static String IMG_PATH = "img/";
    private final static String EDITOR_ICON_FILE = "editor_icon.png";
    private final static String DELETE_ICON_FILE = "delete_icon.png";
    private final static String LEFT_ICON_FILE = "left_icon.png";
    private final static String RIGHT_ICON_FILE = "right_icon.png";

    private final static int MARGIN = 30;
    private final static int CATEGORY_WIDTH = 300;

    private final static String PROJECT_TITLE = "Project";
    private final static String NEW_CATEGORY_TITLE = "New Category";
    private final static String NEW_TASK_TITLE = "New Task";
    private final static String ADD_TITLE = "+";

    private final Image bigEditorIcon;
    private final Image mediumEditorIcon;

    private final Image bigDeleteIcon;
    private final Image mediumDeleteIcon;

    private final Font bigFont;
    private final Font mediumFont;

    private final JPanel jpCategories;
    private final JScrollPane jspCategories;
    private final JLabel jlProjectName;
    private final JButton jbProjectEditor;
    private final JButton jbProjectDelete;
    private final JTextField jtfCategoryName;
    private final JButton jbCategoryAdder;

    private ArrayList<CategoryContent> categoriesContent;
    private final Image mediumLeftIcon;
    private final Image mediumRightIcon;

    public ProjectView(String projectName) throws IOException {

        setSize(new Dimension(800, 500));
        setResizable(false);
        setTitle(PROJECT_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //TODO: Change close operation
        setLocationRelativeTo(null);

        //Load editor icon
        bigEditorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON_FILE)).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        mediumEditorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON_FILE)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);

        //Load delete icon
        bigDeleteIcon = ImageIO.read(new File(IMG_PATH + DELETE_ICON_FILE)).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        mediumDeleteIcon = ImageIO.read(new File(IMG_PATH + DELETE_ICON_FILE)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);

        //Load order icons
        mediumLeftIcon = ImageIO.read(new File(IMG_PATH + LEFT_ICON_FILE)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        mediumRightIcon = ImageIO.read(new File(IMG_PATH + RIGHT_ICON_FILE)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);

        //Load fonts
        bigFont = new Font(Font.DIALOG, Font.BOLD, 20);
        mediumFont = new Font(Font.DIALOG, Font.BOLD, 16);

        //Main panel
        final JPanel jpMainView = new JPanel(new BorderLayout());
        getContentPane().add(jpMainView);

        //Project title
        final JPanel jpProjectTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpMainView.add(jpProjectTitle, BorderLayout.PAGE_START);

        //TODO: Change params
        //Project name
        jlProjectName = new JLabel();

        if(projectName.length() > MAX_PROJECT_LENGTH) {
            String shortProjectName = projectName.substring(0, MAX_PROJECT_LENGTH) + "...";
            jlProjectName.setText(shortProjectName);
        } else {
            jlProjectName.setText(projectName);
        }

        jlProjectName.setFont(bigFont);
        jlProjectName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        jpProjectTitle.add(jlProjectName);

        //Project editor button
        jbProjectEditor = new JButton(new ImageIcon(bigEditorIcon));
        jbProjectEditor.setBorder(BorderFactory.createEmptyBorder());
        jpProjectTitle.add(jbProjectEditor);

        //Project delete button
        jbProjectDelete = new JButton(new ImageIcon(bigDeleteIcon));
        jbProjectDelete.setBorder(BorderFactory.createEmptyBorder());
        jpProjectTitle.add(jbProjectDelete);

        //Categories panel
        jspCategories = new JScrollPane();
        jpMainView.add(jspCategories, BorderLayout.CENTER);

        jpCategories = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jspCategories.getViewport().setView(jpCategories);

        //Category adder panel
        final JPanel jpNewCategory = new JPanel(new BorderLayout());
        jpMainView.add(jpNewCategory, BorderLayout.PAGE_END);

        //New category title
        final JLabel jlCategoryTitle = new JLabel(NEW_CATEGORY_TITLE);
        jlCategoryTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        jpNewCategory.add(jlCategoryTitle, BorderLayout.LINE_START);

        //New category name field
        jtfCategoryName = new JTextField();
        jtfCategoryName.setEditable(true);
        jpNewCategory.add(jtfCategoryName, BorderLayout.CENTER);

        //New category adder button
        jbCategoryAdder = new JButton(ADD_TITLE);
        jpNewCategory.add(jbCategoryAdder, BorderLayout.LINE_END);

        //Prepare for future categories
        categoriesContent = new ArrayList<>();

    }

    public void setProjectName(String projectName) {
        if(projectName.length() > MAX_PROJECT_LENGTH) {
            String shortProjectName = projectName.substring(0, MAX_PROJECT_LENGTH) + "...";
            jlProjectName.setText(shortProjectName);
        } else {
            jlProjectName.setText(projectName);
        }
    }

    public void setCategoryName(String categoryName, int categoryPosition) {
        if(categoryPosition < categoriesContent.size()) {

            JLabel jlCategoryName = categoriesContent.get(categoryPosition).getJlCategoryName();

            if(categoryName.length() > MAX_CATEGORY_LENGTH) {
                String shortCategoryName = categoryName.substring(0, MAX_CATEGORY_LENGTH) + "...";
                jlCategoryName.setText(shortCategoryName);
            } else {
                jlCategoryName.setText(categoryName);
            }

        }
    }

    public void removeCategory(int categoryPosition) {
        if(categoryPosition < categoriesContent.size()) {
            categoriesContent.remove(categoryPosition);
            jpCategories.remove(categoryPosition);
        }
    }

    public void swapCategoriesPosition(int categoryPosition1, int categoryPosition2) {
        if(categoryPosition1 < categoriesContent.size() && categoryPosition2 < categoriesContent.size()) {

            //Change panel data order
            CategoryContent categoryContent1 = categoriesContent.get(categoryPosition1);
            CategoryContent categoryContent2 = categoriesContent.get(categoryPosition2);
            categoriesContent.add(categoryPosition1, categoryContent2);
            categoriesContent.add(categoryPosition2, categoryContent1);

            //Change panels
            JPanel jpCategory1 = categoriesContent.get(categoryPosition1).getJpCategory();
            JPanel jpCategory2 = categoriesContent.get(categoryPosition2).getJpCategory();
            jpCategories.add(jpCategory1, categoryPosition1);
            jpCategories.add(jpCategory2, categoryPosition2);

        }
    }

    public int getSelectedTask(int categoryPosition) {

        if(categoryPosition < categoriesContent.size()) {

            JList<String> jlTasks = categoriesContent.get(categoryPosition).getJlTasks();

            if(jlTasks.isSelectionEmpty()) {
                return -1;
            }

            return jlTasks.getSelectedIndex();

        }

        return -1;

    }

    public void setTasksList(ArrayList<Task> tasks, int categoryPosition) {
        if(categoryPosition < categoriesContent.size()) {

            JList<String> jlTasks = categoriesContent.get(categoryPosition).getJlTasks();
            //TODO: Change params
            String taskList[] = {""};
            jlTasks.setListData(taskList);

        }
    }

    public String getNewTaskName(int categoryPosition) {

        if(categoryPosition < categoriesContent.size()) {
            JTextField jtfTaskName = categoriesContent.get(categoryPosition).getJtfTaskName();
            return jtfTaskName.getText();
        }

        return null;

    }

    public void cleanNewTaskName(int categoryPosition) {
        if(categoryPosition < categoriesContent.size()) {
            JTextField jtfTaskName = categoriesContent.get(categoryPosition).getJtfTaskName();
            jtfTaskName.setText(null);
        }
    }

    public String getNewCategoryName() {
        return jtfCategoryName.getText();
    }

    public void cleanNewCategoryName() {
        jtfCategoryName.setText(null);
    }

    public void addNewCategory(Category category) {

        //New category
        CategoryContent categoryContent = new CategoryContent();

        //Category panel
        final JPanel jpCategory = new JPanel(new BorderLayout());
        jpCategory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jpCategory.setPreferredSize(new Dimension(CATEGORY_WIDTH, jspCategories.getHeight() - MARGIN));
        jpCategories.add(jpCategory);
        categoryContent.setJpCategory(jpCategory);

        //Category title panel
        final JPanel jpCategoryTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpCategory.add(jpCategoryTitle, BorderLayout.PAGE_START);

        //TODO: Change params
        //Category name
        final JLabel jlCategoryName = new JLabel("Category name");
        jlCategoryName.setFont(mediumFont);
        jlCategoryName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        jpCategoryTitle.add(jlCategoryName);
        categoryContent.setJlCategoryName(jlCategoryName);

        //Category editor button
        final JButton jbCategoryEditor = new JButton(new ImageIcon(mediumEditorIcon));
        jbCategoryEditor.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryEditor);
        categoryContent.setJbCategoryEditor(jbCategoryEditor);

        //Category delete button
        final JButton jbCategoryDelete = new JButton(new ImageIcon(mediumDeleteIcon));
        jbCategoryDelete.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryDelete);
        categoryContent.setJbCategoryDelete(jbCategoryDelete);

        //Category reorder left button
        final JButton jbCategoryLeft = new JButton(new ImageIcon(mediumLeftIcon));
        jbCategoryLeft.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryLeft);
        categoryContent.setJbCategoryLeft(jbCategoryLeft);

        //Category reorder right button
        final JButton jbCategoryRight = new JButton(new ImageIcon(mediumRightIcon));
        jbCategoryRight.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryRight);
        categoryContent.setJbCategoryRight(jbCategoryRight);

        //Scrollable task list
        final JScrollPane jspCategories = new JScrollPane();
        jpCategory.add(jspCategories, BorderLayout.CENTER);

        //TODO: Delete sample list and change list representation
        //Task list
        int length = 50;
        String list[] = new String[length];
        for(int i = 0; i < list.length; i++) {
            list[i] = "Task very long to handle it without scroll " + (i + 1);
        }
        final JList<String> jlTasks = new JList<>(list);
        jlTasks.setFont(mediumFont);
        jspCategories.getViewport().setView(jlTasks);
        categoryContent.setJlTasks(jlTasks);

        //Task adder panel
        final JPanel jpTaskAdder = new JPanel(new BorderLayout());
        jpCategory.add(jpTaskAdder, BorderLayout.PAGE_END);

        //New task title
        final JLabel jlTaskTitle = new JLabel(NEW_TASK_TITLE);
        jlTaskTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        jpTaskAdder.add(jlTaskTitle, BorderLayout.LINE_START);

        //New task name field
        final JTextField jtfTaskName = new JTextField();
        jtfTaskName.setEditable(true);
        jpTaskAdder.add(jtfTaskName);
        categoryContent.setJtfTaskName(jtfTaskName);

        //New task adder button
        final JButton jbTaskAdder = new JButton(ADD_TITLE);
        jpTaskAdder.add(jbTaskAdder, BorderLayout.LINE_END);
        categoryContent.setJbTaskAdder(jbTaskAdder);

        //Add category
        categoriesContent.add(categoryContent);

    }

    public void resizePanels() {
        for (int i = 0; i < categoriesContent.size(); i++) {
            JPanel jpCategory = categoriesContent.get(i).getJpCategory();
            jpCategory.setPreferredSize(new Dimension(CATEGORY_WIDTH, jspCategories.getHeight() - MARGIN));
        }
    }

    @Override
    public void setVisible(boolean visibility) {

        super.setVisible(visibility);

        if(visibility) {
            Thread updateView = new Thread(new ResizableProjectContent(this));
            updateView.start();
        }

    }

}