package View.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que genera un panell que pot tenir una imatge de fons
 */
public class BackgroundPanel extends JPanel {

    private Image background;

    /**
     * Mètode encarregar de pintar la imatge
     * @param g Graphics
     */
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

    /**
     * Mètode que permet establir una imatge de fons
     * @param background Imatge de fons
     */
    public void setBackgroundImage(Image background) {
        setOpaque(false);
        this.background = background;
        repaint();
    }

}