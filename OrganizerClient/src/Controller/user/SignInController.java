package Controller.user;

import Controller.MainViewController;
import model.user.UserRegister;
import View.user.LogInPanel;
import model.ServerObjectType;
import View.user.SignInPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Classe Listener que controla la vista de registre.
 * Implementa ActionListener
 */
public class SignInController implements ActionListener {

    private MainViewController controller;
    private SignInPanel view;

    /**
     * Crea el controlador i li assigna una vista
     * @param view Vista que controlara
     */
    public SignInController(SignInPanel view) {
        this.view = view;
    }

    /**
     * Vincula el controlador general amb aquest
     * @param controller Controlador general que comunica els diferents controladors i la vista general
     */
    public void setController(MainViewController controller) {
        this.controller = controller;
    }

    /**
     * Metode encarregat de gestionar els ActionEvent
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton)e.getSource()).getText();

        switch (button){
            case SignInPanel.LOG:
                controller.swapPanel(LogInPanel.LOGIN);
                break;

            case SignInPanel.SIGN:
                UserRegister register = view.getRegister();

                try {
                    controller.sendToServer(ServerObjectType.REGISTER, register);
                } catch (IOException e1) {
                    controller.showDialog("Server connection error");
                }

                break;
        }

    }

}