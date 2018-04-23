package Controller.edition.user;

import View.edition.user.UserPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UserDocumentListener implements DocumentListener {

    private UserPanel view;

    public UserDocumentListener(UserPanel view) {
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() > 0) {
            view.setUserAddButtonState(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(e.getDocument().getLength() == 0) {
            view.setUserAddButtonState(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}