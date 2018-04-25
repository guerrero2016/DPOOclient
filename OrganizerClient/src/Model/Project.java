package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Project {

    private String name;
    private ArrayList<Category> categories;
    private ArrayList<User> users;
    private Image background;

    public Project() {
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Project(String name) {

        if(name != null) {
            this.name = name.toString();
        }

        categories = new ArrayList<>();
        users = new ArrayList<>();

    }

    public int getTotalCategories() {
        return categories.size();
    }

    public ArrayList<Category> getCategories() {
        return categories;
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


    public void removeCategory(int categoryIndex) {
        if(categoryIndex < categories.size()) {
            categories.remove(categoryIndex);
        }
    }

    public String getName() {

        if(name != null) {
            return name;
        }

        return null;

    }

    public void setName(String name) {
        if(name != null) {
            this.name = name.toString();
        }
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image backgroundImage) {
        background = backgroundImage;
    }

    public int getTotalUsers() {
        return users.size();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(int userIndex) {

        if(userIndex < users.size()) {
            return users.get(userIndex);
        }

        return null;

    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(int userIndex) {
        if(userIndex < users.size()) {
            users.remove(userIndex);
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

        Project project = (Project) o;

        return Objects.equals(name, project.name) && Objects.equals(categories, project.categories) &&
                Objects.equals(users, project.users) && Objects.equals(background, project.background);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, categories, users, background);
    }

}