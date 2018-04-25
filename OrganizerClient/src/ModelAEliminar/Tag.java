package ModelAEliminar;

import java.awt.*;
import java.util.Objects;

public class Tag {

    private String name;
    private Color color;

    public Tag(String name, Color color) {

        if(name != null) {
            this.name = name.toString();
        }

        this.color = color;
    }

    public Tag(Tag tag) {

        if(tag.name != null) {
            name = tag.name.toString();
        }

        color = tag.color;

    }

    public String getName() {

        if (name != null) {
            return name;
        }

        return null;

    }

    public void setName(String name) {
        if(name != null) {
            this.name = name.toString();
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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