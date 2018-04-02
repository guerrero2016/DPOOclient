package View;

import javax.swing.*;
import java.util.ArrayList;

public class TaskView {

    private JButton jbTaskEditor;
    private JButton jbTaskDelete;
    private JButton jbTaskUp;
    private JButton jbTaskDown;
    private JButton jbDescriptionEditor;
    private JTextArea jtaDescription;
    private ArrayList<TagView> tags;
    private JList<String> jListMembers;

    private JTextField jtfNewMember;

    private JButton jbNewMember;
    public TaskView() {
        tags = new ArrayList<>();
    }

    public void setTaskEditorButton(JButton jbTaskEditor) {
        this.jbTaskEditor = jbTaskEditor;
    }

    public JButton getTaskEditorButton() {
        return jbTaskEditor;
    }

    public void setTaskDeleteButton(JButton jbTaskDelete) {
        this.jbTaskDelete = jbTaskDelete;
    }

    public JButton getTaskDeleteButton() {
        return jbTaskDelete;
    }

    public void setTaskUpButton(JButton jbTaskUp) {
        this.jbTaskUp = jbTaskUp;
    }

    public JButton getTaskUpButton() {
        return jbTaskUp;
    }

    public void setTaskDownButton(JButton jbTaskDown) {
        this.jbTaskDown = jbTaskDown;
    }

    public JButton getTaskDownButton() {
        return jbTaskDown;
    }

    public void setDescriptionEditorButton(JButton jbDescriptionEditor) {
        this.jbDescriptionEditor = jbDescriptionEditor;
    }

    public JButton getDescriptionEditorButton() {
        return jbDescriptionEditor;
    }

    public void setDescriptionTextArea(JTextArea jtaDescription) {
        this.jtaDescription = jtaDescription;
    }

    public void setDescriptionText(String text) {
        jtaDescription.setText(text);
    }

    public String getDescriptionText() {
        return jtaDescription.getText();
    }

    public void addNewTag(TagView tagView) {
        tags.add(tagView);
    }

    public void removeTag(int tagPosition) {
        if(tagPosition < tags.size()) {
            tags.remove(tagPosition);
        }
    }

    public ArrayList<TagView> getTags() {
        return tags;
    }

    public void setMembersList(JList<String> jListMembers) {
        this.jListMembers = jListMembers;
    }

    public JList<String> getMembersList() {
        return jListMembers;
    }

    public String getSelectedMember() {
        if(!jListMembers.isSelectionEmpty()) {
            return jListMembers.getSelectedValue();
        } else {
            return null;
        }
    }

    public void setNewMemberTextField(JTextField jtfNewMember) {
        this.jtfNewMember = jtfNewMember;
    }

    public void cleanNewMemberText() {
        jtfNewMember.setText(null);
    }

    public String getNewMemberText() {
        return jtfNewMember.getText();
    }

    public void setNewMemberButton(JButton jbNewMember) {
        this.jbNewMember = jbNewMember;
    }

    public JButton getNewMemberButton() {
        return jbNewMember;
    }

}