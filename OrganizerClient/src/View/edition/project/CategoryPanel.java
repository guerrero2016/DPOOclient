package View.edition.project;

import Model.Category;
import Model.Task;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {

    private final static int PANEL_WIDTH = 275;
    private final static int MAX_CATEGORY_LENGTH = 15;

    private final static String NEW_TASK_TITLE = "New Task";
    private final static String ADD_TITLE = "+";

    private final JLabel jlCategoryName;
    private final JButton jbCategoryEditor;
    private final JButton jbCategoryDelete;
    private final JButton jbCategoryLeft;
    private final JButton jbCategoryRight;
    private final JList<Task> jlTasks;
    private final JTextField jtfTaskName;
    private JButton jbTaskAdder;

    private final DefaultListModel<Task> tasksList;

    public CategoryPanel(Category category, Image editorIcon, Image deleteIcon, Image leftIcon, Image rightIcon) {

        //Panel config
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(PANEL_WIDTH, getHeight()));

        //Category title panel
        final JPanel jpCategoryTitle = new JPanel(new BorderLayout());
        jpCategoryTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(jpCategoryTitle, BorderLayout.PAGE_START);

        //Category name
        jlCategoryName = new JLabel();
        jlCategoryName.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        jlCategoryName.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        setCategoryName(category.getCategoryName());
        jpCategoryTitle.add(jlCategoryName, BorderLayout.CENTER);

        //Category buttons panel
        final JPanel jpCategoryButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpCategoryTitle.add(jpCategoryButtons, BorderLayout.LINE_END);

        //Category editor button
        jbCategoryEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        jbCategoryEditor.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryButtons.add(jbCategoryEditor);

        //Category delete button
        jbCategoryDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        jbCategoryDelete.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryButtons.add(jbCategoryDelete);

        //Category reorder left button
        jbCategoryLeft = new JButton(new ImageIcon(leftIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        jbCategoryLeft.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryButtons.add(jbCategoryLeft);

        //Category reorder right button
        jbCategoryRight = new JButton(new ImageIcon(rightIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        jbCategoryRight.setBorder(BorderFactory.createEmptyBorder());
        jpCategoryButtons.add(jbCategoryRight);

        //Scrollable task list
        final JScrollPane jspCategories = new JScrollPane();
        add(jspCategories, BorderLayout.CENTER);

        //Task list
        jlTasks = new JList<>();
        jlTasks.setCellRenderer(new TaskList());
        jlTasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jspCategories.getViewport().setView(jlTasks);

        tasksList = new DefaultListModel<>();

        for(int i = 0; i < category.getTotalTasks(); i++) {
            tasksList.addElement(category.getTask(i));
        }

        jlTasks.setModel(tasksList);

        //Task adder panel
        final JPanel jpTaskAdder = new JPanel(new BorderLayout());
        add(jpTaskAdder, BorderLayout.PAGE_END);

        //New task title
        final JLabel jlTaskTitle = new JLabel(NEW_TASK_TITLE);
        jlTaskTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpTaskAdder.add(jlTaskTitle, BorderLayout.LINE_START);

        //New task name field
        jtfTaskName = new JTextField();
        jtfTaskName.setEditable(true);
        jpTaskAdder.add(jtfTaskName);

        //New task adder button
        jbTaskAdder = new JButton(ADD_TITLE);
        jpTaskAdder.add(jbTaskAdder, BorderLayout.LINE_END);

    }

    public void setTaskAdderButtonState(boolean buttonState) {
        jbTaskAdder.setEnabled(buttonState);
    }

    public void setCategoryName(String categoryName) {
        if(categoryName.length() <= MAX_CATEGORY_LENGTH) {
            jlCategoryName.setText(categoryName);
        } else {
            String shortCategoryName = categoryName.substring(0, MAX_CATEGORY_LENGTH) + "...";
            jlCategoryName.setText(shortCategoryName);
        }
    }

    public String getNewTaskName() {
        return jtfTaskName.getText();
    }

    public void cleanNewTaskName() {
        jtfTaskName.setText(null);
    }

    public void addNewTask(Task task) {
        tasksList.addElement(task);
    }

    public Task getSelectedTask() {

        if(jlTasks.isSelectionEmpty()) {
            return null;
        }

        return jlTasks.getSelectedValue();

    }

    public void removeTask(int taskIndex) {
        if(taskIndex < tasksList.size()) {
            tasksList.remove(taskIndex);
        }
    }

}