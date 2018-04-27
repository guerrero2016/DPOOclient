package Model;


import Model.project.Category;
import Model.project.Project;
import Model.project.Task;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {
    private static DataManager sharedInstance = new DataManager();

    private ArrayList<Project> projectOwnerList;
    private ArrayList<Project> projectSharedList;


    private Project selectedProject;

    private DataManager() {
        selectedProject = new Project();
        projectOwnerList = new ArrayList<>();
    }

    public static DataManager getSharedInstance() {
        return sharedInstance;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
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

    public void setTask(Task task, String categoryID) {
        ArrayList<Category> categories = selectedProject.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            if (categoryID.equals(categories.get(i).getId())) {
                categories.get(i).setTask(task);
            }
        }
    }

    public void deleteTask(Task task, String categoryID) {
        ArrayList<Category> categories = selectedProject.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            if (categoryID.equals(categories.get(i).getId())) {
                categories.get(i).deleteTask(task);
            }
        }
    }

    public void setMembers(String[] members) {
        selectedProject.setMembersName(new ArrayList<>(Arrays.asList(members)));
    }

    public void addMember(String memberName) {
        this.selectedProject.addMemberName(memberName);
    }

    public String getMembersAsString() {
        StringBuilder sb = new StringBuilder();

        for (String s : selectedProject.getMembersName()){
            sb.append(s).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public ArrayList<String> getMembersAsArray() {
        return selectedProject.getMembersName();
    }

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
}
