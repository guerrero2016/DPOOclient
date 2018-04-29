package View.edition;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image background;

    @Override
    public void paintComponent(Graphics g) {

        if(background != null) {
            int width = getSize().width;
            int height = getSize().height;
            g.drawImage(background.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width, height,
                    null);
        }

        super.paintComponent(g);

    }

    public void setBackgroundImage(Image background) {
        setOpaque(false);
        this.background = background;
        repaint();
    }

}