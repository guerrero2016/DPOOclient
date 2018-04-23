package View.edition;

import javax.swing.*;
import java.awt.*;

public class EditionPanel extends BackgroundPanel {

    public EditionPanel() {
        setLayout(new BorderLayout());
    }

    public void setMainPanel(JPanel jPanel) {

        if(((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER) != null) {
            remove(((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER));
        }

        add(jPanel, BorderLayout.CENTER);

    }

    public void setLateralPanel(JPanel jPanel) {

        if(((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.LINE_END) != null) {
            remove(((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.LINE_END));
        }

        add(jPanel, BorderLayout.LINE_END);

    }

    public void setBackground(Image background) {
        setBackgroundImage(background);
    }

//    public void setProjectName(String projectName) {
//        projectPanel.setProjectName(projectName);
//    }

//    public String getNewCategoryName() {
//        return projectPanel.getNewCategoryName();
//    }

//    public void cleanNewCategoryName() {
//        projectPanel.cleanNewCategoryName();
//    }

//    public void addNewCategory(Category category) {
//        projectPanel.addCategory(category);
//    }

//    public void removeCategory(int categoryIndex) {
//        projectPanel.removeCategory(categoryIndex);
//    }

//    public void setCategoryName(int categoryIndex, String categoryName) {
//        projectPanel.setCategoryName(categoryIndex, categoryName);
//    }

//    public void swapCategories(int firstCategoryIndex, int secondCategoryIndex) {
//        projectPanel.swapCategories(firstCategoryIndex, secondCategoryIndex);
//    }

//    public String getNewTaskName(int categoryIndex) {
//        return projectPanel.getNewTaskName(categoryIndex);
//    }

//    public void cleanNewTaskName(int categoryIndex) {
//        projectPanel.cleanNewTaskName(categoryIndex);
//    }

//    public Task getSelectedTask(int categoryIndex) {
//        return projectPanel.getSelectedTask(categoryIndex);
//    }

//    public void addNewTask(Task task, int categoryIndex) {
//        projectPanel.addNewTask(task, categoryIndex);
//    }

//    public void removeTask(int categoryIndex, int taskIndex) {
//        projectPanel.removeTask(categoryIndex, taskIndex);
//    }

//    public void addProjectUser(User user) {
//        projectUserPanel.addUser(user);
//    }

//    public void removeProjectUser(int userIndex) {
//        projectUserPanel.removeUser(userIndex);
//    }

//    public String getNewProjectUser() {
//        return projectUserPanel.getNewUser();
//    }

//    public void cleanNewProjectUser() {
//        projectUserPanel.cleanNewUser();
//    }

//    public void addTaskUser(User user) {
//        if(taskUserPanel != null) {
//            taskUserPanel.addUser(user);
//        }
//    }

//    public void removeTaskUser(int userIndex) {
//        if(taskUserPanel != null) {
//            taskUserPanel.removeUser(userIndex);
//        }
//    }

//    public String getNewTaskUser() {
//
//        if (taskUserPanel != null) {
//            return taskUserPanel.getNewUser();
//        }
//
//        return null;
//
//    }

//    public void cleanNewTaskUser() {
//        if(taskUserPanel != null) {
//            taskUserPanel.cleanNewUser();
//        }
//    }

//    public void setTaskName(String taskName) {
//        if(taskPanel != null) {
//            taskPanel.setTaskName(taskName);
//        }
//    }

//    public String getDescription() {
//
//        if(taskPanel != null) {
//            return taskPanel.getDescription();
//        }
//
//        return null;
//
//    }

//    public void setDescription(String description) {
//        if(taskPanel != null) {
//            taskPanel.setDescription(description);
//        }
//    }

//    public void addNewTag(Tag tag) {
//        if(taskPanel != null) {
//            taskPanel.addTag(tag);
//        }
//    }

//    public void removeTag(int tagIndex) {
//        if(taskPanel != null) {
//            taskPanel.removeTag(tagIndex);
//        }
//    }

//    public void setTagName(int tagIndex, String tagName) {
//        if(taskPanel != null) {
//            taskPanel.setTagName(tagIndex, tagName);
//        }
//    }

//    public void setTagColor(int tagIndex, Color tagColor) {
//        if(taskPanel != null) {
//            taskPanel.setTagColor(tagIndex, tagColor);
//        }
//    }

//    public String getNewTagName() {
//
//        if(taskPanel != null) {
//            return taskPanel.getNewTagName();
//        }
//
//        return null;
//
//    }

//    public void cleanNewTagName() {
//        if(taskPanel != null) {
//            taskPanel.cleanNewTagName();
//        }
//    }

//    public void setTagAdderButtonState(boolean buttonState) {
//        if(taskPanel != null) {
//            taskPanel.setTagAdderButtonState(buttonState);
//        }
//    }

//    public void setCategoryAdderButtonState(boolean buttonState) {
//        projectPanel.setCategoryAdderButtonState(buttonState);
//    }

//    public void setTaskAdderButtonState(int categoryIndex, boolean buttonState) {
//        projectPanel.setTaskAdderButtonState(categoryIndex, buttonState);
//    }

//    public void setProjectUserAddButtonState(boolean buttonState) {
//        projectUserPanel.setUserAddButtonState(buttonState);
//    }

//    public void setTaskUserAddButtonState(boolean buttonState) {
//        taskUserPanel.setUserAddButtonState(buttonState);
//    }

//    public void setTaskDescriptionState(boolean descriptionState) {
//        if(taskPanel != null) {
//            taskPanel.setDescriptionState(descriptionState);
//        }
//    }

}