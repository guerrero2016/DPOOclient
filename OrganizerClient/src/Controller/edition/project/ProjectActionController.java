package Controller.edition.project;

import Controller.edition.EditionController;
import model.DataManager;
import model.project.Category;
import model.project.Project;
import View.edition.project.ProjectPanel;
import View.edition.project.category.CategoryPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe encarregada de controlar els ActionEvent d'un ProjectPanel
 */
public class ProjectActionController implements ActionListener {

    private final static String WRONG_FORMAT_MESSAGE = "The chosen file is not a picture";
    private final static String FILE_ERROR_MESSAGE = "The chosen file could not be read";
    private final static String FILE_ERROR_TITLE = "File Error";
    private final static String PROJECT_REMOVE_MESSAGE = "Do you want to remove";
    private final static String PROJECT_REMOVE_TITLE = "Project Remove";

    private EditionController mainController;
    private ProjectPanel view;
    private Project project;

    /**
     * Constructor que requereix d'un controlador extern, la vista a controlar i el projecte
     * @param mainController Controlador extern
     * @param view Vista a controlar
     * @param project Projecte
     */
    public ProjectActionController(EditionController mainController, ProjectPanel view, Project project) {
        this.mainController = mainController;
        this.view = view;
        this.project = project;
    }

    /**
     * Metode encarregat d'identificar l'accio a realitzar
     * @param e Action Event
     */
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
            mainController.removeCommunicators();
            projectBackManagement();
        }
    }

    /**
     * Metode encarregat de manegar els canvis de nom
     */
    private void projectNameManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setProjectNameEditable(true, project.getName());
        } else {
            if(view.isProjectNameEditable()) {
                view.setProjectNameEditable(false, view.getProjectName());
                Project p = project;
                p.setName(view.getProjectName());
                mainController.updateProject(p);
                mainController.setEditingState(false);
            } else {
                JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE,
                        EditionController.EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Metode encarregat de manegar els canvis de fons de pantalla
     */
    private void backgroundManagement() {
        if(!mainController.isEditing()) {
            JFileChooser jfc = new JFileChooser();
            int result = jfc.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION &&
                    DataManager.getSharedInstance().getSelectedProject() != null &&
                    DataManager.getSharedInstance().getSelectedProject().equals(project)) {
                File file = jfc.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(file);
                    if(image != null) {
                        project.setBackground(image);
                        mainController.updateProject(project);
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

    /**
     * Metode encarregat de manegar l'eliminacio del projecte
     */
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
        }
    }

    /**
     * Metode encarregat d'afegir categories
     */
    private void addCategory() {
        if(!mainController.isEditing() && !view.getNewCategoryName().isEmpty()) {
            Category category = new Category(view.getNewCategoryName());
            mainController.updateCategory(category);
        } else if(mainController.isEditing()) {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Metode encarregat de tornar a la pantalla de seleccio de projectes
     */
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