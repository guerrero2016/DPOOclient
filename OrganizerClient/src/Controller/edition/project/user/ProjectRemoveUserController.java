package Controller.edition.project.user;

import Controller.edition.EditionController;
import model.user.User;

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

    /**
     * Constructor que requeriex d'un controlador extern
     * @param mainController Controlador extern
     */
    public ProjectRemoveUserController(EditionController mainController) {
        this.mainController = mainController;
    }

    /**
     * Metode encarregat de controlar quan s'eliminen usuaris
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) {

            JList userList = (JList) e.getSource();
            int index = userList.locationToIndex(e.getPoint());

            if(index == userList.getSelectedIndex()) {

                int result = JOptionPane.showConfirmDialog(null, USER_REMOVE_MESSAGE + " '" +
                        ((User) userList.getSelectedValue()).getUserName() + "'?", USER_REMOVE_TITLE, JOptionPane.
                        OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                if (result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
                    mainController.deleteUser((User) userList.getSelectedValue());
                }

            }

        }
    }

    /**
     * Metode no usat
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Metode no usat
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Metode no usat
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Metode no usat
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {}

}