package View.project;

import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel {

    public TransparentPanel() {
        super.setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0));
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);
    }

    @Override
    public void setOpaque(boolean b) {}

}