package model.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Task implements Serializable{

    private String id;
    private String name;
    private int order;
    private String description;
    private ArrayList<Tag> tags;
    private ArrayList<String> membersName;
    private String nomCategoria;
    private ArrayList<MemberInCharge> encarregats;

    public Task(String name, int order, String description, ArrayList<Tag> tags, ArrayList<MemberInCharge> encarregats) {
        this.name = name;
        this.order = order;
        this.description = description;
        this.encarregats = encarregats;
        this.tags = tags;
    }

    public ArrayList<MemberInCharge> getEncarregats() {
        return encarregats;
    }

    public Task(String name, int order, String description, ArrayList<Tag> tags, ArrayList<String> membersName,
                String nom_categoria) {
        this.name = name;
        this.order = order;
        this.description = description;
        this.tags = tags;
        this.membersName = membersName;
        this.nomCategoria = nom_categoria;
    }

    public Object getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public ArrayList<String> getMembersName() {
        return membersName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void setMembersName(ArrayList<String> membersName) {
        this.membersName = membersName;
    }

    public void addMemberName(String memberName){
        this.membersName.add(memberName);
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return order == task.order &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(tags, task.tags) &&
                Objects.equals(membersName, task.membersName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, order, description, tags, membersName);
    }


    public String getNomCategoria() {
        return nomCategoria;
    }
}