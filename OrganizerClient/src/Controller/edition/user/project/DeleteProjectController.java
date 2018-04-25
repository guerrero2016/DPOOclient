package Controller.edition.user.project;

import Controller.edition.EditionController;
import View.edition.user.UserPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteProjectController implements MouseListener {

    private EditionController mainController;
    private UserPanel view;

    public DeleteProjectController(EditionController mainController, UserPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            //TODO: Delete user dialog
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