package View.edition.task;

import Model.Tag;
import Model.Task;
import View.edition.TransparentPanel;

import javax.swing.*;
import java.awt.*;

public class TaskPanel extends TransparentPanel {

    private final static int MAX_TASK_LENGTH = 20;

    private final static String TASK_TITLE = "Task";
    private final static String DESCRIPTION_TITLE = "Description";
    private final static String TAGS_TITLE = "Tags";
    private final static String TAG_ADDER_TITLE = "New Tag";
    private final static String ADD_TITLE = "+";

    private final JButton jbTaskBack;
    private final JLabel jlTaskName;
    private final JButton jbTaskEditor;
    private final JButton jbTaskDelete;
    private final JButton jbDescriptionEditor;
    private final JTextArea jtaDescription;
    private final JList<Tag> jlTagsList;
    private final JTextField jtfTagName;
    private final JButton jbTagAdder;
    private final DefaultListModel<Tag> tagsList;

    public TaskPanel(Task task, Image backIcon, Image editorIcon, Image deleteIcon) {

        //Panel settings
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(TASK_TITLE));

        //Task title panel
        final TransparentPanel tpTaskTitle = new TransparentPanel();
        tpTaskTitle.setLayout(new BorderLayout());
        tpTaskTitle.setBorder(BorderFactory.createEmptyBorder(5,5, 5, 0));
        add(tpTaskTitle, BorderLayout.PAGE_START);

        //Task name
        jlTaskName = new JLabel();
        jlTaskName.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        setTaskName(task.getName());
        tpTaskTitle.add(jlTaskName, BorderLayout.CENTER);

        //Task buttons panel
        final TransparentPanel tpTaskButtons = new TransparentPanel();
        tpTaskButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        tpTaskTitle.add(tpTaskButtons, BorderLayout.LINE_END);

        //Task back button
        jbTaskBack = new JButton(new ImageIcon(backIcon.getScaledInstance(20, 20,
                Image.SCALE_SMOOTH)));
        jbTaskBack.setBorder(BorderFactory.createEmptyBorder());
        tpTaskButtons.add(jbTaskBack);

        //Task editor button
        jbTaskEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(20, 20,
                Image.SCALE_SMOOTH)));
        jbTaskEditor.setBorder(BorderFactory.createEmptyBorder());
        tpTaskButtons.add(jbTaskEditor);

        //Task delete icon
        jbTaskDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(20, 20,
                Image.SCALE_SMOOTH)));
        jbTaskDelete.setBorder(BorderFactory.createEmptyBorder());
        tpTaskButtons.add(jbTaskDelete);

        //Content panel
        final TransparentPanel tpContent = new TransparentPanel();
        tpContent.setLayout(new GridBagLayout());
        add(tpContent, BorderLayout.CENTER);

        //Description panel
        final TransparentPanel tpDescription = new TransparentPanel();
        tpDescription.setLayout(new BorderLayout());

        GridBagConstraints gbcDescription = new GridBagConstraints();
        gbcDescription.gridx = 0;
        gbcDescription.gridy = 0;
        gbcDescription.weightx = 1;
        gbcDescription.weighty = 0.5;
        gbcDescription.fill = GridBagConstraints.BOTH;

        tpContent.add(tpDescription, gbcDescription);

        //Description title panel
        final TransparentPanel tpDescriptionTitle = new TransparentPanel();
        tpDescriptionTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        tpDescription.add(tpDescriptionTitle, BorderLayout.PAGE_START);

        //Description title
        final JLabel jlDescription = new JLabel(DESCRIPTION_TITLE);
        jlDescription.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        jlDescription.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        tpDescriptionTitle.add(jlDescription);

        //Description editor button
        jbDescriptionEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(16, 16,
                Image.SCALE_SMOOTH)));
        jbDescriptionEditor.setBorder(BorderFactory.createEmptyBorder());
        tpDescriptionTitle.add(jbDescriptionEditor);

        //Scrollable description text
        final JScrollPane jspDescription = new JScrollPane();
        tpDescription.add(jspDescription);

        //Description text
        jtaDescription = new JTextArea();
        jtaDescription.setEditable(false);
        jtaDescription.setText(task.getDescription());
        jspDescription.getViewport().setView(jtaDescription);

        //Tags panel
        final TransparentPanel tpTags = new TransparentPanel();
        tpTags.setLayout(new BorderLayout());

        GridBagConstraints gbcTags = new GridBagConstraints();
        gbcTags.gridx = 0;
        gbcTags.weightx = 1;
        gbcTags.weighty = 0.5;
        gbcTags.fill = GridBagConstraints.BOTH;

        tpContent.add(tpTags, gbcTags);

        //Tags title panel
        final TransparentPanel tpTagsTitle = new TransparentPanel();
        tpTagsTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        tpTags.add(tpTagsTitle, BorderLayout.PAGE_START);

        //Tags title
        final JLabel jlTagsTitle = new JLabel(TAGS_TITLE);
        jlTagsTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        jlTagsTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        tpTagsTitle.add(jlTagsTitle);

        //Scrollable tags list
        final JScrollPane jspTags = new JScrollPane();
        tpTags.add(jspTags, BorderLayout.CENTER);

        //Tags list
        jlTagsList = new JList<>();
        jlTagsList.setCellRenderer(new TagList());
        jlTagsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jspTags.getViewport().setView(jlTagsList);

        tagsList = new DefaultListModel<>();

        for(int i = 0; i < task.getTotalTags(); i++) {
            tagsList.addElement(task.getTag(i));
        }

        jlTagsList.setModel(tagsList);

        //Tag adder panel
        final JPanel jpTagsAdder = new JPanel(new BorderLayout());
        tpTags.add(jpTagsAdder, BorderLayout.PAGE_END);

        //Tag adder title
        final JLabel jlTagAdderTitle = new JLabel(TAG_ADDER_TITLE);
        jlTagAdderTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jlTagAdderTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jpTagsAdder.add(jlTagAdderTitle, BorderLayout.LINE_START);

        //Tag adder field
        jtfTagName = new JTextField();
        jtfTagName.setEditable(true);
        jpTagsAdder.add(jtfTagName, BorderLayout.CENTER);

        //Tag adder button
        jbTagAdder = new JButton(ADD_TITLE);
        jpTagsAdder.add(jbTagAdder, BorderLayout.LINE_END);

    }

    public void setTaskName(String taskName) {
        if(taskName.length() <= MAX_TASK_LENGTH) {
            jlTaskName.setText(taskName);
        } else {
            String shortTaskName = taskName.substring(0, MAX_TASK_LENGTH) + "...";
            jlTaskName.setText(shortTaskName);
        }
    }

    public String getDescription() {
        return jtaDescription.getText();
    }

    public void setDescription(String description) {
        jtaDescription.setText(description);
    }

    public void addNewTag(Tag tag) {
        tagsList.addElement(tag);
    }

    public Tag getSelectedTag() {

        if(jlTagsList.isSelectionEmpty()) {
            return null;
        }

        return jlTagsList.getSelectedValue();

    }

    public void removeTag(int tagIndex) {
        if(tagIndex < tagsList.size()) {
            tagsList.remove(tagIndex);
        }
    }

    public String getNewTagName() {
        return jtfTagName.getText();
    }

    public void cleanNewTagName() {
        jtfTagName.setText(null);
    }

    public void setTagAdderButtonState(boolean buttonState) {
        jbTagAdder.setEnabled(buttonState);
    }

}