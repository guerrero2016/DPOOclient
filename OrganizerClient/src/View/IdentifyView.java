package View;

import javax.swing.*;
import java.awt.*;

public class IdentifyView extends JFrame {

    private LogInPanel logInPanel;
    private SignInPanel signInPanel;

    public IdentifyView() {

        logInPanel = new LogInPanel();
        signInPanel = new SignInPanel();
        this.getContentPane().add(signInPanel, BorderLayout.CENTER);

        super.setMinimumSize(new Dimension(300,500));
        super.setSize(500,750);
        super.setTitle("Register");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);

    }
}
