package model.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe que representa la categoria d'un projecte.
 * Té un id únic.
 */
public class Category implements Serializable {

    public final static int INVALID_INDEX = -1;
    public final static int serialVersionUID = 1234;

    private String id;
    private String name;
    private int order = -1;
    private ArrayList<model.project.Task> tasks;

    /**
     * Crea una categoria a partir d'un <code>String</code>.
     * @param name nom de la categoria
     */
    public Category(String name) {
        this.name = name.toString();
        order = INVALID_INDEX;
        tasks = new ArrayList<>();
    }

    /**
     * Crea una categoria amb els atributs inciats amb el que es passa per paràmetre
     * @param id identificador de la categoria
     * @param name nom de la categoria
     * @param order posició de la categoria
     * @param tasks conjunt de tasques que conté la categoria
     */
    public Category(String id, String name, int order, ArrayList<Task> tasks) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.tasks = tasks;
    }

    /**
     * Funció per a recuperar l'identificador.
     * @return identificador de la categoria
     */
    public String getId() {
        return id;
    }

    /**
     * Funció per a recuperar el nom
     * @return nom de la categoria
     */
    public String getName() {
        return name;
    }

    /**
     * Procediment per a assignar el nom de la categoria.
     * @param name nom que tindrà la categoria
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Funció per a recuperar l'ordre o posició de la categoria.
     * @return ordre o posició de la categoria
     */
    public int getOrder() {
        return order;
    }

    /**
     * Procediment per a assignar l'ordre o posició de la categoria.
     * @param order ordre o posició que tindrà la categoria
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Funció que recupera el número de tasques que té la categoria.
     * @return número de tasques
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Funció que recupera la llista de tasques de la categoria.
     * @return llista de tasques
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Procediment per a assignar la llista de tasques de la categoria.
     * @param tasks llista de tasques que tindrà
     */
    public void setTasks(ArrayList<Task> tasks) {
        if(tasks != null) {
            this.tasks = tasks;
        }
    }

    /**
     * Procediment per a recuperar en quina posició de la llista està una tasca.
     * @param task tasca de la qual es vol saber la posició
     * @return posició de la tasca en la llista.
     */
    public int getTaskIndex(Task task) {
        if(tasks.contains(task)) {
            return tasks.indexOf(task);
        } else {
            return INVALID_INDEX;
        }
    }

    /**
     * Funció per a recuperar una tasca de la llista.
     * @param taskIndex posició de la tasca en la llista
     * @return retorna la tasca de la posició que es passa. Si no n'hi ha cap en aquest índex retorna <code>null</code>
     */
    public Task getTask(int taskIndex) {
        if(taskIndex < tasks.size()) {
            return tasks.get(taskIndex);
        } else {
            return null;
        }
    }

    /**
     * Procediment per a assignar una tasca. Si ja existeix l'actualitza i sinó, l'afegeix al final.
     * @param task tasca a afegir
     */
    public void setTask(Task task) {
        if(getTaskWithId(task.getID()) != null) {
            tasks.remove(task);
            tasks.add(task.getOrder(), task);
        } else {
            tasks.add(task.getOrder(), task);
        }
    }

    /**
     * Procediment per a esborrar una tasca.
     * @param task tasca a esborrar
     */
    public void deleteTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
        }
    }

    /**
     * Funció per a recuperar una tasca a partir d'un identificador.
     * @param id_task identificador de la tasca a esborrar
     * @return si existeix una tasca amb aquest identificador la retorna. Si no, retorna <code>null</code>
     */
    public Task getTaskWithId(String id_task){
        for(Task t: tasks) {
            if (t.getID().equals(id_task)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category category = (Category) o;

        return order == category.order &&
                Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(tasks, category.tasks);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, order, tasks);
    }

}