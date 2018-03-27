package Model;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        if(task != null) {
            tasks.add(task);
        }
    }

    public Task getTask(int position) {
        if(position < tasks.size()) {
            return tasks.get(position);
        }
        return null;
    }

    public void removeTask(int position) {
        if(position < tasks.size()) {
            tasks.remove(position);
        }
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

}