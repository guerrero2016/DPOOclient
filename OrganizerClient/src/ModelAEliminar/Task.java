package ModelAEliminar;

import java.util.ArrayList;
import java.util.Objects;

public class Task {

    private String name;
    private String description;
    private ArrayList<Tag> tags;
    private ArrayList<User> members;  //Check if only need username

    public Task() {
        tags = new ArrayList<>();
        members = new ArrayList<>();
    }

    public Task(String name) {
        this.name = name.toString();
        tags = new ArrayList<>();
        members = new ArrayList<>();
    }

    public Task(String name, String description, ArrayList<Tag> tags, ArrayList<User> members) {
        this.name = new String(name);
        this.description = new String(description);
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        this.members = members != null ? new ArrayList<>(members) : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = new String(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = new String(description);
    }

    public int getTotalTags() {
        return tags.size();
    }

    public ArrayList<Tag> getAllTags() {
        return tags;
    }

    public Tag getTag(int tagPosition) {

        if(tagPosition < tags.size()) {
            return tags.get(tagPosition);
        }

        return null;

    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public int getTotalUsers() {
        return members.size();
    }

    public User getUser(int memberPosition) {

        if(memberPosition < members.size()) {
            return members.get(memberPosition);
        }

        return null;

    }

    public ArrayList<User> getUsers() {
        return members;
    }

    public void addUser(User member) {
        members.add(member);
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
                Objects.equals(tags, task.tags) && Objects.equals(members, task.members);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, tags, members);
    }

}