package Model;


import Model.project.Project;

import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {
    private static DataManager sharedInstance = new DataManager();

    private Project selectedProject;

    private DataManager() {
        selectedProject = new Project();
    }

    public static DataManager getSharedInstance() {
        return sharedInstance;
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

    public void setMembers(String[] members) {
        selectedProject.setMembersName(new ArrayList<>(Arrays.asList(members)));
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

}
