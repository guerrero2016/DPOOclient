package Controller.edition.project.user;

import Controller.edition.EditionController;
import ModelAEliminar.Project;
import ModelAEliminar.User;
import View.edition.user.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectAddUserController implements ActionListener {

    private final static String USER_MESSAGE_TITLE = "Information";
    private final static String USER_NOT_FOUND_MESSAGE = "User not found";
    private final static String USER_ALREADY_EXISTS_MESSAGE = "User already exists";

    private EditionController mainController;
    private UserPanel view;
    private Project project;

    public ProjectAddUserController(EditionController mainController, UserPanel view, Project project) {
        this.mainController = mainController;
        this.view = view;
        this.project = project;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!view.getNewUser().isEmpty()) {

            User user = mainController.getUserFromDB(view.getNewUser());

            if(user != null && !isUserAdded(user)) {
                project.addUser(user);
                view.cleanNewUser();
                view.addUser(user);
                mainController.updatedProject();
            } else if(user == null) {
                JOptionPane.showMessageDialog(null, USER_NOT_FOUND_MESSAGE, USER_MESSAGE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            } else if(isUserAdded(user)) {
                JOptionPane.showMessageDialog(null, USER_ALREADY_EXISTS_MESSAGE, USER_MESSAGE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    private boolean isUserAdded(User user) {
        return project.getUsers().contains(user);
    }

}