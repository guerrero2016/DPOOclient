package View.edition.task.tag;

import ModelAEliminar.Tag;
import View.edition.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TagPanel extends JPanel {

    public final static String ACTION_TAG_NAME_EDIT = "TagNameEdit";
    public final static String ACTION_TAG_DELETE = "TagDelete";

    private final int PANEL_WIDTH = 150;
    private final int PANEL_HEIGHT = 50;

    private final static int MAX_TAG_LENGTH = 20;

    private Image editorIcon;
    private Image checkIcon;

    private final JLabel jlTagName;
    private final JButton jbTagEditor;
    private final JButton jbTagDelete;

    public TagPanel(Image editorIcon, Image deleteIcon, Image checkIcon, Tag tag) {

        this.editorIcon = editorIcon;
        this.checkIcon = checkIcon;

        //Panel config
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setTagColor(tag.getColor());

        //Buttons panel
        final TransparentPanel tpButtonsPanel = new TransparentPanel();
        tpButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(tpButtonsPanel, BorderLayout.PAGE_START);

        //Editor button
        jbTagEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(12, 12,
                Image.SCALE_SMOOTH)));
        jbTagEditor.setBorder(null);
        jbTagEditor.setActionCommand(ACTION_TAG_NAME_EDIT);
        tpButtonsPanel.add(jbTagEditor);

        //Delete icon
        jbTagDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(12, 12,
                Image.SCALE_SMOOTH)));
        jbTagDelete.setBorder(null);
        jbTagDelete.setActionCommand(ACTION_TAG_DELETE);
        tpButtonsPanel.add(jbTagDelete);

        //Tag name
        jlTagName = new JLabel();
        jlTagName.setHorizontalAlignment(JTextField.CENTER);
        jlTagName.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        jlTagName.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        setTagName(tag.getName());
        add(jlTagName, BorderLayout.CENTER);

    }

    public void setTagName(String tagName) {
        if(tagName.length() <= MAX_TAG_LENGTH) {
            jlTagName.setText(tagName);
        } else {
            String shortTagName = tagName.substring(0, MAX_TAG_LENGTH) + "...";
            jlTagName.setText(shortTagName);
        }
    }

    public void setTagColor(Color tagColor) {
        setBackground(tagColor);
    }

    public void resetActionController() {
        for(ActionListener actionListener : jbTagEditor.getActionListeners()) {
            jbTagEditor.addActionListener(actionListener);
            jbTagDelete.addActionListener(actionListener);
        }
    }

    public void registerActionController(ActionListener actionListener) {
        jbTagEditor.addActionListener(actionListener);
        jbTagDelete.addActionListener(actionListener);
    }

}