package View.utils.color;

import javax.swing.*;
import java.awt.*;

/**
 * Classe encarregada de generar un panell que permeti escollir colors d'una paleta
 */
public class ColorChooserPanel extends JPanel {

    private final static String PREVIEW_TITLE = "Color preview";

    private final PalettePanel palettePanel;
    private final JPanel jpColor;

    /**
     * Constructor del selector
     */
    public ColorChooserPanel() {

        //Config panel
        setLayout(new BorderLayout());

        //Palette panel
        palettePanel = new PalettePanel();
        add(palettePanel, BorderLayout.CENTER);

        //Preview panel
        final JPanel jpPreview = new JPanel(new BorderLayout());
        jpPreview.setBorder(BorderFactory.createTitledBorder(PREVIEW_TITLE));
        add(jpPreview, BorderLayout.PAGE_END);

        //Color panel
        jpColor = new JPanel();
        jpColor.setBackground(Color.WHITE);
        jpColor.setPreferredSize(new Dimension(PalettePanel.HORIZONTAL_BUTTONS * PalettePanel.BUTTON_WIDTH,
                PalettePanel.VERTICAL_BUTTONS * PalettePanel.BUTTON_HEIGHT));
        jpPreview.add(jpColor, BorderLayout.CENTER);

    }

    /**
     * Getter de la paleta de colors
     * @return Paleta de colors
     */
    public PalettePanel getPalettePanel() {
        return palettePanel;
    }

    /**
     * Metode encarregat d'establir la previsualitacio del color seleccionat
     * @param color Color a mostrar
     */
    public void setPreviewColor(Color color) {
        if(color != null) {
            jpColor.setBackground(color);
        }
    }

}