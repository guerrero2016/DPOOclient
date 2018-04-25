package Controller.edition.task;

import View.edition.task.TaskPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TaskDocumentController implements DocumentListener {

    private TaskPanel view;

    public TaskDocumentController(TaskPanel view) {
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() > 0) {
            view.setTagAdderButtonEnabled(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() == 0) {
            view.setTagAdderButtonEnabled(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}