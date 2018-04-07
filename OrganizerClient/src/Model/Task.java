package Model;

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

    public Task(String name, String description, ArrayList<Tag> tags, ArrayList<User> members) {
        this.name = new String(name);
        this.description = new String(description);
        this.tags = new ArrayList<>(tags);
        this.members = new ArrayList<>(members);
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

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = new ArrayList<>(tags);
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = new ArrayList<>(members);
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