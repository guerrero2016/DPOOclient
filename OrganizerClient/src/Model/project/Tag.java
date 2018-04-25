package Model.project;

import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable{

    private String name;
    private String color;
    private String nomTasca;
    private String nomCategoria;

    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
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

    public String getNomTasca() {
        return nomTasca;
    }

    public void setNomTasca(String nomTasca) {
        this.nomTasca = nomTasca;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
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