package Controller.edition.task.user;

import Controller.edition.EditionController;
import model.DataManager;
import model.project.Task;
import model.user.User;
import View.edition.user.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de gestionar els Action Event d'un UserPanel d'una tasca
 */
public class TaskAddUserController implements ActionListener {

    private final static String USER_MESSAGE_TITLE = "Information";
    private final static String USER_NOT_FOUND_MESSAGE = "User not found";
    private final static String USER_ALREADY_EXISTS_MESSAGE = "User already exists";

    private EditionController mainController;
    private UserPanel view;

    /**
     * Constructor que requereix d'un controlador extern, de la vista a controlar i de la tasca on pertenyen els usuaris
     * @param mainController Controlador extern
     * @param view Vista a controlar
     */
    public TaskAddUserController(EditionController mainController, UserPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    /**
     * Metode que afegeix un usuari si es pot
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!view.getNewUser().isEmpty()) {

            User user = mainController.getProjectUser(view.getNewUser());

            if(user != null && !mainController.isUserAdded(user)) {
                view.cleanNewUser();
                mainController.addMemberInDB(user);
            } else if(user == null) {
                JOptionPane.showMessageDialog(null, USER_NOT_FOUND_MESSAGE, USER_MESSAGE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, USER_ALREADY_EXISTS_MESSAGE, USER_MESSAGE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }

        }
    }

}