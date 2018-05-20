package model.project;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que representa una etiqueta sempre relacionada directament amb una tasca.
 */
public class Tag implements Serializable {
    public final static int serialVersionUID = 1236;

    private String id;
    private String name;
    private Color color;

    /**
     * Constructor amb paràmetres.
     * @param id Id de l'etiqueta.
     * @param name Nom de l'etiqueta.
     * @param color Color de l'etiqueta.
     */
    public Tag(String id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    /**
     * Constructor amb paràmetres.
     * @param name Nom de l'etiqueta.
     * @param color Color de l'etiqueta.
     */
    public Tag(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Getter de l'identificador.
     * @return Identificador de l'etiqueta.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter del nom de l'etiqueta.
     * @return Etiqueta retornada.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter del nom de l'etiqueta.
     * @param name Nom que volem settejar.
     */
    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    /**
     * Getter del color de l'etiqueta.
     * @return Color de l'etiqueta.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter del color de l'etiqueta.
     * @param color Color a settejar.
     */
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
        return Objects.equals(name, tag.name) && Objects.equals(color, tag.color) && Objects.equals(id, tag.id);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

}