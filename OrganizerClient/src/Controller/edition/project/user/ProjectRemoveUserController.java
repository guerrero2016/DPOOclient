package Controller.edition.project.user;

import Controller.edition.EditionController;
import model.DataManager;
import model.project.Project;
import model.user.User;
import View.edition.user.UserPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe encarregada d'eliminar un usuari d'un projecte
 */
public class ProjectRemoveUserController implements MouseListener {

    private final static String USER_REMOVE_TITLE = "User Remove";
    private final static String USER_REMOVE_MESSAGE = "Do you want to remove user";

    private EditionController mainController;
    private UserPanel view;
    private Project project;

    /**
     * Constructor que requeriex d'un controlador extern, el panell a controlar i el projecte on s'eliminen els usuaris
     * @param mainController Controlador extern
     * @param view Vista a controlar
     * @param project Projecte on s'eliminen usuaris
     */
    public ProjectRemoveUserController(EditionController mainController, UserPanel view, Project project) {
        this.mainController = mainController;
        this.view = view;
        this.project = project;
    }

    /**
     * Mètode encarregat de controlar quan s'eliminen usuaris
     * @param e Mouse Event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        DataManager dataManager = DataManager.getSharedInstance();
        if (e.getClickCount() == 2) {
            if (dataManager.getUserName().equals(dataManager.getSelectedProject().getOwnerName())) {

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
            } else {
                JOptionPane.showMessageDialog(view,"No ets el propietari del projecte");
            }
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