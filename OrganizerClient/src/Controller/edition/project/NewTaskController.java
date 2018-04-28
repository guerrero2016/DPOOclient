package Controller.edition.project;

import View.edition.project.CategoryPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewTaskController implements DocumentListener {

    private CategoryPanel view;

    public NewTaskController(CategoryPanel view) {
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() > 0) {
            view.setTaskAdderButtonState(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() == 0) {
            view.setTaskAdderButtonState(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}