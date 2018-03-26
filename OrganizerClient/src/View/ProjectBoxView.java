package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ProjectBoxView extends JPanel {

    public ProjectBoxView (String title, Color color) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        setBackground(color);
        add(titleLabel,BorderLayout.CENTER);
    }

    public void registerMouseListener (MouseListener controller) {
        addMouseListener(controller);
    }

}
