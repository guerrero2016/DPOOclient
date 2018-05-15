package Controller;

import Model.user.UserRegister;
import View.SignInPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInController implements ActionListener {

    private MainViewController controller;

    public SignInController(MainViewController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton)e.getSource()).getText();

        switch (button){
            case SignInPanel.LOG:
                controller.swapPanel(1);
                break;

            case SignInPanel.SIGN:
                //TODO Passar-li els paràmetres al constructor
                UserRegister register = new UserRegister();
                int signIn = 0;
                try {
                    signIn = register.checkSignIn();
                } catch(Exception ex) {
                    //Could not signIn
                }
                if(signIn == 0) {
                    //Enviar la petició d'inici de sessió al server.
                    //controller.swapPanel(3);
                }
                break;
        }

    }
}
