package Controller.edition.user.project;

import Controller.edition.EditionController;
import View.edition.user.UserPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectUserController implements ActionListener {

    private EditionController mainController;
    private UserPanel view;

    public NewProjectUserController(EditionController mainController, UserPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Add user
    }

}