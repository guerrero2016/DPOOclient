package View.edition;

import javax.swing.*;
import java.awt.*;

/**
 * Classe encarregada de genrerar un panell transparent amb scroll
 */
public class TransparentScrollPanel extends JScrollPane {

    public TransparentScrollPanel() {
        super.getViewport().setOpaque(false);
    }

    /**
     * Mètode que permet fer transparent el panell
     * @param g Graphics
     */
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0));
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);
    }

    /**
     * Mètode bloquejat per evitar desfer la transparència
     * @param b Sempre false
     */
    @Override
    public void setOpaque(boolean b) {}

}