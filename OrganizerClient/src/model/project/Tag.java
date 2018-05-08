package model.project;

import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable{

    private String id_category;
    private String id_task;
    private String id;
    private String name;
    private String color;

    public Tag(String id_category, String id_task, String id, String name, String color) {
        this.id_category = id_category;
        this.id_task = id_task;
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public String getId_category() {
        return id_category;
    }

    public String getId_task() {
        return id_task;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tag tag = (Tag) o;

        return Objects.equals(name, tag.name) && Objects.equals(color, tag.color);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

}