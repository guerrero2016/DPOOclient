package Controller.edition.task.user;

import Controller.edition.EditionController;
import model.user.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe encarregada dels MouseEvent d'un UserPanel d'una tasca
 */
public class TaskRemoveUserController implements MouseListener {

    private final static String USER_REMOVE_TITLE = "User Remove";
    private final static String USER_REMOVE_MESSAGE = "Do you want to remove user";

    private EditionController mainController;
    private JFrame dialogJFrame;
    private User selectedUser;

    /**
     * Constructor que requereix d'un controlador extern
     * @param mainController Controlador extern
     */
    public TaskRemoveUserController(EditionController mainController) {
        this.mainController = mainController;
    }

    /**
     * Metode encarregat d'eliminar un usuari si es pot
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {

            JList<User> userList = (JList) e.getSource();
            int index = userList.locationToIndex(e.getPoint());
            selectedUser = userList.getSelectedValue();

            if(index == userList.getSelectedIndex()) {

                dialogJFrame = new JFrame();
                dialogJFrame.setLocationRelativeTo(null);
                int result = JOptionPane.showConfirmDialog(null, USER_REMOVE_MESSAGE + " '" +
                                userList.getSelectedValue().getUserName() + "'?", USER_REMOVE_TITLE, JOptionPane.
                                OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                dialogJFrame = null;

                if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
                    mainController.removeMemberInDB(selectedUser);
                }

            }

            selectedUser = null;

        }
    }

    /**
     * Metode que indica si s'esta eliminat un usuari
     * @param user Usuari a comporvar
     * @return Si s'esta eliminant
     */
    public boolean isRemovingUser(User user) {
        return dialogJFrame != null && selectedUser.equals(user);
    }

    /**
     * Metode encarregat de tancar el dialog obert pel controlador
     */
    public void closeDialog() {
        if(dialogJFrame != null) {
            dialogJFrame.dispose();
            dialogJFrame = null;
            selectedUser = null;
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