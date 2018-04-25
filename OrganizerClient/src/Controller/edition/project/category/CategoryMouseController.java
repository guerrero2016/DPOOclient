package Controller.edition.project.category;

import Controller.edition.EditionController;
import ModelAEliminar.Task;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CategoryMouseController implements MouseListener{

    private EditionController mainController;
    private int categoryIndex;

    public CategoryMouseController(EditionController mainController, int categoryIndex) {
        this.mainController = mainController;
        this.categoryIndex = categoryIndex;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {

            JList<Task> tasksList = (JList) e.getSource();
            int index = tasksList.locationToIndex(e.getPoint());

            if(index == tasksList.getSelectedIndex()) {
                mainController.setTaskContent(tasksList.getSelectedValue(), categoryIndex);
                mainController.showTaskContent();
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