package Controller.edition.project.category;

import View.edition.project.ProjectPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewCategoryController implements DocumentListener {

    private ProjectPanel view;

    public NewCategoryController(ProjectPanel view) {
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() > 0) {
            view.setCategoryAdderButtonEnabled(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() == 0) {
            view.setCategoryAdderButtonEnabled(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}