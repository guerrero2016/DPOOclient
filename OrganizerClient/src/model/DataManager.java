package model;

import model.project.Category;
import model.project.Project;
import model.project.Task;
import model.user.User;

import java.util.ArrayList;

/**
 * Classe que implementa Singleton que s'encarrega de guardar les dades mes importants del programa
 */
public class DataManager {

    private static DataManager sharedInstance = new DataManager();

    private String userName;
    private ArrayList<Project> projectOwnerList;
    private ArrayList<Project> projectSharedList;
    private Project selectedProject;
    private int whatPanel;
    private Task editingTask;

    /**
     * Crea el DataManager amb els camps per defecte
     */
    private DataManager() {
        selectedProject = new Project();
        projectOwnerList = new ArrayList<>();
        projectSharedList = new ArrayList<>();
    }

    /**
     * Funcio que recupera el nom d'usuari del client
     * @return Nom d'usuari
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Procediment que assigna un nom d'usuari
     * @param userName Nom d'usuari del client
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Funcio que recupera el DataManager
     * @return DataManager
     */
    public static DataManager getSharedInstance() {
        return sharedInstance;
    }

    /**
     * Procediment que assigna el projecte seleccionat
     * @param selectedProject Projecte seleccionat
     */
    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    /**
     * Funcio que recuprta el projecte obert en el client
     * @return Projecte obert
     */
    public Project getSelectedProject() {
        return selectedProject;
    }

    /**
     * Getter del WhatPanel. El WhatPanel indica en quin JPanel es troba el client
     * @return Panell en el que es troba actualment el client
     */
    public int getWhatPanel() {
        return whatPanel;
    }

    /**
     * Setter del whatPanel
     * @param whatPanel Valor enter que representa un panell
     */
    public void setWhatPanel(int whatPanel) {
        this.whatPanel = whatPanel;
    }

    /**
     * Funcio encarregada de sobreescriure una categoria en cas que aquesta ja existeixi o afegir-la en cas contrari
     * @param category Categoria que volem afegir
     */
    public void setCategory(Category category) {
        selectedProject.addCategory(category);
    }

    /**
     * Funcio que s'encarrega d'eliminar una categoria
     * @param category Categoria a eliminar
     */
    private void deleteCategory(Category category) {
        selectedProject.deleteCategory(category);
    }

    /**
     * Funcio que s'encarrega d'eliminar una categoria, aquest cop a partir del seu ID
     * @param categoryID ID de la categoria que volem eliminar
     */
    public void deleteCategory(String categoryID) {
        deleteCategory(selectedProject.getCategoryWithId(categoryID));
    }

    /**
     * Funcio que s'encarrega d'afegir un encarregat a una tasca
     * @param user Encarregat que volem afegir a una tasca
     */
    public void addUser(User user) {
        this.selectedProject.addUser(user);
    }

    /**
     * Getter de l'index d'un dels projectes dels quals l'usuari del client es propietari
     * @param p Projecte del qual volem rebre l'index
     * @return Index del projecte
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
     * Getter de l'index d'un dels projectes dels quals l'usuari del client es membre
     * @param p Projecte del qual volem l'index
     * @return Index del projecte en questio
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
     * Procediment encarregat d'assignar la llista de projectes propis
     * @param projectOwnerList Projectes propis a assignar
     */
    public void setProjectOwnerList(ArrayList<Project> projectOwnerList) {
        this.projectOwnerList = projectOwnerList;
    }

    /**
     * Procediment encarregat d'afegir un projecte a la llista de porjectes propis
     * @param p Projecte a afegir
     */
    public void addProjectToOwnerList(Project p) {
        projectOwnerList.add(p);
    }

    /**
     * Procediment encarregat d'eliminar un projecte de la llista de propis
     * @param hash Identificador del projecte a eliminar
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
     * @param projectSharedList Llista de projectes compartits
     */
    public void setProjectSharedList(ArrayList<Project> projectSharedList) {
        this.projectSharedList = projectSharedList;
    }

    /**
     * Procediment encarregat d'afegir un projecte a la llista de projectes compartits
     * @param p Projecte a afegir
     */
    public void addProjectToSharedList(Project p) {
        projectSharedList.add(p);
    }

    /**
     * Procediment encarregat d'eliminar un projecte de la llista de compartits
     * @param hash Codi del projecte
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
     * Procediment encarregat d'actualitzar l'ordre de les categories
     * @param order Posicio de la categoria eliminada
     */
    public void updateCategoriesOrder(int order) {
        for(Category c: selectedProject.getCategories()) {
            if(c.getOrder() > order) {
                c.setOrder(c.getOrder() - 1);
            }
        }
    }

    /**
     * Procediment encarregat d'actualitzar l'ordre de les tasques
     * @param order Posicio de la tasca eliminada
     */
    public void updateTasksOrder(String categoryId, int order) {
        for(Task t: selectedProject.getCategoryWithId(categoryId).getTasks()) {
            if(t.getOrder() > order) {
                t.setOrder(t.getOrder() - 1);
            }
        }
    }

    /**
     * Funcio que recupera la tasca que s'esta editant
     * @return Tasca
     */
    public Task getEditingTask() {
        return editingTask;
    }

    /**
     * Procediment que assigna quina tasca s'esta editant
     * @param editingTask Tasca
     */
    public void setEditingTask(Task editingTask) {
        this.editingTask = editingTask;
    }

}
