package View;

import javax.swing.*;
import java.util.ArrayList;

public class CategoryView {

    private JPanel jpCategory;
    private JButton jbCategoryEditor;
    private JButton jbCategoryDelete;
    private JButton jbCategoryLeft;
    private JButton jbCategoryRight;
    private JTextField jtfNewTask;
    private JButton jbNewTask;
    private ArrayList<TaskView> tasks;

    public CategoryView() {
        tasks = new ArrayList<>();
    }

    public void setCategoryPanel(JPanel jpCategory) {
        this.jpCategory = jpCategory;
    }

    public JPanel getCategoryPanel() {
        return jpCategory;
    }

    public void setCategoryEditorButton(JButton jbCategoryEditor) {
        this.jbCategoryEditor = jbCategoryEditor;
    }

    public JButton getCategoryEditorButton() {
        return jbCategoryEditor;
    }

    public void setCategoryDeleteButton(JButton jbCategoryDelete) {
        this.jbCategoryDelete = jbCategoryDelete;
    }

    public JButton getCategoryDeleteButton() {
        return jbCategoryDelete;
    }

    public void setCategoryLeftButton(JButton jbCategoryLeft) {
        this.jbCategoryLeft = jbCategoryLeft;
    }

    public JButton getCategoryLeftButton() {
        return jbCategoryLeft;
    }

    public void setCategoryRightButton(JButton jbCategoryRight) {
        this.jbCategoryRight = jbCategoryRight;
    }

    public JButton getCategoryRightButton() {
        return jbCategoryRight;
    }

    public void setNewTaskTextField(JTextField jtfNewTask) {
        this.jtfNewTask = jtfNewTask;
    }

    public void cleanNewTaskTextField() {
        jtfNewTask.setText(null);
    }

    public void setNewTaskButton(JButton jbNewTask) {
        this.jbNewTask = jbNewTask;
    }

    public JButton getNewTaskButton() {
        return jbNewTask;
    }

    public void addNewTask(TaskView taskView) {
        tasks.add(taskView);
    }

}