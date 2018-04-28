package Controller.edition.task.user;

import Controller.edition.EditionController;
import ModelAEliminar.Task;
import ModelAEliminar.User;
import View.edition.user.UserPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TaskRemoveUserController implements MouseListener {

    private final static String USER_REMOVE_MESSAGE = "User remove";
    private final static String WARNING_MESSAGE = "Do you want to remove user";

    private EditionController mainController;
    private UserPanel view;
    private Task task;

    public TaskRemoveUserController(EditionController mainController, UserPanel view, Task task) {
        this.mainController = mainController;
        this.view = view;
        this.task = task;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {

            JList<User> userList = (JList) e.getSource();
            int index = userList.locationToIndex(e.getPoint());

            if(index == userList.getSelectedIndex()) {

                int result = JOptionPane.showConfirmDialog(null,WARNING_MESSAGE + " '" +
                                userList.getSelectedValue().getName() + "'?", USER_REMOVE_MESSAGE, JOptionPane.
                                OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
                    task.removeUser(index);
                    view.removeUser(index);
                    mainController.updatedTask(task);
                }

            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}