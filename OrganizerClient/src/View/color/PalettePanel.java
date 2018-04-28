package View.color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PalettePanel extends JPanel {

    public final static String ACTION_GREEN_BUTTON = "GreenButton";
    public final static String ACTION_CYAN_BUTTON = "CyanButton";
    public final static String ACTION_MAGENTA_BUTTON = "MagentaButton";
    public final static String ACTION_RED_BUTTON = "RedButton";
    public final static String ACTION_ORANGE_BUTTON = "OrangeButton";
    public final static String ACTION_PINK_BUTTON = "PinkButton";

    public final static int BUTTON_WIDTH = 50;
    public final static int BUTTON_HEIGHT = 50;
    public final static int HORIZONTAL_BUTTONS = 3;
    public final static int VERTICAL_BUTTONS = 2;

    private final static String CHOOSING_TITLE = "Choose a color";

    private final JButton jbGreen;
    private final JButton jbCyan;
    private final JButton jbMagenta;
    private final JButton jbRed;
    private final JButton jbOrange;
    private final JButton jbPink;

    public PalettePanel() {

        //Panel config
        setLayout(new GridLayout(2, 3));
        setBorder(BorderFactory.createTitledBorder(CHOOSING_TITLE));

        //Green button
        jbGreen = new JButton();
        jbGreen.setBackground(Color.GREEN);
        jbGreen.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbGreen.setActionCommand(ACTION_GREEN_BUTTON);
        add(jbGreen);

        //Cyan button
        jbCyan = new JButton();
        jbCyan.setBackground(Color.CYAN);
        jbCyan.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbCyan.setActionCommand(ACTION_CYAN_BUTTON);
        add(jbCyan);

        //Magenta button
        jbMagenta = new JButton();
        jbMagenta.setBackground(Color.MAGENTA);
        jbMagenta.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbMagenta.setActionCommand(ACTION_MAGENTA_BUTTON);
        add(jbMagenta);

        //Red button
        jbRed = new JButton();
        jbRed.setBackground(Color.RED);
        jbRed.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbRed.setActionCommand(ACTION_RED_BUTTON);
        add(jbRed);

        //Orange button
        jbOrange = new JButton();
        jbOrange.setBackground(Color.ORANGE);
        jbOrange.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbOrange.setActionCommand(ACTION_ORANGE_BUTTON);
        add(jbOrange);

        //Pink button
        jbPink = new JButton();
        jbPink.setBackground(Color.PINK);
        jbPink.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        jbPink.setActionCommand(ACTION_PINK_BUTTON);
        add(jbPink);

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