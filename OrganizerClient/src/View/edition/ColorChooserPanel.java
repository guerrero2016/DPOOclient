package View.edition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ColorChooserPanel extends JPanel {

    public final static String ACTION_GREEN_BUTTON = "GreenButton";
    public final static String ACTION_CYAN_BUTTON = "CyanButton";
    public final static String ACTION_MAGENTA_BUTTON = "MagentaButton";
    public final static String ACTION_RED_BUTTON = "RedButton";
    public final static String ACTION_ORANGE_BUTTON = "OrangeButton";
    public final static String ACTION_PINK_BUTTON = "PinkButton";

    private final static String CHOOSING_TITLE = "Choose a color";
    private final static String PREVIEW_TITLE = "Color preview";

    private final static int BUTTON_WIDTH = 50;
    private final static int BUTTON_HEIGHT = 50;
    private final static int PREVIEW_WIDTH = 150;
    private final static int PREVIEW_HEIGHT = 150;

    private final JButton jbGreen;
    private final JButton jbCyan;
    private final JButton jbMagenta;
    private final JButton jbRed;
    private final JButton jbOrange;
    private final JButton jbPink;
    private final JPanel jpColor;

    public ColorChooserPanel() {

        //Config panel
        setLayout(new BorderLayout());

        //Choosing panel
        final JPanel jpChoose = new JPanel(new GridLayout(2, 3));
        jpChoose.setBorder(BorderFactory.createTitledBorder(CHOOSING_TITLE));
        add(jpChoose, BorderLayout.CENTER);

        //Green button
        jbGreen = new JButton();
        jbGreen.setBackground(Color.GREEN);
        jbGreen.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbGreen.setActionCommand(ACTION_GREEN_BUTTON);
        jpChoose.add(jbGreen);

        //Cyan button
        jbCyan = new JButton();
        jbCyan.setBackground(Color.CYAN);
        jbCyan.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbCyan.setActionCommand(ACTION_CYAN_BUTTON);
        jpChoose.add(jbCyan);

        //Magenta button
        jbMagenta = new JButton();
        jbMagenta.setBackground(Color.MAGENTA);
        jbMagenta.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbMagenta.setActionCommand(ACTION_MAGENTA_BUTTON);
        jpChoose.add(jbMagenta);

        //Red button
        jbRed = new JButton();
        jbRed.setBackground(Color.RED);
        jbRed.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbRed.setActionCommand(ACTION_RED_BUTTON);
        jpChoose.add(jbRed);

        //Orange button
        jbOrange = new JButton();
        jbOrange.setBackground(Color.ORANGE);
        jbOrange.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbOrange.setActionCommand(ACTION_ORANGE_BUTTON);
        jpChoose.add(jbOrange);

        //Pink button
        jbPink = new JButton();
        jbPink.setBackground(Color.PINK);
        jbPink.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbPink.setActionCommand(ACTION_PINK_BUTTON);
        jpChoose.add(jbPink);

        //Preview panel
        final JPanel jpPreview = new JPanel(new BorderLayout());
        jpPreview.setBorder(BorderFactory.createTitledBorder(PREVIEW_TITLE));
        add(jpPreview, BorderLayout.PAGE_END);

        //Color panel
        jpColor = new JPanel();
        jpColor.setBackground(Color.WHITE);
        jpColor.setPreferredSize(new Dimension(PREVIEW_WIDTH, PREVIEW_HEIGHT));
        jpPreview.add(jpColor, BorderLayout.CENTER);

    }

    public void setPreviewColor(Color color) {
        if(color != null) {
            jpColor.setBackground(color);
        }
    }

    public void registerActionController(ActionListener actionListener) {
        jbGreen.addActionListener(actionListener);
        jbCyan.addActionListener(actionListener);
        jbMagenta.addActionListener(actionListener);
        jbRed.addActionListener(actionListener);
        jbPink.addActionListener(actionListener);
        jbOrange.addActionListener(actionListener);
    }

}