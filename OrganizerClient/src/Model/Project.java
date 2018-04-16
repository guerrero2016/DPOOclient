package Model;

import java.awt.*;
import java.util.ArrayList;

public class Project {

    private Image background;
    private String projectName;
    private ArrayList<Category> categories;
    private ArrayList<User> users;

    public Project() {
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Project(String projectName) {
        this.projectName = projectName.toString();
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    public int getTotalCategories() {
        return categories.size();
    }

    public Category getCategory(int categoryIndex) {

        if(categoryIndex < categories.size()) {
            return categories.get(categoryIndex);
        }

        return null;

    }

    public void addCategory(Category category) {

        if(category == null) {
            return;
        }

        categories.add(category);

    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName.toString();
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image backgroundImage) {
        background = backgroundImage;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(int userIndex) {

        if(userIndex < users.size()) {
            return users.get(userIndex);
        }

        return null;

    }

    public int getTotalUsers() {
        return users.size();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}