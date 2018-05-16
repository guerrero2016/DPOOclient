package Controller.edition.project.category;

import Controller.edition.EditionController;
import Model.project.Task;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CategoryMouseController implements MouseListener{

    private EditionController mainController;
    private Model.project.Category category;

    public CategoryMouseController(EditionController mainController, Model.project.Category category) {
        this.mainController = mainController;
        this.category = category;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!mainController.isEditing() && e.getClickCount() == 2) {

            JList<Task> tasksList = (JList) e.getSource();
            int index = tasksList.locationToIndex(e.getPoint());

            if(index == tasksList.getSelectedIndex()) {
                mainController.setTaskContent(category, tasksList.getSelectedValue());
                mainController.showTaskContent();
            }

        } else if(mainController.isEditing()) {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
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