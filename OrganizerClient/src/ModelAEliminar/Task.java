package ModelAEliminar;

import java.util.ArrayList;
import java.util.Objects;

public class Task {

    private String name;
    private String description;
    private ArrayList<Tag> tags;
    private ArrayList<User> users;  //Check if only need username

    public Task() {
        tags = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Task(String name) {

        if(name != null) {
            this.name = name.toString();
        }

        tags = new ArrayList<>();
        users = new ArrayList<>();

    }

    public Task(String name, String description, ArrayList<Tag> tags, ArrayList<User> users) {

        if(name != null) {
            this.name = name.toString();
        }

        if(description != null) {
            this.description = description.toString();
        }

        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();

    }

    public Task(Task task) {

        if(task.name != null) {
            name = task.name.toString();
        }

        if(task.description != null) {
            description = task.description.toString();
        }

        tags = task.tags != null ? new ArrayList<>(task.tags) : new ArrayList<>();
        users = task.users != null ? new ArrayList<>(task.users) : new ArrayList<>();

    }

    public String getName() {

        if(name != null) {
            return name.toString();
        }

        return null;

    }

    public void setName(String name) {
        if(name != null) {
            this.name = name.toString();
        }
    }

    public String getDescription() {

        if(description != null) {
            return description;
        }

        return null;

    }

    public void setDescription(String description) {
        if(description != null) {
            this.description = description.toString();
        }
    }

    public int getTotalTags() {
        return tags.size();
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public Tag getTag(int tagPosition) {

        if(tagPosition < tags.size()) {
            return tags.get(tagPosition);
        }

        return null;

    }

    public void addTag(Tag tag) {
        tags.add(new Tag(tag));
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public int getTotalUsers() {
        return users.size();
    }

    public User getUser(int memberPosition) {

        if(memberPosition < users.size()) {
            return users.get(memberPosition);
        }

        return null;

    }

    public int getTagIndex(Tag tag) {
        return tags.indexOf(tag);
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void addUser(User user) {
        users.add(new User(user));
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

        Task task = (Task) o;

        return Objects.equals(name, task.name) && Objects.equals(description, task.description) &&
                Objects.equals(tags, task.tags) && Objects.equals(users, task.users);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, tags, users);
    }

}