package model.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe que representa la categoria d'un projecte.
 * Te un id unic
 */
public class Category implements Serializable {

    public final static int INVALID_INDEX = -1;
    public final static int serialVersionUID = 1234;

    private String id;
    private String name;
    private int order = -1;
    private ArrayList<Task> tasks;

    /**
     * Crea una categoria a partir d'un <code>String</code>.
     * @param name Nom de la categoria
     */
    public Category(String name) {
        this.name = name;
        order = INVALID_INDEX;
        tasks = new ArrayList<>();
    }

    /**
     * Crea una categoria amb els atributs inciats amb el que es passa per parametre
     * @param id Identificador de la categoria
     * @param name Nom de la categoria
     * @param order Posicio de la categoria
     * @param tasks Conjunt de tasques que conte la categoria
     */
    public Category(String id, String name, int order, ArrayList<Task> tasks) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.tasks = tasks;
    }

    /**
     * Constructor encarregat de clonar una categoria
     * @param category Categoria
     */
    public Category(Category category) {
        id = category.id;
        name = category.name;
        order = category.order;
        tasks = new ArrayList<>();
        for(int i = 0; i < category.tasks.size(); i++) {
            tasks.add(new Task(tasks.get(i)));
        }
    }

    /**
     * Funcio per a recuperar l'identificador
     * @return Identificador de la categoria
     */
    public String getId() {
        return id;
    }

    /**
     * Funcio per a recuperar el nom
     * @return Nom de la categoria
     */
    public String getName() {
        return name;
    }

    /**
     * Procediment per a assignar el nom de la categoria
     * @param name Nom que tindra la categoria
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Funcio per recuperar l'ordre o posicio de la categoria
     * @return Ordre o posicio de la categoria
     */
    public int getOrder() {
        return order;
    }

    /**
     * Procediment per a assignar l'ordre o posicio de la categoria
     * @param order Ordre o posicio que tindra la categoria
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Funcio que recupera el número de tasques que te la categoria
     * @return Numero de tasques
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Funcio que recupera la llista de tasques de la categoria
     * @return Llista de tasques
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Procediment per a assignar la llista de tasques de la categoria
     * @param tasks Llista de tasques que tindra
     */
    public void setTasks(ArrayList<Task> tasks) {
        if(tasks != null) {
            this.tasks = new ArrayList<>();
            this.tasks.addAll(tasks);
        }
    }

    /**
     * Procediment per a recuperar en quina posicio de la llista esta una tasca
     * @param task Tasca de la qual es vol saber la posicio
     * @return Posicio de la tasca en la llista
     */
    public int getTaskIndex(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(task.getId()))
                return i;
        }
        return INVALID_INDEX;
    }

    /**
     * Funcio per a recuperar una tasca de la llista
     * @param taskIndex Posicio de la tasca en la llista
     * @return Tasca de la posicio que es passa. Si no n'hi ha cap en aquest index, retorna <code>null</code>
     */
    public Task getTask(int taskIndex) {
        if(taskIndex < tasks.size()) {
            return tasks.get(taskIndex);
        } else {
            return null;
        }
    }

    /**
     * Procediment per assignar una tasca. Si ja existeix l'actualitza i sino, l'afegeix al final
     * @param task Tasca a afegir
     */
    public void setTask(Task task) {
        Task existentTask = getTaskWithId(task.getId());
        if(existentTask != null) {
            existentTask.updateTask(existentTask);
        } else {
            tasks.add(task.getOrder(), task);
        }
    }

    /**
     * Procediment per esborrar una tasca
     * @param task Tasca a esborrar
     */
    public void deleteTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
        }
    }

    /**
     * Funcio per recuperar una tasca a partir d'un identificador
     * @param taskId Identificador de la tasca a esborrar
     * @return Si existeix una tasca amb aquest identificador la retorna. Sino, retorna <code>null</code>
     */
    public Task getTaskWithId(String taskId){
        for(Task t: tasks) {
            if(t.getId().equals(taskId)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Metode encarregat d'actualitzar la categoria a partir d'una altra
     * @param category Categoria amb dades a actualitzar
     */
    public void update(Category category) {
        name = category.name;
        order = category.order;
        tasks = category.tasks;
    }

    /**
     * Equals
     * @param o Objecte
     * @return Si equival
     */
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

    /**
     * Hashcode
     * @return Hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, order, tasks);
    }

}