package Controller.edition.document;

import View.edition.document.DocumentEnablePanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Clase encarregada de controlar el camp de text d'un vista que implementi DocumentEnablePanel
 */
public class DocumentController implements DocumentListener {

    private DocumentEnablePanel view;

    /**
     * Constructor que demana la vista que implementa DocumentEnablePanel
     * @param view
     */
    public DocumentController(DocumentEnablePanel view) {
        this.view = view;
    }

    /**
     * Mètode encarregat d'habilitar quelcom
     * @param e Document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() > 0) {
            view.setDocumentEnableState(true);
        }
    }

    /**
     * Mètode encarregat d'inhabilitar quelcom
     * @param e Document event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() == 0) {
            view.setDocumentEnableState(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}

}