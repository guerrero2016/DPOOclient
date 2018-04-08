package View.project;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectView extends JFrame {

    private final static String IMG_PATH = "img/";
    private final static String EDITOR_ICON_FILE = "editor_icon.png";
    private final static String DELETE_ICON_FILE = "delete_icon.png";
    private final static String LEFT_ICON_FILE = "left_icon.png";
    private final static String RIGHT_ICON_FILE = "right_icon.png";

    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 500;
    private final static String WINDOW_TITLE = "Project";

    private final static int MARGIN = 30;
    private final static int CATEGORY_WIDTH = 300;

    private final static int MAX_PROJECT_LENGTH = 30;
    private final static int MAX_CATEGORY_LENGTH = 15;

    private final static String NEW_CATEGORY_TITLE = "New Category";
    private final static String NEW_TASK_TITLE = "New Task";
    private final static String ADD_TITLE = "+";

    private Image bigEditorIcon;
    private Image mediumEditorIcon;

    private Image bigDeleteIcon;
    private Image mediumDeleteIcon;

    private Image mediumLeftIcon;
    private Image mediumRightIcon;

    private Font bigFont;
    private Font mediumFont;

    private final BackgroundPanel jpCategories;
    private final JScrollPane jspCategories;
    private final JLabel jlProjectName;
    private final JButton jbProjectEditor;
    private final JButton jbProjectDelete;
    private final JTextField jtfCategoryName;
    private final JButton jbCategoryAdder;

    private ArrayList<CategoryContent> categoriesContent;

    public ProjectView(Project project) throws IOException {

        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setResizable(false);
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //TODO: Change close operation
        setLocationRelativeTo(null);

        //Load settings
        loadIcons();
        loadFonts();

        //Main panel
        final JPanel jpMainView = new JPanel(new BorderLayout());
        getContentPane().add(jpMainView);

        //Project title
        final JPanel jpProjectTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpMainView.add(jpProjectTitle, BorderLayout.PAGE_START);

        //Project name
        jlProjectName = new JLabel();

        //TODO: Change params
        String projectName = "Project name";
        if(projectName.length() > MAX_PROJECT_LENGTH) {
            String shortProjectName = projectName.substring(0, MAX_PROJECT_LENGTH) + "...";
            jlProjectName.setText(shortProjectName);
        } else {
            jlProjectName.setText(projectName);
        }

        jlProjectName.setFont(bigFont);
        jlProjectName.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
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

        jpCategories = new BackgroundPanel();
        jpCategories.setLayout(new FlowLayout(FlowLayout.LEFT));
        jspCategories.getViewport().setView(jpCategories);

        //Category adder panel
        final JPanel jpNewCategory = new JPanel(new BorderLayout());
        jpMainView.add(jpNewCategory, BorderLayout.PAGE_END);

        //New category title
        final JLabel jlCategoryTitle = new JLabel(NEW_CATEGORY_TITLE);
        jlCategoryTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpNewCategory.add(jlCategoryTitle, BorderLayout.LINE_START);

        //New category name field
        jtfCategoryName = new JTextField();
        jtfCategoryName.setEditable(true);
        jpNewCategory.add(jtfCategoryName, BorderLayout.CENTER);

        //New category adder button
        jbCategoryAdder = new JButton(ADD_TITLE);
        jpNewCategory.add(jbCategoryAdder, BorderLayout.LINE_END);

        //Categories
        categoriesContent = new ArrayList<>();

        //TODO: Change 'i' limit and change params
        for(int i = 0; i < 0; i++) {
            addNewCategory(null);
        }

    }

    private void loadIcons() throws IOException {

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

    }

    private void loadFonts() {
        bigFont = new Font(Font.DIALOG, Font.BOLD, 20);
        mediumFont = new Font(Font.DIALOG, Font.BOLD, 16);
    }

    public void setProjectName(String projectName) {
        if(projectName.length() > MAX_PROJECT_LENGTH) {
            String shortProjectName = projectName.substring(0, MAX_PROJECT_LENGTH) + "...";
            jlProjectName.setText(shortProjectName);
        } else {
            jlProjectName.setText(projectName);
        }
    }

    public void setBackground(Image background) {
        jpCategories.setBackground(background);
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

            //Update panels
            JPanel jpCategory1 = categoriesContent.get(categoryPosition1).getJpCategory();
            JPanel jpCategory2 = categoriesContent.get(categoryPosition2).getJpCategory();
            jpCategories.add(jpCategory1, categoryPosition1);
            jpCategories.add(jpCategory2, categoryPosition2);

        }
    }

    public Task getSelectedTask(int categoryPosition) {

        if(categoryPosition < categoriesContent.size()) {

            JList<Task> jlTasks = categoriesContent.get(categoryPosition).getJlTasks();

            if(jlTasks.isSelectionEmpty()) {
                return null;
            }

            return jlTasks.getSelectedValue();

        }

        return null;

    }

    public void addTask(Task task, int categoryPosition) {
        if(categoryPosition < categoriesContent.size()) {
            JList<Task> jlTasks = categoriesContent.get(categoryPosition).getJlTasks();
            jlTasks.add(new TaskList.TaskListComponent(task, mediumFont));
        }
    }

    public void removeTask(int categoryPosition, int taskPosition) {
        if(categoryPosition < categoriesContent.size()) {

            JList<Task> jlTasks = categoriesContent.get(categoryPosition).getJlTasks();

            if(taskPosition < jlTasks.getMaxSelectionIndex()) {
                jlTasks.remove(taskPosition);
            }

        }
    }

    public void swapTasksPosition(int categoryPosition, int taskPosition1, int taskPosition2) {
        if(categoryPosition < categoriesContent.size()) {

            JList<Task> jlTasks = categoriesContent.get(categoryPosition).getJlTasks();

            if(taskPosition1 < jlTasks.getMaxSelectionIndex() && taskPosition2 < jlTasks.getMaxSelectionIndex()) {

                DefaultListModel<Task> tasksList = (DefaultListModel<Task>) jlTasks.getModel();

                //Change tasks order
                Task task1 = tasksList.getElementAt(taskPosition1);
                Task task2 = tasksList.getElementAt(taskPosition2);
                tasksList.add(taskPosition2, task1);
                tasksList.add(taskPosition1, task2);

                //Update list
                jlTasks.setModel(tasksList);

            }

        }
    }

    public void setTasksList(ArrayList<Task> tasks, int categoryPosition) {
        if(categoryPosition < categoriesContent.size()) {

            JList<Task> jlTasks = categoriesContent.get(categoryPosition).getJlTasks();
            DefaultListModel<Task> tasksList = new DefaultListModel<>();

            for(int i = 0; tasks != null && i < tasks.size(); i++) {
                tasksList.addElement(tasks.get(i));
            }

            jlTasks.setModel(tasksList);

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
        jlCategoryName.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
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

        //Task list
        final JList<Task> jlTasks = new JList<>();
        jlTasks.setCellRenderer(new TaskList(mediumFont));
        jspCategories.getViewport().setView(jlTasks);
        categoryContent.setJlTasks(jlTasks);

        //Task adder panel
        final JPanel jpTaskAdder = new JPanel(new BorderLayout());
        jpCategory.add(jpTaskAdder, BorderLayout.PAGE_END);

        //New task title
        final JLabel jlTaskTitle = new JLabel(NEW_TASK_TITLE);
        jlTaskTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
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

        //TODO: Change params
        //Tasks list content
        setTasksList(null, categoriesContent.indexOf(categoryContent));

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