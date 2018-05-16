package model.project;

import model.user.User;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Project implements Serializable{

    private final static int INVALID_INDEX = -1;

    private String id;
    private String name;
    private Color color;
    private ArrayList<Category> categories;
    private ArrayList<User> users;
    private Image background;
    private boolean isOwner;

    public Project() {}

    public Project(String id, String name, Color color, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.isOwner = isOwner;
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Project(String id, String name, Color color, ArrayList<Category> categories, ArrayList<User> users,
                   Image background, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.categories = categories;
        this.users = users;
        this.background = background;
        this.isOwner = isOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCategoriesSize() {
        return categories.size();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        if(categories != null) {
            this.categories = categories;
        }
    }

    public int getCategoryIndex(Category category) {
        if(categories.contains(category)) {
            return categories.indexOf(category);
        } else {
            return INVALID_INDEX;
        }
    }

    public Category getCategory(int categoryIndex) {
        if(categoryIndex < categories.size()) {
            return categories.get(categoryIndex);
        } else {
            return null;
        }
    }

    public Category getCategoryWithId(String categoryId) {

        for(Category category : categories) {
            if(category.getId().equals(categoryId)) {
                return category;
            }
        }

        return null;

    }

    public void setCategory(Category category) {
        if(category != null) {

            for (int i = 0; i < categories.size(); i++) {
                if (category.getId().equals(categories.get(i).getId())) {

                    if (category.getName() != null) {
                        categories.get(i).setName(category.getName());
                    }

                    if (category.getOrder() != -1) {
                        Category aux = categories.get(i);
                        aux.setOrder(category.getOrder());
                        categories.remove(i);
                        categories.add(aux.getOrder(), aux);
                    }

                    return;

                }
            }

            categories.add(category.getOrder(), category);

        }
    }

    public void deleteCategory(Category category) {
        if(categories.contains(category)) {
            categories.remove(category);
        }
    }

    public void swapCategories(int firstCategoryIndex, int secondCategoryIndex) {
        if(firstCategoryIndex >= 0 && firstCategoryIndex < categories.size() && secondCategoryIndex < categories.size()
                && secondCategoryIndex >= 0) {
            Category category1 = categories.get(firstCategoryIndex);
            Category category2 = categories.get(secondCategoryIndex);
            categories.set(firstCategoryIndex, category2);
            categories.set(secondCategoryIndex, category1);
        }
    }

    public int getUsersSize() {
        return users.size();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        if(users != null) {
            this.users = users;
        }
    }

    public User getUser(int userIndex) {
        if(userIndex >= 0 && userIndex < users.size()) {
            return users.get(userIndex);
        } else {
            return null;
        }
    }

    public void addUser(User user) {
        if(!users.contains(user)) {
            users.add(user);
        }
    }

    public void deleteUser(int userIndex) {
        if(userIndex >= 0 && userIndex < users.size()) {
            users.remove(userIndex);
        }
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(name, project.name) &&
                Objects.equals(color, project.color) &&
                Objects.equals(categories, project.categories) &&
                Objects.equals(users, project.users) &&
                Objects.equals(background, project.background);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, categories, users, background);
    }

}