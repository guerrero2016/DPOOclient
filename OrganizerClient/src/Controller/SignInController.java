package Controller;

import model.user.UserRegister;
import View.LogInPanel;
import model.ServerObjectType;
import View.SignInPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignInController implements ActionListener {

    private MainViewController controller;
    private SignInPanel view;

    public SignInController(SignInPanel view) {
        this.view = view;
    }

    public void setController(MainViewController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton)e.getSource()).getText();

        switch (button){
            case SignInPanel.LOG:
                controller.swapPanel(LogInPanel.LOGIN);
                break;

            case SignInPanel.SIGN:
                UserRegister register = view.getRegister();

                if((register.checkSignIn() == 0)) {
                    try {
                        System.out.println("se envia");
                        controller.sendToServer(ServerObjectType.REGISTER, register);
                    } catch (IOException e1) {
                        controller.showDialog("Erro de connexió amb el servidor");
                    }
                } else {
                    controller.showDialog("Error, dades incorectes");
                }
                break;
        }

    }
}
