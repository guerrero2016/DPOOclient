package Controller.edition.user;

import Controller.edition.EditionController;
import View.edition.user.UserPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserActionController implements ActionListener {

    private EditionController mainController;
    private UserPanel view;

    public UserActionController(EditionController mainController, UserPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Add user
    }

}