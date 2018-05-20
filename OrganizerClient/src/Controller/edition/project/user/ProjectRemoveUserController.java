package Controller.edition.project.user;

import Controller.edition.EditionController;
import model.DataManager;
import model.project.Project;
import model.user.User;
import View.edition.user.UserPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectRemoveUserController implements MouseListener {

    private final static String USER_REMOVE_TITLE = "User Remove";
    private final static String USER_REMOVE_MESSAGE = "Do you want to remove user";

    private EditionController mainController;
    private UserPanel view;
    private Project project;

    public ProjectRemoveUserController(EditionController mainController, UserPanel view, Project project) {
        this.mainController = mainController;
        this.view = view;
        this.project = project;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DataManager dataManager = DataManager.getSharedInstance();
        if (dataManager.getUserName().equals(dataManager.getSelectedProject().getOwnerName())) {
            if (e.getClickCount() == 2) {
                JList userList = (JList) e.getSource();
                int index = userList.locationToIndex(e.getPoint());

                if (index == userList.getSelectedIndex()) {
                    int result = JOptionPane.showConfirmDialog(null, USER_REMOVE_MESSAGE + " '" +
                            ((User) userList.getSelectedValue()).getUserName() + "'?", USER_REMOVE_TITLE, JOptionPane.
                            OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
                        Project p = project;
                        mainController.deleteUser(p.getUser(index));
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(view,"You are not the owner of this project");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

}