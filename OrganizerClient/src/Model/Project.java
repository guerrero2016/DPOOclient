package Model;

import java.awt.*;

public class Project {
    private Color color;
    private String name;

    public Project(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
