package Controller;

import View.LogInPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController implements ActionListener {
    private MainViewController controller;

    public LogInController(MainViewController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton)e.getSource()).getText();

        switch (button){
            case LogInPanel.SIGN:
                controller.swapPanel(2);
                break;

            case LogInPanel.LOG:
                //JOptionPane.showMessageDialog(null, "Este es un mensaje de Advertencia", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                break;

        }

    }
}
