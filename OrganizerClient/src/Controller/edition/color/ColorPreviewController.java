package Controller.edition.color;

import View.edition.color.ColorChooserPanel;

import java.awt.event.ActionEvent;

public class ColorPreviewController extends ColorKeeperController {

    private ColorChooserPanel view;

    public ColorPreviewController(ColorChooserPanel view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        view.setPreviewColor(color);
    }

}