package model.project;

import model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Task implements Serializable{

    private final static int INVALID_INDEX = -1;
    public final static int serialVersionUID = 1237;

    private String id;
    private String name;
    private String description;
    private ArrayList<Tag> tags;
    private ArrayList<User> users;
    private int order;

    public Task() {
        tags = new ArrayList<>();
        users = new ArrayList<>();
        order = INVALID_INDEX;
    }

    public Task(String name) {

        if(name != null) {
            this.name = name;
        }

        tags = new ArrayList<>();
        users = new ArrayList<>();
        order = INVALID_INDEX;

    }

    public Task(String name, String description, ArrayList<Tag> tags, ArrayList<User> users, int order) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.tags = tags;
        this.order = order;
    }

    public String getID() {
        return id;
    }

    public void setId(String id) {
        if(id != null) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null) {
            this.description = description;
        }
    }

    public int getTagsSize() {
        return tags.size();
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        if(tags != null) {
            this.tags = tags;
        }
    }

    public int getTagIndex(Tag tag) {
        if(tags.contains(tag)) {
            return tags.indexOf(tag);
        } else {
            return INVALID_INDEX;
        }
    }

    public Tag getTag(int tagIndex) {
        if(tagIndex < tags.size()) {
            return tags.get(tagIndex);
        } else {
            return null;
        }
    }

    public Tag getTagWithId(String tagId) {

        for(Tag tag : tags) {
            if(tag.getId().equals(tagId)) {
                return tag;
            }
        }

        return null;

    }

    public void addTag(Tag tag){
        if(tag != null && !tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public void removeTag(Tag tag) {
        if(tags.contains(tag)) {
            tags.remove(tag);
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

    public int getUserIndex(User user) {
        if(users.contains(user)) {
            return users.indexOf(user);
        } else {
            return INVALID_INDEX;
        }
    }

    public User getUser(int userIndex) {
        if(userIndex < users.size()) {
            return users.get(userIndex);
        } else {
            return null;
        }
    }

    public void addUser(User user){
        if(user != null && !users.contains(user)) {
            users.add(user);
        }
    }

    public void removeUser(User user) {
        if(users.contains(user)) {
            users.remove(user);
        }
    }

    public void removeUser(int userIndex) {
        if(userIndex >= 0 && userIndex < users.size()) {
            users.remove(userIndex);
        }
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        if(order >= 0) {
            this.order = order;
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

        Task task = (Task) o;
        return order == task.order &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(tags, task.tags) &&
                Objects.equals(users, task.users);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, tags, users, order);
    }

}