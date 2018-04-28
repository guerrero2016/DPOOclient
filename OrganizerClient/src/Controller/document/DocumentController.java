package Controller.document;

import View.document.DocumentEnablePanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentController implements DocumentListener {

    private DocumentEnablePanel view;

    public DocumentController(DocumentEnablePanel view) {
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() > 0) {
            view.setDocumentEnableState(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() == 0) {
            view.setDocumentEnableState(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}