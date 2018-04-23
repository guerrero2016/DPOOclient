package Controller.edition.project;

import View.edition.project.ProjectPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewCategoryDocumentController implements DocumentListener {

    private ProjectPanel view;

    public NewCategoryDocumentController(ProjectPanel view) {
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() > 0) {
            view.setCategoryAdderButtonState(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() == 0) {
            view.setCategoryAdderButtonState(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}