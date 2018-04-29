package Controller.edition.color;

import View.edition.color.PalettePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorKeeperController implements ActionListener {

    protected Color color;

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

    public Color getColor() {
        return color;
    }

}