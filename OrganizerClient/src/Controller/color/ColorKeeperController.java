package Controller.color;

import View.color.PalettePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorKeeperController implements ActionListener {

    protected Color currentColor;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(PalettePanel.ACTION_GREEN_BUTTON)) {
            currentColor = Color.GREEN;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_CYAN_BUTTON)) {
            currentColor = Color.CYAN;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_MAGENTA_BUTTON)) {
            currentColor = Color.MAGENTA;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_RED_BUTTON)) {
            currentColor = Color.RED;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_ORANGE_BUTTON)) {
            currentColor = Color.ORANGE;
        } else if(e.getActionCommand().equals(PalettePanel.ACTION_PINK_BUTTON)) {
            currentColor = Color.PINK;
        }
    }

    public Color getColor() {
        return currentColor;
    }

}