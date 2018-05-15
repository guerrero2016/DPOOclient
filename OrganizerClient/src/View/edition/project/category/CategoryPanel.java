package View.edition.project.category;

import Model.project.Category;
import Model.project.Task;
import View.edition.document.DocumentEnablePanel;
import View.edition.project.category.task.TaskListCellRenderer;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class CategoryPanel extends JPanel implements DocumentEnablePanel {

    public final static String ACTION_CATEGORY_EDIT_NAME = "CategoryEditName";
    public final static String ACTION_CATEGORY_LEFT = "CategoryLeft";
    public final static String ACTION_CATEGORY_RIGHT = "CategoryRight";
    public final static String ACTION_CATEGORY_DELETE = "CategoryDelete";
    public final static String ACTION_TASK_ADD = "TaskAdd";

    private final static int PANEL_WIDTH = 275;
    private final static int MAX_CATEGORY_LENGTH = 15;

    private final static String NEW_TASK_TITLE = "New Task";
    private final static String ADD_TITLE = "+";

    private Image editorIcon;
    private Image checkIcon;

    private final JTextField jtfCategoryName;
    private final JButton jbCategoryEditor;
    private final JButton jbCategoryDelete;
    private final JButton jbCategoryLeft;
    private final JButton jbCategoryRight;
    private final JList<Task> jlTasks;
    private final JTextField jtfTaskName;
    private JButton jbTaskAdder;

    private final DefaultListModel<Task> tasksList;

    private ActionListener actionListener;
    private MouseListener mouseListener;
    private TransferHandler transferHandler;

    public CategoryPanel(Category category, Image editorIcon, Image deleteIcon, Image leftIcon, Image rightIcon,
                         Image checkIcon) {

        this.editorIcon = editorIcon;
        this.checkIcon = checkIcon;

        //Panel config
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(PANEL_WIDTH, getHeight()));

        //Category title panel
        final JPanel jpCategoryTitle = new JPanel(new BorderLayout());
        jpCategoryTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(jpCategoryTitle, BorderLayout.PAGE_START);

        //Category name
        jtfCategoryName = new JTextField();
        jtfCategoryName.setEditable(false);
        jtfCategoryName.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        jtfCategoryName.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        setCategoryName(category.getName());
        jpCategoryTitle.add(jtfCategoryName, BorderLayout.CENTER);

        //Category buttons panel
        final JPanel jpCategoryButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpCategoryTitle.add(jpCategoryButtons, BorderLayout.LINE_END);

        //Category editor button
        jbCategoryEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        jbCategoryEditor.setBorder(null);
        jbCategoryEditor.setActionCommand(ACTION_CATEGORY_EDIT_NAME);
        jpCategoryButtons.add(jbCategoryEditor);

        //Category reorder left button
        jbCategoryLeft = new JButton(new ImageIcon(leftIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        jbCategoryLeft.setBorder(null);
        jbCategoryLeft.setActionCommand(ACTION_CATEGORY_LEFT);
        jpCategoryButtons.add(jbCategoryLeft);

        //Category reorder right button
        jbCategoryRight = new JButton(new ImageIcon(rightIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        jbCategoryRight.setBorder(null);
        jbCategoryRight.setActionCommand(ACTION_CATEGORY_RIGHT);
        jpCategoryButtons.add(jbCategoryRight);

        //Category delete button
        jbCategoryDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        jbCategoryDelete.setBorder(null);
        jbCategoryDelete.setActionCommand(ACTION_CATEGORY_DELETE);
        jpCategoryButtons.add(jbCategoryDelete);

        //Scrollable remove list
        final JScrollPane jspCategories = new JScrollPane();
        add(jspCategories, BorderLayout.CENTER);

        //Task list
        jlTasks = new JList<>();
        jlTasks.setCellRenderer(new TaskListCellRenderer());
        jlTasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jspCategories.getViewport().setView(jlTasks);

        tasksList = new DefaultListModel<>();

        for(int i = 0; i < category.getTasksSize(); i++) {
            tasksList.addElement(category.getTask(i));
        }

        jlTasks.setModel(tasksList);

        //Task adder panel
        final JPanel jpTaskAdder = new JPanel(new BorderLayout());
        add(jpTaskAdder, BorderLayout.PAGE_END);

        //New remove title
        final JLabel jlTaskTitle = new JLabel(NEW_TASK_TITLE);
        jlTaskTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpTaskAdder.add(jlTaskTitle, BorderLayout.LINE_START);

        //New remove name field
        jtfTaskName = new JTextField();
        jtfTaskName.setEditable(true);
        jpTaskAdder.add(jtfTaskName);

        //New remove adder button
        jbTaskAdder = new JButton(ADD_TITLE);
        jbTaskAdder.setEnabled(false);
        jbTaskAdder.setActionCommand(ACTION_TASK_ADD);
        jpTaskAdder.add(jbTaskAdder, BorderLayout.LINE_END);

    }

    public void setCategoryName(String categoryName) {
        if(categoryName.length() <= MAX_CATEGORY_LENGTH) {
            jtfCategoryName.setText(categoryName);
        } else {
            String shortCategoryName = categoryName.substring(0, MAX_CATEGORY_LENGTH) + "...";
            jtfCategoryName.setText(shortCategoryName);
        }
    }

    public String getCategoryName() {
        return jtfCategoryName.getText();
    }

    public void setCategoryNameEditable(boolean editableState, String completeCategoryName) {

        if(editableState) {
            jtfCategoryName.setText(completeCategoryName);
            jbCategoryEditor.setIcon(new ImageIcon(checkIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        } else {
            setCategoryName(completeCategoryName);
            jbCategoryEditor.setIcon(new ImageIcon(editorIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        }

        jtfCategoryName.setEditable(editableState);

    }

    public boolean isCategoryNameEditable() {
        return jtfCategoryName.isEditable();
    }

    public String getNewTaskName() {
        return jtfTaskName.getText();
    }

    public void cleanNewTaskName() {
        jtfTaskName.setText(null);
    }

    public void addNewTask(Task task) {
        tasksList.addElement(task);
        revalidate();
        repaint();
    }

    public void removeTask(Task task) {
        tasksList.removeElement(task);
        revalidate();
        repaint();
    }

    public void updateTask(int taskIndex, Task task) {
        if(task != null && taskIndex >= 0 && taskIndex < tasksList.size()) {
            tasksList.setElementAt(task, taskIndex);
            revalidate();
            repaint();
        }
    }

    public JList<Task> getListComponent() {
        return jlTasks;
    }

    public void resetActionController() {
        jbCategoryEditor.removeActionListener(actionListener);
        jbCategoryLeft.removeActionListener(actionListener);
        jbCategoryRight.removeActionListener(actionListener);
        jbCategoryDelete.removeActionListener(actionListener);
        jbTaskAdder.removeActionListener(actionListener);
        actionListener = null;
    }

    public void registerActionController(ActionListener actionListener) {
        this.actionListener = actionListener;
        jbCategoryEditor.addActionListener(actionListener);
        jbCategoryLeft.addActionListener(actionListener);
        jbCategoryRight.addActionListener(actionListener);
        jbCategoryDelete.addActionListener(actionListener);
        jbTaskAdder.addActionListener(actionListener);
    }

    public void resetMouseController() {
        jlTasks.removeMouseListener(mouseListener);
        mouseListener = null;
    }

    public void registerMouseController(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
        jlTasks.addMouseListener(mouseListener);
    }

    public void registerDocumentController(DocumentListener documentListener) {
        jtfTaskName.getDocument().addDocumentListener(documentListener);
    }

    public void resetDnDController() {
        transferHandler = null;
        jlTasks.setTransferHandler(null);
        jlTasks.setDropMode(DropMode.USE_SELECTION);
        jlTasks.setDragEnabled(false);
    }

    public void registerDnDController(TransferHandler transferHandler) {
        this.transferHandler = transferHandler;
        jlTasks.setTransferHandler(transferHandler);
        jlTasks.setDropMode(DropMode.INSERT);
        jlTasks.setDragEnabled(true);
    }

    @Override
    public void setDocumentEnableState(boolean enableState) {
        jbTaskAdder.setEnabled(enableState);
    }

}