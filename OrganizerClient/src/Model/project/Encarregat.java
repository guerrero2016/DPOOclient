package Model.project;

public class Encarregat {
    private String name;
    private String  color;
    private String nomTasca;
    private String nomCategoria;

    public Encarregat(String name, String color) {
        this.name = new String(name);
        this.color = color;
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

    public String getColor() {
        return color;
    }
}
