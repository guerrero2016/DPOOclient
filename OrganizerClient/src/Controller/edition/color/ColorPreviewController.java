package Controller.edition.color;

import View.edition.color.ColorChooserPanel;

import java.awt.event.ActionEvent;

/**
 * Clase encarregada de mostrar el color seleccionat en una vista del tipus ColorChooserPanel
 */
public class ColorPreviewController extends ColorKeeperController {

    private ColorChooserPanel view;

    /**
     * Constructor a partir de la vista del tipus ColorChooserPanel
     * @param view Vista a controlar
     */
    public ColorPreviewController(ColorChooserPanel view) {
        this.view = view;
    }

    /**
     * Mètode encarregat de mostrar el color sel·leccionat a la vista
     * @param e Action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        view.setPreviewColor(color);
    }

}