package Controller.edition.task.tag;

import View.edition.ColorChooserPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PickColorController implements ActionListener {

    private ColorChooserPanel view;
    private Color currentColor;

    public PickColorController(ColorChooserPanel view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(ColorChooserPanel.ACTION_GREEN_BUTTON)) {
            currentColor = Color.GREEN;
        } else if(e.getActionCommand().equals(ColorChooserPanel.ACTION_CYAN_BUTTON)) {
            currentColor = Color.CYAN;
        } else if(e.getActionCommand().equals(ColorChooserPanel.ACTION_MAGENTA_BUTTON)) {
            currentColor = Color.MAGENTA;
        } else if(e.getActionCommand().equals(ColorChooserPanel.ACTION_RED_BUTTON)) {
            currentColor = Color.RED;
        } else if(e.getActionCommand().equals(ColorChooserPanel.ACTION_ORANGE_BUTTON)) {
            currentColor = Color.ORANGE;
        } else if(e.getActionCommand().equals(ColorChooserPanel.ACTION_PINK_BUTTON)) {
            currentColor = Color.PINK;
        }

        view.setPreviewColor(currentColor);

    }

    public Color getCurrentColor() {
        return currentColor;
    }

}