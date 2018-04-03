package View;

import Model.Category;
import Model.Task;
import Model.TaskManager;

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
    private final static String NEW_TASK = "New Task";
    private final static String TAGS_TITLE = "Tags";
    private final static String MEMBERS_TITLE = "Members";
    private final static String NEW_MEMBER_TITLE = "New Member";

    private Image smallEditorIcon;
    private Image mediumEditorIcon;
    private Image bigEditorIcon;

    private Image mediumDeleteIcon;
    private Image smallDeleteIcon;

    private Image bigBackgroundIcon;

    private Image mediumLeftIcon;
    private Image mediumRightIcon;
    private Image mediumUpIcon;
    private Image mediumDownIcon;

    private Font bigFont;
    private Font mediumFont;
    private Font smallFont;
    private Font plainSmallFont;

    private final JButton jbProjectEditor;
    private final JButton jbBackgroundEditor;
    private final CategoryPanel cpCenter;
    private final JTextField jtfNewCategory;
    private final JButton jbNewCategory;

    private ArrayList<CategoryView> categoriesManager;

    public ProjectEditor(String projectName) throws IOException {

        //Load icons
        loadIcons();

        //Load fonts
        loadFonts();

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
        jbBackgroundEditor = new JButton(new ImageIcon(bigBackgroundIcon));
        jbBackgroundEditor.setBorder(BorderFactory.createEmptyBorder());
        jpNorth.add(jbBackgroundEditor);

        //CENTER
        final JScrollPane jspCenter = new JScrollPane();
        jspCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jpMainView.add(jspCenter, BorderLayout.CENTER);

        cpCenter = new CategoryPanel();
        cpCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
        cpCenter.setBorder(BorderFactory.createTitledBorder(CATEGORIES_TITLE));
        jspCenter.getViewport().add(cpCenter);

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

    private void loadIcons() throws IOException {

        //Editor icon
        bigEditorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON)).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        mediumEditorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        smallEditorIcon = ImageIO.read(new File(IMG_PATH + EDITOR_ICON)).
                getScaledInstance(12, 12, Image.SCALE_SMOOTH);

        //Delete icon
        mediumDeleteIcon = ImageIO.read(new File(IMG_PATH + DELETE_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        smallDeleteIcon = ImageIO.read(new File(IMG_PATH + DELETE_ICON)).
                getScaledInstance(12, 12, Image.SCALE_SMOOTH);

        //Background icon
        bigBackgroundIcon = ImageIO.read(new File(IMG_PATH + BACKGROUND_ICON)).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        //Movement icons
        mediumLeftIcon = ImageIO.read(new File(IMG_PATH + LEFT_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        mediumRightIcon = ImageIO.read(new File(IMG_PATH + RIGHT_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        mediumUpIcon = ImageIO.read(new File(IMG_PATH + UP_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        mediumDownIcon = ImageIO.read(new File(IMG_PATH + DOWN_ICON)).
                getScaledInstance(16, 16, Image.SCALE_SMOOTH);

    }

    private void loadFonts() {
        bigFont = new Font(Font.DIALOG, Font.BOLD, 20);
        mediumFont = new Font(Font.DIALOG, Font.BOLD, 16);
        smallFont = new Font(Font.DIALOG, Font.BOLD, 12);
        plainSmallFont = new Font(Font.DIALOG, Font.PLAIN, 12);
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

        //Clean categories
        cpCenter.removeAll();

        //Create new category manager
        categoriesManager = new ArrayList<>();

        //TODO: Change 'i' limit
        //Create all categories and tasks
        for(int i = 0; i < 1; i++) {

            //New category
            CategoryView categoryView = new CategoryView();

            //TODO: Change params
            //Category content
            createCategoryContent(categoryView, null);

            //Add category
            categoriesManager.add(categoryView);

        }

    }

    private void createCategoryContent(CategoryView categoryView, Category category) {

        //Category panel
        final JPanel jpCategory = new JPanel(new BorderLayout());
        jpCategory.setBorder(BorderFactory.createLineBorder(Color.black));
        cpCenter.add(jpCategory);
        categoryView.setCategoryPanel(jpCategory);

        //Category title panel
        final JPanel jpCategoryTitle = createCategoryTitle(categoryView, category);
        jpCategory.add(jpCategoryTitle, BorderLayout.PAGE_START);

        //New task
        final JPanel jpNewTask = new JPanel(new BorderLayout());
        jpCategory.add(jpNewTask, BorderLayout.PAGE_END);

        //New task title
        final JLabel jlNewTask = new JLabel(NEW_TASK);
        jlNewTask.setFont(smallFont);
        jlNewTask.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        jpNewTask.add(jlNewTask, BorderLayout.LINE_START);

        //New task field
        final JTextField jtfNewTask = new JTextField();
        jpNewTask.add(jtfNewTask, BorderLayout.CENTER);
        categoryView.setNewTaskTextField(jtfNewTask);

        //New task button
        final JButton jbNewTask = new JButton(ADD);
        jpNewTask.add(jbNewTask, BorderLayout.LINE_END);
        categoryView.setNewTaskButton(jbNewTask);

        //TODO: Change params
        //Create tasks
        final JScrollPane jspTasks = createTasksContent(categoryView, null);
        jpCategory.add(jspTasks, BorderLayout.CENTER);

    }

    private JPanel createCategoryTitle(CategoryView categoryView, Category category) {

        //Category title panel
        final JPanel jpCategoryTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //TODO: Change params
        //Category title
        final JLabel jlCategoryTitle = new JLabel("Category name");
        jlCategoryTitle.setFont(mediumFont);
        jpCategoryTitle.add(jlCategoryTitle);

        //Editor category
        final JButton jbCategoryEditor = new JButton(new ImageIcon(mediumEditorIcon));
        jbCategoryEditor.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryEditor);
        categoryView.setCategoryEditorButton(jbCategoryEditor);

        //Delete category
        final JButton jbCategoryDelete = new JButton(new ImageIcon(mediumDeleteIcon));
        jbCategoryDelete.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryDelete);
        categoryView.setCategoryDeleteButton(jbCategoryDelete);

        //Left category
        final JButton jbCategoryLeft = new JButton(new ImageIcon(mediumLeftIcon));
        jbCategoryLeft.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryLeft);
        categoryView.setCategoryLeftButton(jbCategoryLeft);

        //Right category
        final JButton jbCategoryRight = new JButton(new ImageIcon(mediumRightIcon));
        jbCategoryRight.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryTitle.add(jbCategoryRight);
        categoryView.setCategoryRightButton(jbCategoryRight);

        return jpCategoryTitle;

    }

    private JScrollPane createTasksContent(CategoryView categoryView, TaskManager tasks) {

        //Tasks scrollable panel
        final JScrollPane jspTasks = new JScrollPane();
        jspTasks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jspTasks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Tasks panel
        final JPanel jpTasks = new JPanel();
        jpTasks.setLayout(new BoxLayout(jpTasks, BoxLayout.PAGE_AXIS));
        jpTasks.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jspTasks.getViewport().add(jpTasks);

        //TODO: Change 'i' limit
        for(int i = 0; i < 1; i++) {

            //New task
            TaskView taskView = new TaskView();

            //Task
            final JPanel jpTask = new JPanel();
            jpTask.setLayout(new BoxLayout(jpTask, BoxLayout.PAGE_AXIS));
            jpTask.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jpTasks.add(jpTask);

            //Task title
            final JPanel jpTaskTitle = createTaskTitle(taskView);
            jpTask.add(jpTaskTitle);

            //TODO: Change params
            //Task description
            final JPanel jpDescription = createTaskDescription(taskView, null);
            jpTask.add(jpDescription);

            //Tags title
            final JPanel jpTagsTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
            jpTask.add(jpTagsTitle);

            final JLabel jlTagsTitle = new JLabel(TAGS_TITLE);
            jlTagsTitle.setFont(smallFont);
            jpTagsTitle.add(jlTagsTitle);

            //TODO: Change params
            //Tags
            final JPanel jpTags = createTagsContent(taskView, null);
            jpTask.add(jpTags);

            //TODO: Change params
            //Members
            final JPanel jpMembers = createMembersContent(taskView, null);
            jpTask.add(jpMembers);

            //Add task
            categoryView.addNewTask(taskView);

        }

        return jspTasks;

    }

    private JPanel createTaskTitle(TaskView taskView) {

        final JPanel jpTaskTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //TODO: Change params
        //Task title
        final JLabel jlTaskTitle = new JLabel("Task name");
        jlTaskTitle.setFont(mediumFont);
        jpTaskTitle.add(jlTaskTitle);

        //Task editor button
        final JButton jbTaskEditor = new JButton(new ImageIcon(mediumEditorIcon));
        jbTaskEditor.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskEditor);
        taskView.setTaskEditorButton(jbTaskEditor);

        //Task delete button
        final JButton jbTaskDelete = new JButton(new ImageIcon(mediumDeleteIcon));
        jbTaskDelete.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskDelete);
        taskView.setTaskDeleteButton(jbTaskDelete);

        //Up category button
        final JButton jbTaskUp = new JButton(new ImageIcon(mediumUpIcon));
        jbTaskUp.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskUp);
        taskView.setTaskUpButton(jbTaskUp);

        //Down category button
        final JButton jbTaskDown = new JButton(new ImageIcon(mediumDownIcon));
        jbTaskDown.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskDown);
        taskView.setTaskDownButton(jbTaskDown);

        return jpTaskTitle;

    }

    private JPanel createTaskDescription(TaskView taskView, Task task) {

        final JPanel jpDescription = new JPanel(new BorderLayout());

        final JPanel jpDescriptionTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpDescription.add(jpDescriptionTitle, BorderLayout.PAGE_START);

        //Task description title
        final JLabel jlDescriptionTitle = new JLabel(DESCRIPTION_TITLE);
        jlDescriptionTitle.setFont(smallFont);
        jpDescriptionTitle.add(jlDescriptionTitle);

        //Task description editor button
        final JButton jbDescriptionEditor = new JButton(new ImageIcon(smallEditorIcon));
        jbDescriptionEditor.setBorder(BorderFactory.createEmptyBorder());
        jpDescriptionTitle.add(jbDescriptionEditor);
        taskView.setDescriptionEditorButton(jbDescriptionEditor);

        //TODO: Change params
        //Task description text
        final JTextArea jtaDescription = new JTextArea("Sample description");
        jtaDescription.setEditable(false);
        jpDescription.add(jtaDescription, BorderLayout.CENTER);
        taskView.setDescriptionTextArea(jtaDescription);

        return jpDescription;

    }

    private JPanel createTagsContent(TaskView taskView, Task task) {

        final JPanel jpTags = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //TODO: Change 'i' limit
        //Create tags
        for(int i = 0; i < 1; i++) {

            //New tag view
            TagView tagView = new TagView();

            //Tag
            final JPanel jpTag = new JPanel(new FlowLayout(FlowLayout.LEFT));
            jpTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jpTags.add(jpTag);

            //TODO: Change params
            //Tag name
            final JLabel jlTagName = new JLabel("Tag name");
            jlTagName.setFont(plainSmallFont);
            jpTag.add(jlTagName);

            //Tag editor button
            final JButton jbTagEditor = new JButton(new ImageIcon(smallEditorIcon));
            jbTagEditor.setBorder(BorderFactory.createEmptyBorder());
            jpTag.add(jbTagEditor);

            //Tag delete button
            final JButton jbTagDelete = new JButton(new ImageIcon(smallDeleteIcon));
            jbTagDelete.setBorder(BorderFactory.createEmptyBorder());
            jpTag.add(jbTagDelete);

            //Add tag
            taskView.addNewTag(tagView);

        }

        return jpTags;

    }

    private JPanel createMembersContent(TaskView taskView, Task task) {

        final JPanel jpMembers = new JPanel(new BorderLayout());

        //Member title
        final JLabel jlMembers = new JLabel(MEMBERS_TITLE);
        jlMembers.setFont(smallFont);
        jpMembers.add(jlMembers, BorderLayout.PAGE_START);

        //TODO: Change params
        //Members list
        final JList<String> jListMembers = new JList<>();
        String members[] = {"Member 1", "Member 2", "Member 3"};
        jListMembers.setListData(members);
        jpMembers.add(jListMembers, BorderLayout.CENTER);

        //New member
        final JPanel jpNewMember = new JPanel(new BorderLayout());
        jpMembers.add(jpNewMember, BorderLayout.PAGE_END);

        //New member title
        final JLabel jlNewMember = new JLabel(NEW_MEMBER_TITLE);
        jlNewMember.setFont(smallFont);
        jlNewMember.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        jpNewMember.add(jlNewMember, BorderLayout.LINE_START);

        //New member field
        final JTextField jtfNewMember = new JTextField();
        jpNewMember.add(jtfNewMember, BorderLayout.CENTER);
        taskView.setNewMemberTextField(jtfNewMember);

        //New member button
        final JButton jbNewMember = new JButton(ADD);
        jpNewMember.add(jbNewMember, BorderLayout.LINE_END);
        taskView.setNewMemberButton(jbNewMember);

        return jpMembers;

    }

    @Override
    public void setVisible(boolean visibility) {

        pack();
        setSize(600, 400);

        if(visibility) {
            for(int i = 0; i < categoriesManager.size(); i++) {
                JPanel jpCategory = categoriesManager.get(i).getCategoryPanel();
                jpCategory.setPreferredSize(new Dimension(jpCategory.getWidth(), cpCenter.getHeight() - 90));
            }
        }

        super.setVisible(visibility);

    }

}