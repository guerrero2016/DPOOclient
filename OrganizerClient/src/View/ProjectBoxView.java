package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ProjectBoxView extends JPanel {

    final int HEIGHT = 50;
    final int WIDTH = 180;
    final int MAX_CHARS = 17;

    final JLabel titleLabel;

    public ProjectBoxView (String title, Color color) {
        setLayout(new BorderLayout());

        titleLabel = new JLabel(configureLabelMaxTextWidth(title));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        System.out.println(getTextWidth("asdfgasdfgasdfgas"));

        final int marginX = (WIDTH/2) - (getTextWidth(title)/2);

        setBackground(color);
        add(Box.createRigidArea(new Dimension(marginX,1)), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(marginX,1)), BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(1,HEIGHT/2)), BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(1,HEIGHT/2)), BorderLayout.SOUTH);
        add(titleLabel,BorderLayout.CENTER);
    }

    public void registerMouseListener (MouseListener controller) {
        addMouseListener(controller);
    }

    private String configureLabelMaxTextWidth (String text) {
        if (text.length() > MAX_CHARS) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(text.substring(0,MAX_CHARS - 4)).append("...");
            return stringBuilder.toString();
        }
        return text;
    }

    private int getTextWidth (String text) {
        FontMetrics fm = getFontMetrics(titleLabel.getFont());
        return fm.stringWidth(text);
    }

}
