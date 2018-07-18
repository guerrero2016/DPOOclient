package Controller.project;

import Controller.MainViewController;
import model.ServerObjectType;
import model.project.Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Classe encarregada de controlar cada projecte
 */
public class ProjectBoxController implements MouseListener, ActionListener {

    private final Project project;
    private MainViewController controller;

    /**
     * Constructor a partir del projecte a controlar i del controlador principal
     * @param project Projecte a controlar
     * @param controller Controlador principal
     */
    public ProjectBoxController(Project project, MainViewController controller) {
        this.project = project;
        this.controller = controller;
    }

    /**
     * Metode encarregat de demanar al servidor la informacio del projecte clickat
     * @param e Mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            try {
                controller.sendToServer(ServerObjectType.GET_PROJECT, project.getId());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Metode encarregat de demanar al servidor eliminar el projecte
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            controller.sendToServer(ServerObjectType.DELETE_PROJECT, project.getId());
        } catch (IOException e1) {
            e1.printStackTrace();
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
