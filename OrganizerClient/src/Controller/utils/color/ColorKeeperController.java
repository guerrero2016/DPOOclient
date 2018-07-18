package Controller.utils.color;

import View.utils.color.PalettePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de controlar el color seleccionat en una paleta de 6 colors
 */
public class ColorKeeperController implements ActionListener {

    protected Color color;

    /**
     * Metode encarregat d'identificar quin color s'ha clickat
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(PalettePanel.ACTION_GREEN_BUTTON)) {
            color = Color.GREEN;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_CYAN_BUTTON)) {
            color = Color.CYAN;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_MAGENTA_BUTTON)) {
            color = Color.MAGENTA;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_RED_BUTTON)) {
            color = Color.RED;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_ORANGE_BUTTON)) {
            color = Color.ORANGE;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_PINK_BUTTON)) {
            color = Color.PINK;
        }
    }

    /**
     * Geter de l'utlim color clickat (null per defecte)
     * @return Ultim color seleccionat
     */
    public Color getColor() {
        return color;
    }

}