package Controller.edition.project.category;

import Controller.edition.EditionController;
import View.edition.project.CategoryPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryActionController implements ActionListener {

    private EditionController mainController;
    private CategoryPanel view;

    public CategoryActionController(EditionController mainController, CategoryPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_EDIT_NAME)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_LEFT)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_RIGHT)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_CATEGORY_DELETE)) {

        } else if(e.getActionCommand().equals(CategoryPanel.ACTION_TASK_ADD)) {

        }
    }

}