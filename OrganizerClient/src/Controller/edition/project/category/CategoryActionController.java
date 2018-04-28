package Controller.edition.project.category;

import Controller.edition.EditionController;
import ModelAEliminar.Category;
import View.edition.project.CategoryPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryActionController implements ActionListener {

    private EditionController mainController;
    private CategoryPanel view;
    private Category category;

    public CategoryActionController(EditionController mainController, CategoryPanel view, Category category) {
        this.mainController = mainController;
        this.view = view;
        this.category = category;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: ALL
        if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_EDIT_NAME)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_LEFT)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_RIGHT)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_DELETE)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_TASK_ADD)) {

        }
    }

}