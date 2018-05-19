package Controller.edition.project.category;

import Controller.edition.EditionController;
import model.DataManager;
import model.project.Task;
import View.edition.project.category.CategoryPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryActionController implements ActionListener {

    private final static String CATEGORY_REMOVE_MESSAGE = "Do you want to delete";
    private final static String CATEGORY_REMOVE_TITLE = "Category Remove";

    private final static int TO_RIGHT = 1;
    private final static int TO_LEFT = 2;

    private EditionController mainController;
    private CategoryPanel view;
    private model.project.Category category;

    public CategoryActionController(EditionController mainController, CategoryPanel view, model.project.Category category) {
        this.mainController = mainController;
        this.view = view;
        this.category = category;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_EDIT_NAME)) {
            category = DataManager.getSharedInstance().getSelectedProject().getCategory(category.getOrder());
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

    private void categoryNameManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setCategoryNameEditable(true, category.getName());
        } else {
            if(view.isCategoryNameEditable()) {
                view.setCategoryNameEditable(false, view.getCategoryName());
                category.setName(view.getCategoryName());
                mainController.updateCategory(category);
                mainController.setEditingState(false);
            } else {
                JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE,
                        EditionController.EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void categoryReorder(int orderBy) {
        if(!mainController.isEditing()) {

            int index = mainController.getCategoryIndex(category);

            switch(orderBy) {
                case TO_RIGHT:
                    mainController.swapCategories(index, index + 1);
                    break;
                case TO_LEFT:
                    mainController.swapCategories(index, index - 1);
                    break;
            }

        }
    }

    private void categoryDelete() {

        int result = JOptionPane.showConfirmDialog(null, CATEGORY_REMOVE_MESSAGE + " '" +
               category.getName() + "'?", CATEGORY_REMOVE_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.
                WARNING_MESSAGE);

        if(result != JOptionPane.CLOSED_OPTION && result != JOptionPane.CANCEL_OPTION) {

            if (mainController.isEditing() && view.isCategoryNameEditable()) {
                mainController.setEditingState(false);
            }

            mainController.deleteCategory(category);

        }

    }

    private void addTask() {
        if(!mainController.isEditing() && !view.getNewTaskName().isEmpty()) {
            Task task = new Task(view.getNewTaskName());
            category.addTask(task);
            view.cleanNewTaskName();
            view.addNewTask(task);
            mainController.createTask(task, category);
        } else if(mainController.isEditing()) {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }
    }

}