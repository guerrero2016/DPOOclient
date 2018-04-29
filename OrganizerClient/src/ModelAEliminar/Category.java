package ModelAEliminar;

import java.util.ArrayList;
import java.util.Objects;

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

    public String getName() {
        return categoryName;
    }

    public void setName(String categoryName) {
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

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public int getTaskIndex(Task task) {
        return tasks.indexOf(task);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category category = (Category) o;

        return Objects.equals(categoryName, category.categoryName) && Objects.equals(tasks, category.tasks);

    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, tasks);
    }
}