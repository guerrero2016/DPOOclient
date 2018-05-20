package Controller.edition.task.user;

import Controller.edition.EditionController;
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
    private Task task;

    /**
     * Constructor que requereix d'un controlador extern, de la vista a controlar i de la tasca on pertenyen els usuaris
     * @param mainController Controlador extern
     * @param view Vista a controlar
     * @param task Tasca a controlar
     */
    public TaskAddUserController(EditionController mainController, UserPanel view, Task task) {
        this.mainController = mainController;
        this.view = view;
        this.task = task;
    }

    /**
     * Mètode que afegeix un usuari si es pot
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!view.getNewUser().isEmpty()) {

            User user = mainController.getProjectUser(view.getNewUser());

            if(user != null && !isUserAdded(user)) {
                view.cleanNewUser();
                mainController.addMemberInDB(user);
            } else if(user == null) {
                JOptionPane.showMessageDialog(null, USER_NOT_FOUND_MESSAGE, USER_MESSAGE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            } else if(isUserAdded(user)) {
                JOptionPane.showMessageDialog(null, USER_ALREADY_EXISTS_MESSAGE, USER_MESSAGE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    /**
     * Mètode auxliar que indica si ja està afegit l'usuari
     * @param user Usuari a comprovar
     * @return Si està afegit
     */
    private boolean isUserAdded(User user) {
        return task.getUsers().contains(user);
    }

}