package Controller.edition.task.user;

import Controller.edition.EditionController;
import ModelAEliminar.Task;
import ModelAEliminar.User;
import View.edition.user.UserPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskAddUserController implements ActionListener {

    private EditionController mainController;

    private UserPanel view;

    private Task task;

    public TaskAddUserController(EditionController mainController, UserPanel view, Task task) {
        this.mainController = mainController;
        this.view = view;
        this.task = task;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!view.getNewUser().isEmpty()) {

            User user = mainController.getProjectUser(view.getNewUser());

            if(user != null) {
                task.addUser(user);
                view.cleanNewUser();
                view.addUser(user);
                mainController.updatedTask(task);
            }

        }
    }

}