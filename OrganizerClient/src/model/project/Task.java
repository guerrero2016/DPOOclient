package model.project;

import model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe que representa una tasca
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
     * Crea una tasca amb els camps per defecte
     */
    public Task() {
        tags = new ArrayList<>();
        users = new ArrayList<>();
        order = INVALID_INDEX;
    }

    /**
     * Crea una tasca amb el nom especificat i la resta d'atributs per defecte
     * @param name Nom de la tasca
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
     * Crea una tasca amb tots els atributs (menys l'identificador) especificats pels parametres d'entrada
     * @param name Nom de la tasca
     * @param description Descripcio de la tasca
     * @param tags Llista d'etiquetes de la tasca
     * @param users Usuaris assignats a la tasca
     * @param order Ordre o posicio de la tasca
     */
    public Task(String name, String description, ArrayList<Tag> tags, ArrayList<User> users, int order) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.tags = tags;
        this.order = order;
    }

    /**
     * Constructor encarregat de clonar una tasca
     * @param task Tasca
     */
    public Task(Task task) {
        id = task.id;
        name = task.name;
        description = task.description;
        tags = new ArrayList<>();
        for(int i = 0; i < task.tags.size(); i++) {
            tags.add(new Tag(task.getTag(i)));
        }
        users = new ArrayList<>();
        for(int i = 0; i < task.users.size(); i++) {
            users.add(new User(task.getUser(i).getUserName()));
        }
        order = task.order;
        isFinished = task.isFinished;
    }

    /**
     * Funcio que recupera l'identificador
     * @return Identificador de la tasca
     */
    public String getId() {
        return id;
    }

    /**
     * Procediment que assigna l'identificador passat per parametre a la tasca
     * @param id Identificador que s'assignara
     */
    public void setId(String id) {
        if(id != null) {
            this.id = id;
        }
    }

    /**
     * Funcio que recupera el nom de la tasca
     * @return Nom de la tasca
     */
    public String getName() {
        return name;
    }

    /**
     * Procediment que assigna un nom a la tasca
     * @param name Nom a assignar
     */
    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    /**
     * Funcio que recupera la descripcio de la tasca
     * @return Descripcio de la tasca
     */
    public String getDescription() {
        return description;
    }

    /**
     * Procediment que assigna una descripcio a la tasca
     * @param description Descripcio que s'assignara
     */
    public void setDescription(String description) {
        if(description != null) {
            this.description = description;
        }
    }

    /**
     * Funcio que recupera el número d'etiquetes que te la tasca
     * @return Numero d'etiquetes
     */
    public int getTagsSize() {
        return tags.size();
    }

    /**
     * Funcio que recupera la llista d'etiquetes de la tasca
     * @return Llista d'etiquetes
     */
    public ArrayList<Tag> getTags() {
        return tags;
    }

    /**
     * Procediment que assigna una llista d'etiquetes a la tasca
     * @param tags Llista d'etiquetes que s'assginara
     */
    public void setTags(ArrayList<Tag> tags) {
        if(tags != null) {
            this.tags = tags;
        }
    }

    /**
     * Funcio que recupera la posicio d'una etiqueta a la llista
     * @param tag Etiqueta a buscar
     * @return Index de la etiqueta. Si no existeix retorna <code>INVALID_INDEX</code>
     */
    public int getTagIndex(Tag tag) {
        if(tags.contains(tag)) {
            return tags.indexOf(tag);
        } else {
            return INVALID_INDEX;
        }
    }

    /**
     * Funcio que recupera l'ordre d'una etiqueta
     * @param tag Etiqueta a buscar l'ordre
     * @return Ordre de l'etiqueta. Si no existeix, retorna <code>INVALID_INDEX</code>
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
     * Funcio que recupera una etiqueta a partir de la seva posicio a la llista
     * @param tagIndex Index de l'etiqueta
     * @return Tasca que esta a l'index especificat. Si no n'hi ha, retorna <code>null</code>
     */
    public Tag getTag(int tagIndex) {
        if(tagIndex < tags.size()) {
            return tags.get(tagIndex);
        } else {
            return null;
        }
    }

    /**
     * Funcio que recupera una etiqueta a partir del seu identificador
     * @param tagId Identificador de l'etiqueta a buscar
     * @return Etiqueta amb l'identificador. Si no existeix retorna <code>null</code>
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
     * Procediment que afegeix una etiqueta a la tasca
     * @param tag Etiqueta a afegir
     */
    public void addTag(Tag tag){
        if(tag != null && !tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Procediment encarregat d'eliminar una etiqueta de la tasca
     * @param tag Etiqueta a eliminar
     */
    public void removeTag(Tag tag) {
        if(tags.contains(tag)) {
            tags.remove(tag);
        }
    }

    /**
     * Funcio que recupera el numero d'usuaris que tenen assignada la tasca
     * @return Numero d'usuaris de la tasca
     */
    public int getUsersSize() {
        return users.size();
    }

    /**
     * Funcio que recupera la llista d'usuaris que tenen assignada la tasca
     * @return Llista d'usuaris de la tasca
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Procediment que assigna la llista d'usuaris
     * @param users Llista d'usuaris a assignar
     */
    public void setUsers(ArrayList<User> users) {
        if(users != null) {
            this.users = users;
        }
    }

    /**
     * Funcio que recupera la posicio a la llista d'un usuari
     * @param user Usuari a trobar
     * @return Posicio de l'usuari a la llista. Si no existeix retorna <code>null</code>
     */
    public int getUserIndex(User user) {
        if(users.contains(user)) {
            return users.indexOf(user);
        } else {
            return INVALID_INDEX;
        }
    }

    /**
     * Funcio encarregada de recuperar un usuari a partir de la seva posicio a la llista
     * @param userIndex Index de l'usuari
     * @return Usuari que esta a l'index. Si no existeix retorna <code>null</code>
     */
    public User getUser(int userIndex) {
        if(userIndex < users.size()) {
            return users.get(userIndex);
        } else {
            return null;
        }
    }

    /**
     * Procediment encarregat d'afegir un usuari a la tasca
     * @param user Usuari a afegir
     */
    public void addUser(User user){
        if(user != null && !users.contains(user)) {
            users.add(user);
        }
    }

    /**
     * Procediment encarregat d'eliminar un usuari de la tasca
     * @param user Usuari a eliminar
     */
    public void removeUser(User user) {
        if(users.contains(user)) {
            users.remove(user);
        }
    }

    /**
     * Procediment encarregat d'eliminar un usuari de la tasca
     * @param userIndex Posició de l'usuari a la llista
     */
    public void removeUser(int userIndex) {
        if(userIndex >= 0 && userIndex < users.size()) {
            users.remove(userIndex);
        }
    }

    /**
     * Funcio que recupera l'ordre o posicio de la tasca.
     * @return Ordre o posicio de la tasca
     */
    public int getOrder() {
        return order;
    }

    /**
     * Procediment que assigna un ordre o posicio a la tasca
     * @param order Ordre a assignar
     */
    public void setOrder(int order) {
        if(order >= 0) {
            this.order = order;
        }
    }

    /**
     * Funcio que retorna si la tasca esta acabada o no
     * @return Estat de la tasca
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Procediment que assigna si esta acabada o no la tasca
     * @param finished Estat de la tasca
     */
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * Metode encarregat d'actualitzar la tasca a partir d'una altra
     * @param task Tasca
     */
    public void updateTask(Task task) {
        id = task.id;
        name = task.name;
        description = task.description;
        tags = new ArrayList<>();
        tags.addAll(task.tags);
        users = new ArrayList<>();
        users.addAll(task.users);
        order = task.order;
        isFinished = task.isFinished;
    }

    /**
     * Equals
     * @param o Objecte
     * @return Si equival
     */
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

    /**
     * Hashcode
     * @return Hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description, tags, users, order);
    }

}