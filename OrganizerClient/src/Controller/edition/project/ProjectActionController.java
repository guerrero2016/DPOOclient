package Controller.edition.project;

import Controller.edition.EditionController;
import Controller.edition.document.DocumentController;
import Controller.edition.project.category.CategoryActionController;
import Controller.edition.project.category.CategoryMouseController;
import model.project.Category;
import model.project.Project;
import View.edition.project.ProjectPanel;
import View.edition.project.category.CategoryPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ProjectActionController implements ActionListener {

    private final static String WRONG_FORMAT_MESSAGE = "The chosen file is not a picture";
    private final static String FILE_ERROR_MESSAGE = "The chosen file could not be read";
    private final static String FILE_ERROR_TITLE = "File Error";
    private final static String PROJECT_REMOVE_MESSAGE = "Do you want to remove";
    private final static String PROJECT_REMOVE_TITLE = "Project Remove";

    private EditionController mainController;
    private ProjectPanel view;
    private Project project;

    public ProjectActionController(EditionController mainController, ProjectPanel view, Project project) {
        this.mainController = mainController;
        this.view = view;
        this.project = project;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_EDIT_NAME)) {
            projectNameManagement();
        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_BACKGROUND)) {
            backgroundManagement();
        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_DELETE)) {
            deleteProject();
        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_CATEGORY_ADD)) {
            addCategory();
        } else if(e.getActionCommand().equals(ProjectPanel.ACTION_PROJECT_BACK)) {
            projectBackManagement();
        }
    }

    private void projectNameManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setProjectNameEditable(true, project.getName());
        } else {
            if(view.isProjectNameEditable()) {
                view.setProjectNameEditable(false, view.getProjectName());
                project.setName(view.getProjectName());
                mainController.updatedProject();
                mainController.setEditingState(false);
            } else {
                JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE,
                        EditionController.EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void backgroundManagement() {
        if(!mainController.isEditing()) {

            JFileChooser jfc = new JFileChooser();
            int result = jfc.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION) {

                File file = jfc.getSelectedFile();

                try {

                    Image image = ImageIO.read(file);

                    if(image != null) {
                        project.setBackground(image);
                        mainController.setBackgroundImage(image);
                        mainController.updatedProject();
                    } else {
                        JOptionPane.showMessageDialog(null, WRONG_FORMAT_MESSAGE, FILE_ERROR_TITLE,
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch(IOException e) {
                    JOptionPane.showMessageDialog(null, FILE_ERROR_MESSAGE, FILE_ERROR_TITLE,
                            JOptionPane.WARNING_MESSAGE);
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteProject() {

        int result = JOptionPane.showConfirmDialog(null, PROJECT_REMOVE_MESSAGE + " '" +
                project.getName() + "'?", PROJECT_REMOVE_TITLE, JOptionPane.
                OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        if(result != JOptionPane.CLOSED_OPTION && result != JOptionPane.CANCEL_OPTION) {

            for (int i = 0; i < project.getCategoriesSize(); i++) {
                CategoryPanel categoryPanel = view.getCategoryPanel(i);
                categoryPanel.setCategoryNameEditable(false, project.getCategory(i).getName());
                categoryPanel.cleanNewTaskName();
            }

            view.setProjectNameEditable(false, project.getName());
            view.cleanNewCategoryName();
            mainController.deleteProject();
            mainController.showProjectSelection();

        }

    }

    private void addCategory() {
        if(!mainController.isEditing() && !view.getNewCategoryName().isEmpty()) {
            Category category = new Category(view.getNewCategoryName());
            project.setCategory(category);
            view.cleanNewCategoryName();
            view.addCategory(category);
            CategoryPanel categoryPanel = view.getCategoryPanel(project.getCategoriesSize() - 1);
            categoryPanel.registerActionController(new CategoryActionController(mainController, categoryPanel, category));
            categoryPanel.registerMouseController(new CategoryMouseController(mainController, category));
            categoryPanel.registerDocumentController(new DocumentController(categoryPanel));
            mainController.updatedProject();
        } else if(mainController.isEditing()) {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }
    }

    private void projectBackManagement() {

        for(int i = 0; i < project.getCategoriesSize(); i++) {
            CategoryPanel categoryPanel = view.getCategoryPanel(i);
            categoryPanel.setCategoryNameEditable(false, project.getCategory(i).getName());
            categoryPanel.cleanNewTaskName();
        }

        view.setProjectNameEditable(false, project.getName());
        view.cleanNewCategoryName();
        mainController.showProjectSelection();

    }

}