package View.edition.project.category;

import model.project.Category;
import model.project.Task;
import View.edition.document.DocumentEnablePanel;
import View.edition.project.category.task.TaskListCellRenderer;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Classe que correspon als panells de categories
 */
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
    private final static String EDITOR_BUTTON = "Edit";
    private final static String LEFT_BUTTON = "L";
    private final static String RIGHT_BUTTON = "R";
    private final static String DELETE_BUTTON = "Delete";
    private final static String CHECK_BUTTON = "Save changes";

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

    /**
     * Constructor que requereix de la categoria a mostrar i les imatges dels botons desitjats
     * @param category Categoria
     * @param editorIcon Imatge del boto d'edicio
     * @param deleteIcon Imatge d'eliminacio de categoria
     * @param leftIcon Imatge d'ordenament cap a l'esquerra
     * @param rightIcon Imatge d'ordenament cap a la dreta
     * @param checkIcon Imatge del boto de confirmacio
     */
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
        if(editorIcon != null) {
            jbCategoryEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            jbCategoryEditor.setBorder(null);
        } else {
            jbCategoryEditor = new JButton(EDITOR_BUTTON);
            jbCategoryEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
        }

        jbCategoryEditor.setActionCommand(ACTION_CATEGORY_EDIT_NAME);
        jpCategoryButtons.add(jbCategoryEditor);

        //Category reorder left button
        if(leftIcon != null) {
            jbCategoryLeft = new JButton(new ImageIcon(leftIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            jbCategoryLeft.setBorder(null);
        } else {
            jbCategoryLeft = new JButton(LEFT_BUTTON);
            jbCategoryLeft.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
        }

        jbCategoryLeft.setActionCommand(ACTION_CATEGORY_LEFT);
        jpCategoryButtons.add(jbCategoryLeft);

        //Category reorder right button
        if(rightIcon != null) {
            jbCategoryRight = new JButton(new ImageIcon(rightIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            jbCategoryRight.setBorder(null);
        } else {
            jbCategoryRight = new JButton(RIGHT_BUTTON);
            jbCategoryRight.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
        }

        jbCategoryRight.setActionCommand(ACTION_CATEGORY_RIGHT);
        jpCategoryButtons.add(jbCategoryRight);

        //Category delete button
        if(deleteIcon != null) {
            jbCategoryDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            jbCategoryDelete.setBorder(null);
        } else {
            jbCategoryDelete = new JButton(DELETE_BUTTON);
            jbCategoryDelete.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
        }

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

    /**
     * Setter del nom de la categoria
     * @param categoryName Nom de la categoria
     */
    public void setCategoryName(String categoryName) {
        if(categoryName.length() <= MAX_CATEGORY_LENGTH) {
            jtfCategoryName.setText(categoryName);
        } else {
            String shortCategoryName = categoryName.substring(0, MAX_CATEGORY_LENGTH) + "...";
            jtfCategoryName.setText(shortCategoryName);
        }
    }

    /**
     * Getter del nom de la categoria
     * @return Nom de la categoria
     */
    public String getCategoryName() {
        return jtfCategoryName.getText();
    }

    /**
     * Metode que indica si el nom de la categoria es editable
     * @param editableState Estat
     * @param completeCategoryName Nom de la categoria
     */
    @SuppressWarnings("Duplicates")
    public void setCategoryNameEditable(boolean editableState, String completeCategoryName) {

        if(editableState) {

            jtfCategoryName.setText(completeCategoryName);

            if(checkIcon != null) {
                jbCategoryEditor.setText(null);
                jbCategoryEditor.setIcon(new ImageIcon(checkIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
                jbCategoryEditor.setBorder(null);
            } else {
                jbCategoryEditor.setIcon(null);
                jbCategoryEditor.setText(CHECK_BUTTON);
                jbCategoryEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
                jbCategoryEditor.setBorder(new JButton().getBorder());
            }

        } else {

            setCategoryName(completeCategoryName);

            if(editorIcon != null) {
                jbCategoryEditor.setText(null);
                jbCategoryEditor.setIcon(new ImageIcon(editorIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
                jbCategoryEditor.setBorder(null);
            } else {
                jbCategoryEditor.setIcon(null);
                jbCategoryEditor.setText(EDITOR_BUTTON);
                jbCategoryEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
                jbCategoryEditor.setBorder(new JButton().getBorder());
            }

        }

        jtfCategoryName.setEditable(editableState);

    }

    /**
     * Getter de l'estat d'edicio de la categoria
     * @return Estat
     */
    public boolean isCategoryNameEditable() {
        return jtfCategoryName.isEditable();
    }

    /**
     * Getter del nom de la tasca que es vol afegir
     * @return Nom de la tasca
     */
    public String getNewTaskName() {
        return jtfTaskName.getText();
    }

    /**
     * Metode que buida el camp de la nova tasca
     */
    public void cleanNewTaskName() {
        jtfTaskName.setText(null);
    }

    /**
     * Metode que afegeix una nova tasca
     * @param task Tasca a afegir
     */
    public void addNewTask(Task task) {
        tasksList.addElement(task);
        revalidate();
        repaint();
    }

    /**
     * Metode encarregat d'eliminar una tasca
     * @param task Tasca a eliminar
     */
    public void removeTask(Task task) {
        tasksList.removeElement(task);
        revalidate();
        repaint();
    }

    /**
     * Metode encarregat d'actualitzar el contingut d'una tasca
     * @param taskIndex Index de la tasca
     * @param task Tasca
     */
    public void updateTask(int taskIndex, Task task) {
        if(task != null && taskIndex >= 0 && taskIndex < tasksList.size()) {
            tasksList.setElementAt(task, taskIndex);
            revalidate();
            repaint();
        }
    }

    /**
     * Metode encarregat d'actualitzar la llista de tasques
     */
    public void updateTasksList(ArrayList<Task> tasks) {
        tasksList.clear();
        for(int i = 0; i < tasks.size(); i++) {
            tasksList.addElement(tasks.get(i));
        }
        revalidate();
        repaint();
    }

    /**
     * Getter del component de la llista de tasques
     * @return JList de tasques
     */
    public JList<Task> getListComponent() {
        return jlTasks;
    }

    /**
     * Metode encarregat d'eliminar l'ActionListener
     */
    @SuppressWarnings("Duplicates")
    public void resetActionController() {
        jbCategoryEditor.removeActionListener(actionListener);
        jbCategoryLeft.removeActionListener(actionListener);
        jbCategoryRight.removeActionListener(actionListener);
        jbCategoryDelete.removeActionListener(actionListener);
        jbTaskAdder.removeActionListener(actionListener);
        actionListener = null;
    }

    /**
     * Metode encarregat de registrar el l'ActionListener de la vista
     * @param actionListener Controlador
     */
    @SuppressWarnings("Duplicates")
    public void registerActionController(ActionListener actionListener) {
        this.actionListener = actionListener;
        jbCategoryEditor.addActionListener(actionListener);
        jbCategoryLeft.addActionListener(actionListener);
        jbCategoryRight.addActionListener(actionListener);
        jbCategoryDelete.addActionListener(actionListener);
        jbTaskAdder.addActionListener(actionListener);
    }

    /**
     * Metode encarregat d'eliminar el MouseListener
     */
    public void resetMouseController() {
        jlTasks.removeMouseListener(mouseListener);
        mouseListener = null;
    }

    /**
     * Metode encarregat de registrar un MouseListener
     * @param mouseListener Controlador
     */
    public void registerMouseController(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
        jlTasks.addMouseListener(mouseListener);
    }

    /**
     * Metode encarregat de registrar un DocumentListener
     * @param documentListener Controlador
     */
    public void registerDocumentController(DocumentListener documentListener) {
        jtfTaskName.getDocument().addDocumentListener(documentListener);
    }

    /**
     * Metode encarregat d'eliminar el controlador drag & drop (DnD)
     */
    public void resetDnDController() {
        jlTasks.setTransferHandler(null);
        jlTasks.setDropMode(DropMode.USE_SELECTION);
        jlTasks.setDragEnabled(false);
    }

    /**
     * Metode encarregat de registrar un controlador drag & drop (DnD)
     * @param transferHandler Controlador
     */
    public void registerDnDController(TransferHandler transferHandler) {
        jlTasks.setTransferHandler(transferHandler);
        jlTasks.setDropMode(DropMode.INSERT);
        jlTasks.setDragEnabled(true);
    }

    /**
     * Metode encarregat d'establir l'estat del boto d'afegir tasques
     * @param enableState Estat
     */
    @Override
    public void setDocumentEnableState(boolean enableState) {
        jbTaskAdder.setEnabled(enableState);
    }

}