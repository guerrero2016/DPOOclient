package Model.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Category implements Serializable{

    private String id;
    private String name;
    private int order;
    private ArrayList<Task> tasks;

    public Category(String name, int order, ArrayList<Task> tasks) {
        this.id = UUID.randomUUID().toString();
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

    public int getOrder() {
        return order;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
}