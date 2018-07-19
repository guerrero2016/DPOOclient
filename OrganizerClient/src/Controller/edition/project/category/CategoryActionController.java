package Controller.edition.project.category;

import Controller.edition.EditionController;
import model.DataManager;
import model.project.Category;
import model.project.Task;
import View.edition.project.category.CategoryPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de contolar els ActionEvent d'un CategoryPanel
 */
public class CategoryActionController implements ActionListener {

    private final static String CATEGORY_REMOVE_MESSAGE = "Do you want to delete";
    private final static String CATEGORY_REMOVE_TITLE = "Category Remove";

    private final static int TO_RIGHT = 1;
    private final static int TO_LEFT = 2;

    private EditionController mainController;
    private CategoryPanel view;
    private Category category;

    /**
     * Constructor que requereix d'un controlador extern, de la vista a controlar i de la categoria usada
     * @param mainController Controlador extern
     * @param view Vista a controlar
     * @param category Categoria corresponent a la vista
     */
    public CategoryActionController(EditionController mainController, CategoryPanel view, Category category) {
        this.mainController = mainController;
        this.view = view;
        this.category = category;
    }

    /**
     * Metode encarregat de distingir l'accio detectada
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_EDIT_NAME)) {
            categoryNameManagement();
        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_LEFT)) {
            categoryReorder(TO_LEFT);
        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_RIGHT)) {
            categoryReorder(TO_RIGHT);
        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_DELETE)) {
            categoryDelete();
        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_TASK_ADD)) {
            addTask();
        }
    }

    /**
     * Metode encarregat de manegar els canvis de nom
     */
    private void categoryNameManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setCategoryNameEditable(true, category.getName());
        } else {
            if(view.isCategoryNameEditable()) {
                String name = view.getCategoryName();
                if(name.isEmpty()) {
                    name = category.getName();
                }
                view.setCategoryNameEditable(false, name);
                Category c = new Category(category);
                c.setName(view.getCategoryName());
                mainController.updateCategory(c);
                mainController.setEditingState(false);
            } else {
                JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE,
                        EditionController.EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Metode encarregat de reordenar les categories
     * @param orderBy Ordenat dret o esquerre (TO_RIGHT, TO_LEFT)
     */
    private void categoryReorder(int orderBy) {
        if(!mainController.isEditing()) {
            int index = mainController.getCategoryIndex(category);
            switch(orderBy) {
                case TO_RIGHT:
                    if(index != DataManager.getSharedInstance().getSelectedProject().getCategories().size() - 1) {
                        mainController.swapCategories(index, index + 1);
                    }
                    break;
                case TO_LEFT:
                    if(index != 0) {
                        mainController.swapCategories(index - 1, index);
                    }
                    break;
            }
        }
    }

    /**
     * Metode encarregat d'eliminar la categoria
     */
    private void categoryDelete() {
        int result = JOptionPane.showConfirmDialog(null, CATEGORY_REMOVE_MESSAGE + " '" +
               category.getName() + "'?", CATEGORY_REMOVE_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.
                WARNING_MESSAGE);
        if(result != JOptionPane.CLOSED_OPTION && result != JOptionPane.CANCEL_OPTION) {
            if (mainController.isEditing() && view.isCategoryNameEditable()) {
                mainController.setEditingState(false);
            }
            mainController.deleteCategory(category.getId());
        }
    }

    /**
     * Metode encarregat d'afegir una tasca
     */
    private void addTask() {
        if(!mainController.isEditing() && !view.getNewTaskName().isEmpty()) {
            Task task = new Task(view.getNewTaskName());
            mainController.createTask(task, category);
        } else if(mainController.isEditing()) {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }
    }

}