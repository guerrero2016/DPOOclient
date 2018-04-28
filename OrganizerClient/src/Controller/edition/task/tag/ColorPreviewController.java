package Controller.edition.task.tag;

import View.edition.ColorChooserPanel;
import View.edition.ColorPreviewPanel;

import java.awt.event.ActionEvent;

public class ColorPreviewController extends ColorKeeperController {

    private ColorPreviewPanel view;

    public ColorPreviewController(ColorChooserPanel view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        view.setPreviewColor(currentColor);
    }

}