package model.project;

import model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe que representa una tasca.
 */
public class Task implements Serializable{

    public final static int INVALID_INDEX = -1;
    public final static int serialVersionUID = 1237;

    private String id;
    private String name;
    private String description;
    private ArrayList<Tag> tags;
    private ArrayList<User> users;
    private int order;
    private boolean isFinished;

    /**
     * Crea una tasca amb els camps per defecte.
     */
    public Task() {
        tags = new ArrayList<>();
        users = new ArrayList<>();
        order = INVALID_INDEX;
    }

    /**
     * Crea una tasca amb el nom especificat i la resta d'atributs per defecte.
     * @param name nom de la tasca
     */
    public Task(String name) {

        if(name != null) {
            this.name = name;
        }

        tags = new ArrayList<>();
        users = new ArrayList<>();
        order = INVALID_INDEX;

    }

    /**
     * Crea una tasca amb tots els atributs (menys l'identificador) especificats pels paràmetres d'entrada.
     * @param name nom de la tasca
     * @param description descripció de la tasca
     * @param tags llista d'etiquetes de la tasca
     * @param users usuaris assignats a la tasca
     * @param order ordre o posició de la tasca
     */
    public Task(String name, String description, ArrayList<Tag> tags, ArrayList<User> users, int order) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.tags = tags;
        this.order = order;
    }

    /**
     * Funció que recupera l'identificador
     * @return identificador de la tasca
     */
    public String getID() {
        return id;
    }

    /**
     * Procediment que assigna l'identificador passat per paràmetre a la tasca
     * @param id identificador que s'assignarà
     */
    public void setId(String id) {
        if(id != null) {
            this.id = id;
        }
    }

    /**
     * Funció que recupera el nom de la tasca
     * @return nom de la tasca
     */
    public String getName() {
        return name;
    }

    /**
     * Procediment que assigna un nom a la tasca.
     * @param name nom a assignar
     */
    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    /**
     * Funció que recupera la descripció de la tasca.
     * @return descripció de la tasca
     */
    public String getDescription() {
        return description;
    }

    /**
     * Procediment que assigna una descripció a la tasca.
     * @param description descripció que s'assignarà.
     */
    public void setDescription(String description) {
        if(description != null) {
            this.description = description;
        }
    }

    /**
     * Funció que recupera el número d'etiquetes que té la tasca.
     * @return número d'etiquetes
     */
    public int getTagsSize() {
        return tags.size();
    }

    /**
     * Funció que recupera la llista d'etiquetes de la tasca.
     * @return llista d'etiquetes.
     */
    public ArrayList<Tag> getTags() {
        return tags;
    }

    /**
     * Procediment que assigna una llista d'etiquetes a la tasca
     * @param tags llista d'etiquetes que s'assginarà
     */
    public void setTags(ArrayList<Tag> tags) {
        if(tags != null) {
            this.tags = tags;
        }
    }

    /**
     * Funció que recupera la posició d'una etiqueta a la llista
     * @param tag etiqueta a buscar
     * @return índex de la etiqueta. Si no existeix retorna <code>INVALID_INDEX</code>
     */
    public int getTagIndex(Tag tag) {
        if(tags.contains(tag)) {
            return tags.indexOf(tag);
        } else {
            return INVALID_INDEX;
        }
    }

    /**
     * Funció que recupera l'ordre d'una etiqueta.
     * @param tag etiqueta a buscar l'ordre
     * @return ordre de l'etiqueta. Si no existeix retorna <code>INVALID_INDEX</code>
     */
    public int getTagOrder(Tag tag) {
        for(int i = 0; i < tags.size();i++) {
            if(tags.get(i).getId().equals(tag.getId())) {
                return (i);
            }
        }
        return INVALID_INDEX;
    }

    /**
     * Funció que recupera una etiqueta a partir de la seva posicio a la llista
     * @param tagIndex índex de l'etiqueta
     * @return tasca que està a l'index especificat. Si no n'hi ha, retorna <code>null</code>
     */
    public Tag getTag(int tagIndex) {
        if(tagIndex < tags.size()) {
            return tags.get(tagIndex);
        } else {
            return null;
        }
    }

    /**
     * Funció que recupera una etiqueta a partir del seu identificador.
     * @param tagId identificador de l'etiqueta a buscar
     * @return etiqueta amb l'identificador. Si no existeix retorna <code>null</code>
     */
    public Tag getTagWithId(String tagId) {

        for(Tag tag : tags) {
            if(tag.getId().equals(tagId)) {
                return tag;
            }
        }

        return null;

    }

    /**
     * Procediment que afegeix una etiqueta a la tasca.
     * @param tag etiqueta a afegir
     */
    public void addTag(Tag tag){
        if(tag != null && !tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Procediment encarregat d'eliminar una etiqueta de la tasca.
     * @param tag etiqueta a eliminar
     */
    public void removeTag(Tag tag) {
        if(tags.contains(tag)) {
            tags.remove(tag);
        }
    }

    /**
     * Funció que recupera el número d'usuaris que tenen assignada la tasca.
     * @return número d'usuaris de la tasca
     */
    public int getUsersSize() {
        return users.size();
    }

    /**
     * Funció que recupera la llista d'usuaris que tenen assignada la tasca.
     * @return llista d'usuaris de la tasca
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Procediment que assigna la llista d'usuaris.
     * @param users llista d'usuaris a assignar
     */
    public void setUsers(ArrayList<User> users) {
        if(users != null) {
            this.users = users;
        }
    }

    /**
     * Funció que recupera la posició a la llista d'un usuari.
     * @param user usuari a trobar
     * @return posició de l'usuari a la llista. Si no existeix retorna <code>null</code>
     */
    public int getUserIndex(User user) {
        if(users.contains(user)) {
            return users.indexOf(user);
        } else {
            return INVALID_INDEX;
        }
    }

    /**
     * Funció encarregada de recuperar un usuari a partir de la seva posició a la llista
     * @param userIndex índex de l'usuari
     * @return usuari que està a l'índex. Si no existeix retorna <code>null</code>
     */
    public User getUser(int userIndex) {
        if(userIndex < users.size()) {
            return users.get(userIndex);
        } else {
            return null;
        }
    }

    /**
     * Procediment encarregat d'afegir un usuari a la tasca.
     * @param user usuari a afegir
     */
    public void addUser(User user){
        if(user != null && !users.contains(user)) {
            users.add(user);
        }
    }

    /**
     * Procediment encarregat d'eliminar un usuari de la tasca.
     * @param user usuari a eliminar
     */
    public void removeUser(User user) {
        if(users.contains(user)) {
            users.remove(user);
        }
    }

    /**
     * Procediment encarregat d'eliminar un usuari de la tasca.
     * @param userIndex posició de l'usuari a la llista
     */
    public void removeUser(int userIndex) {
        if(userIndex >= 0 && userIndex < users.size()) {
            users.remove(userIndex);
        }
    }

    /**
     * Funció que recupera l'ordre o posició de la tasca.
     * @return ordre o posició de la tasca
     */
    public int getOrder() {
        return order;
    }

    /**
     * Procediment que assigna un ordre o posició a la tasca.
     * @param order ordre a assignar
     */
    public void setOrder(int order) {
        if(order >= 0) {
            this.order = order;
        }
    }

    /**
     * Funció que retorna si la tasca està acabada o no.
     * @return estat de la tasca
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Procediment que assigna si està acabada o no la tasca.
     * @param finished estat de la tasca
     */
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;
        return order == task.order &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(tags, task.tags) &&
                Objects.equals(users, task.users);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, tags, users, order);
    }

}