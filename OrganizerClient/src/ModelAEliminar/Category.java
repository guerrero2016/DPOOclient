package ModelAEliminar;

import java.util.ArrayList;

public class Category {

    private String categoryName;
    private ArrayList<Task> tasks;

    public Category() {
        tasks = new ArrayList<>();
    }

    public Category(String categoryName) {
        this.categoryName = categoryName.toString();
        tasks = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName.toString();
    }

    public int getTotalTasks() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) {

        if(taskIndex < tasks.size()) {
            return tasks.get(taskIndex);
        }

        return null;

    }

    public void addTask(Task task) {

        if(task == null) {
            return;
        }

        tasks.add(task);

    }

}