package Controller.edition.task.user;

import Controller.edition.EditionController;
import model.user.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TaskRemoveUserController implements MouseListener {

    private final static String USER_REMOVE_TITLE = "User Remove";
    private final static String USER_REMOVE_MESSAGE = "Do you want to remove user";

    private EditionController mainController;
    private JFrame dialogJFrame;
    private User selectedUser;

    public TaskRemoveUserController(EditionController mainController) {
        this.mainController = mainController;
    }

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

    public boolean isRemovingUser(User user) {
        return dialogJFrame != null && selectedUser.equals(user);
    }

    public void closeDialog() {
        if(dialogJFrame != null) {
            dialogJFrame.dispose();
            dialogJFrame = null;
            selectedUser = null;
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