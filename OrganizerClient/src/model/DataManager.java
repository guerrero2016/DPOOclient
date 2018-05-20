package model;

import model.project.Category;
import model.project.Project;
import model.project.Task;
import model.user.User;

import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {
    private static DataManager sharedInstance = new DataManager();

    private String userName;
    private ArrayList<Project> projectOwnerList;
    private ArrayList<Project> projectSharedList;
    private Project selectedProject;
    private Task editingTask;
    private int whatPanel;

    private DataManager() {
        selectedProject = new Project();
        projectOwnerList = new ArrayList<>();
    }

    public Task getEditingTask() {
        return editingTask;
    }

    public void setEditingTask(Task editingTask) {
        this.editingTask = editingTask;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static DataManager getSharedInstance() {
        return sharedInstance;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setProjectID(String id) {
        selectedProject.setId(id);
    }

    public String getProjectID() {
        return selectedProject.getId();
    }

    public void setTitle(String title) {
        selectedProject.setName(title);
    }

    public String getTitle() {
        return selectedProject.getName();
    }

    public int getWhatPanel() {
        return whatPanel;
    }

    public void setWhatPanel(int whatPanel) {
        this.whatPanel = whatPanel;
    }

    public void setCategory(Category category) {
        selectedProject.setCategory(category);
    }

    public void deleteCategory(Category category) {
        selectedProject.deleteCategory(category);
    }

    public void deleteCategory(String categoryID) {
        deleteCategory(selectedProject.getCategoryWithId(categoryID));
    }

    public void setTask(Task task, String categoryID) {
        Category category = selectedProject.getCategoryWithId(categoryID);
        category.setTask(task);
    }

    public void deleteTask(Task task, String categoryID) {
        Category category = selectedProject.getCategoryWithId(categoryID);
        category.deleteTask(task);
    }

    public void setUsers(User[] users) {
        selectedProject.setUsers(new ArrayList<>(Arrays.asList(users)));
    }

    public void addUser(User user) {
        this.selectedProject.addUser(user);
    }

    public String getMembersAsString() {

        StringBuilder sb = new StringBuilder();

        for (User user : selectedProject.getUsers()){
            sb.append(user.getUserName()).append(System.lineSeparator());
        }

        return sb.toString();

    }

    public ArrayList<String> getMembersAsArray() {

        ArrayList<String> userNames = new ArrayList<>();

        for(int i = 0; i < selectedProject.getUsersSize(); i++) {
            userNames.add(selectedProject.getUser(i).getUserName());
        }

        return userNames;

    }

    public int getOwnerProjectIndex(Project p) {
        for (int i = 0; i < projectOwnerList.size(); i++) {
            if (projectOwnerList.get(i).getId().equals(p.getId())) {
                return i;
            }
        }
        return -1;
    }

    public int getSharedProjectIndex(Project p) {
        for (int i = 0; i < projectSharedList.size(); i++) {
            if (projectSharedList.get(i).getId().equals(p.getId())) {
                return i;
            }
        }
        return -1;    }

    public void setProjectOwnerList(ArrayList<Project> projectOwnerList) {
        this.projectOwnerList = projectOwnerList;
    }

    public void addProjectToOwnerList(Project p) {
        projectOwnerList.add(p);
    }

    public Project getOwnerPorjectAt(int i) {
        return projectOwnerList.get(i);
    }

    public void deleteOwnerProjectByID(String hash) {
        for (int i = 0; i < projectOwnerList.size(); i++) {
            if (hash.equals(projectOwnerList.get(i).getId())) {
                projectOwnerList.remove(i);
                return;
            }
        }
    }

    public void setProjectSharedList(ArrayList<Project> projectSharedList) {
        this.projectSharedList = projectSharedList;
    }

    public void addProjectToSharedList(Project p) {
        projectSharedList.add(p);
    }

    public Project getSharedPorjectAt(int i) {
        return projectSharedList.get(i);
    }

    public void deleteSharedProjectByID(String hash) {
        for (int i = 0; i < projectSharedList.size(); i++) {
            if (hash.equals(projectSharedList.get(i).getId())) {
                projectSharedList.remove(i);
                return;
            }
        }
    }

    public void updateCategoriesOrder(int order) {
        for(Category c: selectedProject.getCategories()) {
            if(c.getOrder() > order) {
                c.setOrder(c.getOrder() - 1);
            }
        }
    }

    public void updateTasksOrder(int order) {
        for(Category c: selectedProject.getCategories()) {
            if(c.getOrder() > order) {
                c.setOrder(c.getOrder() - 1);
            }
        }
    }
}
