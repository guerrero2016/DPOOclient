package Controller;

import View.LogInPanel;
import View.SignInPanel;
import model.ServerObjectType;
import model.user.UserLogIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Classe Listener que controla la vista d'iniciar sessió.
 * Implementa ActionListener.
 */
public class LogInController implements ActionListener {
    private MainViewController controller;
    private LogInPanel view;

    /**
     * Crea el controlador i li assigna la vista.
     * @param view Vista que controlarà.
     */
    public LogInController(LogInPanel view) {
        this.view = view;
    }

    /**
     * Vincula el controlador general amb aquest.
     * @param controller Controlador general que comunica els diferents controladors i la vista general.
     */
    public void setController(MainViewController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton)e.getSource()).getText();

        switch (button){
            case LogInPanel.SIGN:
                controller.swapPanel(SignInPanel.SIGNIN);
                break;

            case LogInPanel.LOG:
                UserLogIn logIn = view.getLogin();
                if((logIn.checkLogIn())) {
                    try {
                        controller.sendToServer(ServerObjectType.LOGIN, logIn);
                    } catch (IOException e1) {
                    }
                }
                break;

        }

    }
}
