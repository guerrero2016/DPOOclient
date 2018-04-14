package View;

import javax.swing.*;
import java.awt.*;

public class CustomDialog extends JDialog{

    private final JPanel view;

    public CustomDialog (String title, JPanel view) {

        this.view = view;

        this.getContentPane().add(view);
        this.setTitle(title);
        this.setSize(new Dimension(300, 300));
        this.setModal(true);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(300, 300));
    }
}
