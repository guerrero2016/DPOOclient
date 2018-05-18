package model.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Category implements Serializable {

    public final static int INVALID_INDEX = -1;
    public final static int serialVersionUID = 1234;

    private String id;
    private String name;
    private int order;
    private ArrayList<model.project.Task> tasks;

    public Category(String name) {
        this.name = name.toString();
        order = INVALID_INDEX;
        tasks = new ArrayList<>();
    }

    public Category(String name, int order, ArrayList<Task> tasks) {
        this.name = name;
        this.order = order;
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getTasksSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        if(tasks != null) {
            this.tasks = tasks;
        }
    }

    public int getTaskIndex(Task task) {
        if(tasks.contains(task)) {
            return tasks.indexOf(task);
        } else {
            return INVALID_INDEX;
        }
    }

    public Task getTask(int taskIndex) {
        if(taskIndex < tasks.size()) {
            return tasks.get(taskIndex);
        } else {
            return null;
        }
    }

    public void setTask(Task task) {
        if(tasks.contains(task)) {
            tasks.remove(task);
            tasks.add(task.getOrder(), task);
        } else {
            tasks.add(task.getOrder(), task);
        }
    }

    public void addTask(Task task) {
        if(task != null && !tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public void deleteTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
        }
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

        return order == category.order &&
                Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(tasks, category.tasks);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, order, tasks);
    }

}