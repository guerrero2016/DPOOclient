package View.project;

import Model.Tag;
import Model.Task;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TaskView extends JFrame {

    private final static String IMG_PATH = "img/";
    private final static String EDITOR_ICON_FILE = "editor_icon.png";
    private final static String DELETE_ICON_FILE = "delete_icon.png";

    private final static int WINDOW_WIDTH = 350;
    private final static int WINDOW_HEIGHT = 500;
    private final static String WINDOW_TITLE = "Task";

    private final static int MAX_TASK_LENGTH = 15;

    private final static String DESCRIPTION_TITLE = "Description";
    private final static String TAG_TITLE = "Tags";
    private final static String NEW_TAG_TITLE = "New Tag";
    private final static String ADD_TITLE = "+";

    private Font bigFont;
    private Font mediumFont;

    private Image bigEditorIcon;
    private Image mediumEditorIcon;

    private Image bigDeleteIcon;

    private final JLabel jlTaskName;
    private final JButton jbTaskEditor;
    private final JButton jbTaskDelete;
    private final JTextArea jtaDescription;
    private final JButton jbDescriptionEditor;
    private final JList<Tag> jlTagList;
    private final JTextField jtfTagName;
    private final JButton jbTagAdder;

    public TaskView(Task task) throws IOException {

        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setResizable(false);
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //TODO: Change close operation
        setLocationRelativeTo(null);

        //Load settings
        loadFonts();
        loadIcons();

        //Main view
        final JPanel jpMain = new JPanel(new BorderLayout());
        setContentPane(jpMain);

        //Task title
        final JPanel jpTaskTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpMain.add(jpTaskTitle, BorderLayout.PAGE_START);

        //TODO: Change params
        //Task name
        jlTaskName = new JLabel();

        if(task.getName().length() > MAX_TASK_LENGTH) {
            String shortTaskName = task.getName().substring(0, MAX_TASK_LENGTH) + "...";
            jlTaskName.setText(shortTaskName);
        } else {
            jlTaskName.setText(task.getName());
        }

        jlTaskName.setFont(bigFont);
        jlTaskName.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpTaskTitle.add(jlTaskName);

        //Task editor icon
        jbTaskEditor = new JButton(new ImageIcon(bigEditorIcon));
        jbTaskEditor.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskEditor);

        //Task delete icon
        jbTaskDelete = new JButton(new ImageIcon(bigDeleteIcon));
        jbTaskDelete.setBorder(BorderFactory.createEmptyBorder());
        jpTaskTitle.add(jbTaskDelete);

        //Task content
        final JPanel jpTaskContent = new JPanel(new GridLayout(2, 1));
        jpMain.add(jpTaskContent, BorderLayout.CENTER);

        //Task description
        final JPanel jpTaskDescription = new JPanel(new BorderLayout());
        jpTaskContent.add(jpTaskDescription);

        //Description title
        final JPanel jpDescriptionTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpTaskDescription.add(jpDescriptionTitle, BorderLayout.PAGE_START);

        //Description name
        final JLabel jlDescriptionName = new JLabel(DESCRIPTION_TITLE);
        jlDescriptionName.setFont(mediumFont);
        jlDescriptionName.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpDescriptionTitle.add(jlDescriptionName);

        //Description editor button
        jbDescriptionEditor = new JButton(new ImageIcon(mediumEditorIcon));
        jbDescriptionEditor.setBorder(BorderFactory.createEmptyBorder());
        jpDescriptionTitle.add(jbDescriptionEditor);

        //TODO: Resize text?
        //Scrollable description
        final JScrollPane jspDescription = new JScrollPane();
        jspDescription.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpTaskDescription.add(jspDescription);

        //Description text
        jtaDescription = new JTextArea();
        jtaDescription.setEditable(false);
        jtaDescription.setText(task.getDescription());
        jspDescription.getViewport().setView(jtaDescription);

        //Tags
        final JPanel jpTags = new JPanel(new BorderLayout());
        jpTaskContent.add(jpTags);

        //Tag title
        final JLabel jlTagTitle = new JLabel(TAG_TITLE);
        jlTagTitle.setFont(mediumFont);
        jlTagTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpTags.add(jlTagTitle, BorderLayout.PAGE_START);

        //Scrollable tag list
        final JScrollPane jspTagList = new JScrollPane();
        jspTagList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jpTags.add(jspTagList, BorderLayout.CENTER);

        //Tag list
        jlTagList = new JList<>();
        jlTagList.setCellRenderer(new TagList(mediumFont));
        jspTagList.getViewport().setView(jlTagList);

        //TODO: Delete
        DefaultListModel<Tag> taskList = new DefaultListModel<>();
        taskList.addElement(new Tag("Orange tag", Color.ORANGE));
        taskList.addElement(new Tag("Cyan tag", Color.CYAN));
        jlTagList.setModel(taskList);

        //Tag adder
        final JPanel jpTagAdder = new JPanel(new BorderLayout());
        jpTagAdder.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jpTags.add(jpTagAdder, BorderLayout.PAGE_END);

        //Tag adder name
        final JLabel jlNewTag = new JLabel(NEW_TAG_TITLE);
        jlNewTag.setFont(mediumFont);
        jlNewTag.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        jpTagAdder.add(jlNewTag, BorderLayout.LINE_START);

        //Tag adder field
        jtfTagName = new JTextField();
        jtfTagName.setEditable(true);
        jpTagAdder.add(jtfTagName, BorderLayout.CENTER);

        //Tag adder button
        jbTagAdder = new JButton(ADD_TITLE);
        jpTagAdder.add(jbTagAdder, BorderLayout.LINE_END);

    }

    private void loadFonts() {
        bigFont = new Font(Font.DIALOG, Font.BOLD, 20);
        mediumFont = new Font(Font.DIALOG, Font.BOLD, 16);
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

    }

}