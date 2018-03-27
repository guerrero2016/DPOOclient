package View;

import Controller.IdentifyController;

import javax.swing.*;
import java.awt.*;

public class IdentifyView extends JFrame {
    private LogInPanel logInPanel;
    private SignInPanel signInPanel;
    private boolean signNOTlog;

    public IdentifyView() {
        signNOTlog = true;
        logInPanel = new LogInPanel();
        signInPanel = new SignInPanel();
        this.getContentPane().add(signInPanel, BorderLayout.CENTER);

        super.setMinimumSize(new Dimension(300,500));
        super.setSize(500,750);
        super.setTitle("Register");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);

    }

    public void swapPanel() {
        signNOTlog = !signNOTlog;
        this.getContentPane().removeAll();
        if (signNOTlog) {
            this.getContentPane().add(signInPanel, BorderLayout.CENTER);
        }else {
            this.getContentPane().add(logInPanel, BorderLayout.CENTER);
        }
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void addControllerButton (IdentifyController ic) {
        logInPanel.addControllerButton(ic);
        signInPanel.addControllerButton(ic);
    }
}
