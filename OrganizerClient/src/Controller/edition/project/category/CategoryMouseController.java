package Controller.edition.project.category;

import Controller.edition.EditionController;
import model.project.Category;
import model.project.Task;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe encarregada de controlar l'acces a les tasques d'una categoria
 */
public class CategoryMouseController implements MouseListener{

    private EditionController mainController;
    private String categoryId;

    /**
     * Constructor a partir del controlador d'edicio principal i de la categoria a controlar
     * @param mainController Controlador principal
     * @param categoryId Id de la categoria
     */
    public CategoryMouseController(EditionController mainController, String categoryId) {
        this.mainController = mainController;
        this.categoryId = categoryId;
    }

    /**
     * Metode encarregat de controlar els clicks del ratoli
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!mainController.isEditing() && e.getClickCount() == 2) {

            JList<Task> tasksList = (JList) e.getSource();
            int index = tasksList.locationToIndex(e.getPoint());

            if(index == tasksList.getSelectedIndex()) {
                mainController.setTaskContent(categoryId, tasksList.getSelectedValue().getId());
                mainController.showTaskContent();
            }

        } else if(mainController.isEditing()) {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
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