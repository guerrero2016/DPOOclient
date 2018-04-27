package View.edition.task;

import ModelAEliminar.Tag;
import View.edition.TransparentPanel;
import View.edition.TransparentScrollPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaskPanel extends TransparentPanel {

    public final static String ACTION_TASK_BACK = "TaskBack";
    public final static String ACTION_TASK_EDIT_NAME = "TaskEditName";
    public final static String ACTION_TASK_DELETE = "TaskDelete";
    public final static String ACTION_DESCRIPTION_EDITION = "DescriptionEdition";
    public final static String ACTION_TAG_ADD = "TagAdd";

    private final static int MAX_TASK_LENGTH = 20;
    private final static int MAX_TAGS = 5;

    private final static String TASK_TITLE = "Task";
    private final static String DESCRIPTION_TITLE = "Description";
    private final static String TAGS_TITLE = "Tags";
    private final static String TAG_ADDER_TITLE = "New Tag";
    private final static String ADD_TITLE = "+";

    private final JButton jbTaskBack;
    private final JTextField jtfTaskName;
    private final JButton jbTaskEditor;
    private final JButton jbTaskDelete;
    private final JButton jbDescriptionEditor;
    private final JTextArea jtaDescription;
    private final TransparentPanel tpTagsList;
    private final JTextField jtfTagName;
    private final JButton jbTagAdder;

    private Image editorIcon;
    private Image checkIcon;
    private Image deleteIcon;

    private ArrayList<TagPanel> tagPanels;

    public TaskPanel(Image backIcon, Image editorIcon, Image deleteIcon, Image checkIcon) {

        //Save needed icons
        this.editorIcon = editorIcon;
        this.checkIcon = checkIcon;
        this.deleteIcon = deleteIcon;

        //Panel settings
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(TASK_TITLE));

        //Task title panel
        final TransparentPanel tpTaskTitle = new TransparentPanel();
        tpTaskTitle.setLayout(new BorderLayout());
        tpTaskTitle.setBorder(BorderFactory.createEmptyBorder(5,5, 5, 0));
        add(tpTaskTitle, BorderLayout.PAGE_START);

        //Task name
        jtfTaskName = new JTextField();
        jtfTaskName.setBorder(BorderFactory.createEmptyBorder());
        jtfTaskName.setEditable(false);
        jtfTaskName.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        tpTaskTitle.add(jtfTaskName, BorderLayout.CENTER);

        //Task buttons panel
        final TransparentPanel tpTaskButtons = new TransparentPanel();
        tpTaskButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        tpTaskTitle.add(tpTaskButtons, BorderLayout.LINE_END);

        //Task back button
        jbTaskBack = new JButton(new ImageIcon(backIcon.getScaledInstance(20, 20,
                Image.SCALE_SMOOTH)));
        jbTaskBack.setBorder(null);
        jbTaskBack.setActionCommand(ACTION_TASK_BACK);
        tpTaskButtons.add(jbTaskBack);

        //Task editor button
        jbTaskEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(20, 20,
                Image.SCALE_SMOOTH)));
        jbTaskEditor.setBorder(null);
        jbTaskEditor.setActionCommand(ACTION_TASK_EDIT_NAME);
        tpTaskButtons.add(jbTaskEditor);

        //Task delete icon
        jbTaskDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(20, 20,
                Image.SCALE_SMOOTH)));
        jbTaskDelete.setBorder(null);
        jbTaskDelete.setActionCommand(ACTION_TASK_DELETE);
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
        gbcDescription.weighty = 0.3;
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
        jbDescriptionEditor.setBorder(null);
        jbDescriptionEditor.setActionCommand(ACTION_DESCRIPTION_EDITION);
        tpDescriptionTitle.add(jbDescriptionEditor);

        //Scrollable description text
        final JScrollPane jspDescription = new JScrollPane();
        tpDescription.add(jspDescription);

        //Description text
        jtaDescription = new JTextArea();
        jtaDescription.setEditable(false);
        jspDescription.getViewport().setView(jtaDescription);

        //Tags panel
        final TransparentPanel tpTags = new TransparentPanel();
        tpTags.setLayout(new BorderLayout());

        GridBagConstraints gbcTags = new GridBagConstraints();
        gbcTags.gridx = 0;
        gbcTags.weightx = 1;
        gbcTags.weighty = 0.7;
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
        final TransparentScrollPanel tspTags = new TransparentScrollPanel();
        tpTags.add(tspTags, BorderLayout.CENTER);

        //Tags list
        tpTagsList = new TransparentPanel();
        tpTagsList.setLayout(new GridBagLayout());
        tspTags.getViewport().setView(tpTagsList);
        tagPanels = new ArrayList<>();

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
        jbTagAdder.setEnabled(false);
        jbTagAdder.setActionCommand(ACTION_TAG_ADD);
        jpTagsAdder.add(jbTagAdder, BorderLayout.LINE_END);

    }

    public String getTaskName() {

        if(jtfTaskName.getText() != null) {
            return jtfTaskName.getText();
        }

        return null;

    }

    public void setTaskName(String taskName) {
        if(taskName.length() <= MAX_TASK_LENGTH) {
            jtfTaskName.setText(taskName);
        } else {
            String shortTaskName = taskName.substring(0, MAX_TASK_LENGTH) + "...";
            jtfTaskName.setText(shortTaskName);
        }
    }

    public void setTaskNameEditable(boolean editableState, String completeTaskName) {

        if(editableState) {
            jtfTaskName.setText(completeTaskName);
            jbTaskEditor.setIcon(new ImageIcon(checkIcon.getScaledInstance(20, 20,
                    Image.SCALE_SMOOTH)));
        } else {
            setTaskName(completeTaskName);
            jbTaskEditor.setIcon(new ImageIcon(editorIcon.getScaledInstance(20, 20,
                    Image.SCALE_SMOOTH)));
        }

        jtfTaskName.setEditable(editableState);

    }

    public boolean isTaskNameEditable() {
        return jtfTaskName.isEditable();
    }

    public String getDescription() {
        return jtaDescription.getText();
    }

    public void setDescription(String description) {
        jtaDescription.setText(description);
    }

    public void cleanTagsList() {
        tagPanels = new ArrayList<>();
        tpTagsList.removeAll();
        revalidate();
        repaint();
    }

    public void setTagsList(ArrayList<Tag> tags) {
        if(tags != null) {

            tagPanels = new ArrayList<>();

            for (int i = 0; i < tags.size(); i++) {
                addTag(tags.get(i));
            }

        }
    }

    public void addTag(Tag tag) {
        tagPanels.add(new TagPanel(editorIcon, deleteIcon, tag));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = (tagPanels.size() - 1) % MAX_TAGS;
        gbc.gridy = (tagPanels.size() - 1) / MAX_TAGS;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        tpTagsList.add(tagPanels.get(tagPanels.size() - 1), gbc);
        revalidate();
        repaint();
    }

    public void removeTag(int tagIndex) {
        if(tagIndex < tagPanels.size()) {

            tpTagsList.setVisible(false);
            tpTagsList.removeAll();
            tagPanels.remove(tagIndex);

            for(int i = 0; i < tagPanels.size(); i++) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = i % MAX_TAGS;
                gbc.gridy = i / MAX_TAGS;
                gbc.weightx = 1;
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.NONE;
                tpTagsList.add(tagPanels.get(i), gbc);
            }

            tpTagsList.setVisible(true);
            revalidate();
            repaint();

        }
    }

    public void setTagName(int tagIndex, String tagName) {
        if (tagIndex < tagPanels.size()) {
            tagPanels.get(tagIndex).setTagName(tagName);
        }
    }

    public void setTagColor(int tagIndex, Color tagColor) {
        if (tagIndex < tagPanels.size()) {
            tagPanels.get(tagIndex).setTagColor(tagColor);
        }
    }

    public String getNewTagName() {
        return jtfTagName.getText();
    }

    public void cleanNewTagName() {
        jtfTagName.setText(null);
    }

    public void setTagAdderButtonEnabled(boolean enableState) {
        jbTagAdder.setEnabled(enableState);
    }

    public void setDescriptionEditable(boolean editableState) {

        if(editableState) {
            jbDescriptionEditor.setIcon(new ImageIcon(checkIcon.getScaledInstance(16, 16,
                    Image.SCALE_SMOOTH)));
        } else {
            jbDescriptionEditor.setIcon(new ImageIcon(editorIcon.getScaledInstance(16, 16,
                    Image.SCALE_SMOOTH)));
        }

        jtaDescription.setEditable(editableState);

    }

    public boolean isDescriptionEditable() {
        return jtaDescription.isEditable();
    }

    public int getTotalTags() {
        return tagPanels.size();
    }

    public TagPanel getTagPanel(int tagIndex) {

        if(tagIndex < tagPanels.size()) {
            return tagPanels.get(tagIndex);
        }

        return null;

    }

    public void resetActionController() {
        for(ActionListener actionListener : jbTaskBack.getActionListeners()) {
            jbTaskBack.removeActionListener(actionListener);
            jbTaskEditor.removeActionListener(actionListener);
            jbTaskDelete.removeActionListener(actionListener);
            jbDescriptionEditor.removeActionListener(actionListener);
            jbTagAdder.removeActionListener(actionListener);
        }
    }

    public void registerActionController(ActionListener actionListener) {
        jbTaskBack.addActionListener(actionListener);
        jbTaskEditor.addActionListener(actionListener);
        jbTaskDelete.addActionListener(actionListener);
        jbDescriptionEditor.addActionListener(actionListener);
        jbTagAdder.addActionListener(actionListener);
    }

    public void registerDocumentController(DocumentListener documentListener) {
        jtfTagName.getDocument().addDocumentListener(documentListener);
    }

}