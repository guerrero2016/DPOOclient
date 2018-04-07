package View.project;

import Model.Task;

import javax.swing.*;

public class CategoryContent {

    private JPanel jpCategory;
    private JLabel jlCategoryName;
    private JButton jbCategoryEditor;
    private JButton jbCategoryDelete;
    private JButton jbCategoryLeft;
    private JButton jbCategoryRight;
    private JList<Task> jlTasks;
    private JTextField jtfTaskName;
    private JButton jbTaskAdder;

    public CategoryContent() {
    }

    public JPanel getJpCategory() {
        return jpCategory;
    }

    public void setJpCategory(JPanel jpCategory) {
        this.jpCategory = jpCategory;
    }

    public JLabel getJlCategoryName() {
        return jlCategoryName;
    }

    public void setJlCategoryName(JLabel jlCategoryName) {
        this.jlCategoryName = jlCategoryName;
    }

    public JButton getJbCategoryEditor() {
        return jbCategoryEditor;
    }

    public void setJbCategoryEditor(JButton jbCategoryEditor) {
        this.jbCategoryEditor = jbCategoryEditor;
    }

    public JButton getJbCategoryDelete() {
        return jbCategoryDelete;
    }

    public void setJbCategoryDelete(JButton jbCategoryDelete) {
        this.jbCategoryDelete = jbCategoryDelete;
    }

    public JButton getJbCategoryLeft() {
        return jbCategoryLeft;
    }

    public void setJbCategoryLeft(JButton jbCategoryLeft) {
        this.jbCategoryLeft = jbCategoryLeft;
    }

    public JButton getJbCategoryRight() {
        return jbCategoryRight;
    }

    public void setJbCategoryRight(JButton jbCategoryRight) {
        this.jbCategoryRight = jbCategoryRight;
    }

    public JList<Task> getJlTasks() {
        return jlTasks;
    }

    public void setJlTasks(JList<Task> jlTasks) {
        this.jlTasks = jlTasks;
    }

    public JTextField getJtfTaskName() {
        return jtfTaskName;
    }

    public void setJtfTaskName(JTextField jtfTaskName) {
        this.jtfTaskName = jtfTaskName;
    }

    public JButton getJbTaskAdder() {
        return jbTaskAdder;
    }

    public void setJbTaskAdder(JButton jbTaskAdder) {
        this.jbTaskAdder = jbTaskAdder;
    }

}