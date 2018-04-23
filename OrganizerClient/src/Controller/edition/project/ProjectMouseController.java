package Controller.edition.project;

import Controller.edition.EditionController;
import View.edition.project.ProjectPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectMouseController implements MouseListener{

    private EditionController mainController;
    private ProjectPanel view;

    public ProjectMouseController(EditionController mainController, ProjectPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            //TODO: Open task to edit
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