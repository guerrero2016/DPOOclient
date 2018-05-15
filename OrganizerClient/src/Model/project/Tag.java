package Model.project;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable {

    private String name;
    private Color color;

    public Tag(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if(color != null) {
            this.color = color;
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

        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name) && Objects.equals(color, tag.color);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

}