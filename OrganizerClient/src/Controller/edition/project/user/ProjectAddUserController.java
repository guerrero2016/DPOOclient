package Controller.edition.project.user;

import Controller.edition.EditionController;
import model.project.Project;
import model.user.User;
import View.edition.user.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectAddUserController implements ActionListener {

    private final static String USER_ADD_MESSAGE = "Do you want to share this project with user";
    private final static String USER_ADD_TITLE = "Add User";
    private final static String USER_MESSAGE_TITLE = "Information";
    private final static String USER_NOT_FOUND_MESSAGE = "User not found";
    private final static String USER_ALREADY_EXISTS_MESSAGE = "User already exists";
    private final static String PROJECT_SHARED_TITLE = "Project invitation";
    private final static String PROJECT_SHARED_MESSAGE = "Project share code";

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
        JTextArea jtaMessage = new JTextArea(PROJECT_SHARED_MESSAGE + ": " + project.getId());
        jtaMessage.setEditable(false);
        jtaMessage.setOpaque(false);
        JOptionPane.showMessageDialog(null, jtaMessage, PROJECT_SHARED_TITLE,
                JOptionPane.PLAIN_MESSAGE);
    }


}