package Controller;

import View.IdentifyView;
import View.LogInPanel;
import View.SignInPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IdentifyController implements ActionListener {
    private IdentifyView view;

    public IdentifyController(IdentifyView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton)e.getSource()).getText();

        switch (button){
            case LogInPanel.LOG:
                break;

            case LogInPanel.SIGN:
                view.swapPanel();
                break;

            case SignInPanel.SIGN:
                break;

            case SignInPanel.LOG:
                view.swapPanel();
                break;
        }

    }
}
