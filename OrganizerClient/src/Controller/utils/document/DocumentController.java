package Controller.utils.document;

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
     * @param view Vista
     */
    public DocumentController(DocumentEnablePanel view) {
        this.view = view;
    }

    /**
     * Metode encarregat d'habilitar quelcom
     * @param e DocumentEvent
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() > 0) {
            view.setDocumentEnableState(true);
        }
    }

    /**
     * Metode encarregat d'inhabilitar quelcom
     * @param e DocumentEvent
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() == 0) {
            view.setDocumentEnableState(false);
        }
    }

    /**
     * Metode no usat
     * @param e DocumentEvent
     */
    @Override
    public void changedUpdate(DocumentEvent e) {}

}