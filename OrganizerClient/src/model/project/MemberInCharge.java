package model.project;

import java.io.Serializable;

public class MemberInCharge implements Serializable{

    private String name;
    private String color;
    private String id_category;
    private String id_task;
    private String id;

    public MemberInCharge(String id_category, String id_task, String id, String name, String color) {
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

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
