package model;

import model.project.Category;
import model.project.Project;
import model.project.Task;
import model.user.User;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe que implementa Singleton que s'encarrega de guardar les dades més importants del programa.
 */
public class DataManager {
    private static DataManager sharedInstance = new DataManager();

    private String userName;
    private ArrayList<Project> projectOwnerList;
    private ArrayList<Project> projectSharedList;
    private Project selectedProject;
    private Task editingTask;
    private int whatPanel;

    /**
     * Crea el DataManager amb els camps per defecte
     */
    private DataManager() {
        selectedProject = new Project();
        projectOwnerList = new ArrayList<>();
        projectSharedList = new ArrayList<>();
    }

    /**
     * Funció que recupera si la tasca que s'està editant.
     *
     * @return tasca sent editada
     */
    public Task getEditingTask() {
        return editingTask;
    }

    /**
     * Procediment que assigna quina tasca s'està editant.
     *
     * @param editingTask tasca que s'editarà
     */
    public void setEditingTask(Task editingTask) {
        this.editingTask = editingTask;
    }

    /**
     * Funció que recupera el nom d'usuari del client.
     *
     * @return nom d'usuari
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Procediment que assigna un nom d'usuari
     *
     * @param userName nom d'usuari del client
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Funció que recupera el DataManager.
     *
     * @return DataManager
     */
    public static DataManager getSharedInstance() {
        return sharedInstance;
    }

    /**
     * Procediment que assigna el projecte seleccionat
     *
     * @param selectedProject projecte seleccionat
     */
    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    /**
     * Funció que recuprta el projecte obert en el client.
     *
     * @return projecte obert
     */
    public Project getSelectedProject() {
        return selectedProject;
    }

    /**
     * Procediment encarregat d'assignar un identificador al projecte seleccionat
     *
     * @param id identificador
     */
    public void setProjectID(String id) {
        selectedProject.setId(id);
    }

    /**
     * Funció que recupera l'identificador del projecte seleccionat
     *
     * @return identificador del projecte seleccionat
     */
    public String getProjectID() {
        return selectedProject.getId();
    }

    /**
     * Getter del WhatPanel. El WhatPanel indica en quin JPanel es troba el client.
     *
     * @return Panell en el que es troba actualment el client.
     */
    public int getWhatPanel() {
        return whatPanel;
    }

    /**
     * Setter del whatPanel.
     *
     * @param whatPanel Valor int que representa quin panell
     */
    public void setWhatPanel(int whatPanel) {
        this.whatPanel = whatPanel;
    }

    /**
     * Funció encarregada de sobreescriure una categoria en cas que aquesta ja existeixi o afegir-la en cas contrari.
     *
     * @param category Categoria que volem afegir.
     */
    public void setCategory(Category category) {
        selectedProject.setCategory(category);
    }

    /**
     * Funció que s'encarrega d'eliminar una categoria.
     *
     * @param category Categoria a eliminar.
     */
    public void deleteCategory(Category category) {
        selectedProject.deleteCategory(category);
    }

    /**
     * Funció que s'encarrega d'eliminar una categoria, aquest cop a partir del seu ID.
     *
     * @param categoryID ID de la categoria que volem eliminar.
     */
    public void deleteCategory(String categoryID) {
        deleteCategory(selectedProject.getCategoryWithId(categoryID));
    }

    /**
     * S'encarrega d'eliminar una tasca.
     *
     * @param task       Tasca que volem eliminar.
     * @param categoryID ID de la categoria en la que es troba la tasca.
     */
    public void deleteTask(Task task, String categoryID) {
        Category category = selectedProject.getCategoryWithId(categoryID);
        category.deleteTask(task);
    }

    /**
     * Funció que s'encarrega d'afegir un encarregat a una tasca.
     *
     * @param user Encarregat que volem afegir a una tasca.
     */
    public void addUser(User user) {
        this.selectedProject.addUser(user);
    }

    /**
     * Getter de l'index d'un dels projectes dels quals l'usuari del client es propietari.
     *
     * @param p Projecte del qual volem rebre l'index.
     * @return Index del projecte.
     */
    public int getOwnerProjectIndex(Project p) {
        for (int i = 0; i < projectOwnerList.size(); i++) {
            if (projectOwnerList.get(i).getId().equals(p.getId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Getter de l'index d'un dels projectes dels quals l'usuari del client és membre.
     *
     * @param p Projecte del qual volem l'index.
     * @return Index del projecte en qüestió.
     */
    public int getSharedProjectIndex(Project p) {
        for (int i = 0; i < projectSharedList.size(); i++) {
            if (projectSharedList.get(i).getId().equals(p.getId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Procediment encarregat d'assignar la llista de projectes propis.
     * @param projectOwnerList projectes propis a assignar
     */
    public void setProjectOwnerList(ArrayList<Project> projectOwnerList) {
        this.projectOwnerList = projectOwnerList;
    }

    /**
     * Procediment encarregat d'afegir un projecte a la llista de porjectes propis.
     * @param p projecte a afegir
     */
    public void addProjectToOwnerList(Project p) {
        projectOwnerList.add(p);
    }

    /**
     * Procediment encarregat d'eliminar un projecte de la llista de propis.
     * @param hash identificador del projecte a eliminar
     */
    public void deleteOwnerProjectByID(String hash) {
        for (int i = 0; i < projectOwnerList.size(); i++) {
            if (hash.equals(projectOwnerList.get(i).getId())) {
                projectOwnerList.remove(i);
                return;
            }
        }
    }

    /**
     * Procediment encarregat d'assignar la llista de projectes compartits
     * @param projectSharedList llista de projectes compartits
     */
    public void setProjectSharedList(ArrayList<Project> projectSharedList) {
        this.projectSharedList = projectSharedList;
    }

    /**
     * Procediment encarregat d'afegir un projecte a la llista de projectes compartits
     * @param p projecte a afegir
     */
    public void addProjectToSharedList(Project p) {
        projectSharedList.add(p);
    }

    /**
     * Procediment encarregat d'eliminar un projecte de la llista de compartits
     * @param hash codi del projecte
     */
    public void deleteSharedProjectByID(String hash) {
        for (int i = 0; i < projectSharedList.size(); i++) {
            if (hash.equals(projectSharedList.get(i).getId())) {
                projectSharedList.remove(i);
                return;
            }
        }
    }

    /**
     * Procediment encarregat d'actualitzar l'ordre de les categories.
     * @param order posició de la categoria eliminada
     */
    public void updateCategoriesOrder(int order) {
        for(Category c: selectedProject.getCategories()) {
            if(c.getOrder() > order) {
                c.setOrder(c.getOrder() - 1);
            }
        }
    }

    /**
     * Procediment encarregat d'actualitzar l'ordre de les tasques.
     * @param order posició de la tasca eliminada
     */
    public void updateTasksOrder(int order) {
        for(Category c: selectedProject.getCategories()) {
            if(c.getOrder() > order) {
                c.setOrder(c.getOrder() - 1);
            }
        }
    }
}
