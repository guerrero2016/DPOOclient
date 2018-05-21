package Controller.edition.project.user;

import model.project.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de controlar els Action Event que permeten invitar usuaris a un projecte
 */
public class ProjectAddUserController implements ActionListener {

    private final static String PROJECT_SHARED_TITLE = "Project invitation";
    private final static String PROJECT_SHARED_MESSAGE = "Project share code";

    private Project project;

    /**
     * Constructor que requereix el projecte a compartir
     * @param project Projecte
     */
    public ProjectAddUserController(Project project) {
        this.project = project;
    }

    /**
     * Mètode encarregat de generar una invitació
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea jtaMessage = new JTextArea(PROJECT_SHARED_MESSAGE + ": " + project.getId());
        jtaMessage.setEditable(false);
        jtaMessage.setOpaque(false);
        JOptionPane.showMessageDialog(null, jtaMessage, PROJECT_SHARED_TITLE,
                JOptionPane.PLAIN_MESSAGE);
    }


}