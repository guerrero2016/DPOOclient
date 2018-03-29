package View;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {

    private Image background;

    @Override
    public void paintComponent(Graphics g) {

        if(background != null) {
            int width = getSize().width;
            int height = getSize().height;
            g.drawImage(background, 0, 0, width, height, null);
        }

        super.paintComponent(g);

    }

    public void setBackground(Image background) {
        setOpaque(false);
        this.background = background;
        repaint();
    }

}