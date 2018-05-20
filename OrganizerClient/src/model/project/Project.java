package model.project;

import model.DataManager;
import model.user.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Project implements Serializable{

    private final static int INVALID_INDEX = -1;
    public final static int serialVersionUID = 1235;

    private String id;
    private String name;
    private Color color;
    private ArrayList<Category> categories;
    private ArrayList<User> users;
    private byte[] background;
    private boolean isOwner;
    private String ownerName;

    public Project() {
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Project(String id, String name, Color color, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.isOwner = isOwner;
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Project(String id, String name, String color, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.color = Color.decode(color);
        this.isOwner = isOwner;
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Project(String id, String name, Color color, ArrayList<Category> categories, ArrayList<User> users, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.categories = categories;
        this.users = users;
        this.isOwner = isOwner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public void setColorFromCode(String color) {
        this.color = Color.decode(color);
    }

    public String getHexColor () {
        if (color == null) return null;
        int rgb = color.getRGB()&0xffffff;
        String zeros = "000000";
        String data = Integer.toHexString(rgb);
        return "#" + (zeros.substring(data.length()) + data).toUpperCase();
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
            category.setOrder(categories.size());
            categories.add(category);
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
            int aux = category1.getOrder();
            category1.setOrder(category2.getOrder());
            category2.setOrder(aux);
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

    public BufferedImage getBackground() {
        try {
            if (background != null) {
                return ImageIO.read(new ByteArrayInputStream(this.background));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBackground(BufferedImage background) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(background, "png", baos);
            baos.flush();
            this.background = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public void setMembersName (ArrayList<String> names) {
        ArrayList<User> users = new ArrayList<>();
        for (String name:names) {
            users.add(new User(name));
        }
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
