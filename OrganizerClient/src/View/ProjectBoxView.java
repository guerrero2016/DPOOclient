package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class ProjectBoxView extends JPanel {
    public static final String INFO_AC = "INFO";
    public static final String DELETE_AC = "DELETE";

    final int HEIGHT = 80;  //50
    final int WIDTH = 180;  //180
    final int MAX_CHARS = 10;
    private final static String INFO_ICON = "img/info_icon.png";
    private final static String DELETE_ICON = "img/delete_icon.png";

    final JLabel titleLabel;
    private String title;
    private CustomProjectButton jbInfo;
    private CustomProjectButton jbDelete;
    private final int index;
    private final boolean isOwner;

    public ProjectBoxView (String title, Color color, int index, boolean isOwner) {
        setLayout(new BorderLayout());

        this.index = index;
        this.title  = title;
        this.isOwner = isOwner;

        JPanel jpLabel = new JPanel(new BorderLayout());
        titleLabel = new JLabel(configureLabelMaxTextWidth(title));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(HEIGHT / 2 - 5, WIDTH / 2,
                HEIGHT / 2 + 5,WIDTH / 2));
        jpLabel.add(titleLabel, BorderLayout.EAST);

        Image infoImage = null;
        Image deleteImage = null;
        try {
            infoImage = ImageIO.read(new File(INFO_ICON)).
                    getScaledInstance(10, 10, Image.SCALE_SMOOTH);

            deleteImage = ImageIO.read(new File(DELETE_ICON)).
                    getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel jpAux = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        jbInfo = new CustomProjectButton(new ImageIcon(infoImage), title, index);
        jbInfo.setBackground(color);
        jbInfo.setBorder(null);
        jbInfo.setActionCommand(INFO_AC);

        jbDelete = new CustomProjectButton(new ImageIcon(deleteImage), title, index);

        if (isOwner) {
            jbDelete.setBackground(color);
            jbDelete.setBorder(null);
            jbDelete.setActionCommand(DELETE_AC);
            jpAux.add(jbDelete);
        }

        jpAux.setBackground(color);
        jpAux.add(jbInfo);


        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(color);
        jpLabel.setBackground(color);
        this.add(jpAux, BorderLayout.NORTH);
        this.add(jpLabel,BorderLayout.CENTER);
    }

    public String getTitle() {
        return title;
    }

    public void registerButtonListener (ActionListener controller){
        jbDelete.addActionListener(controller);
        jbInfo.addActionListener(controller);
    }

    public void registerMouseListener (MouseListener controller) {
        addMouseListener(controller);
    }

    private String configureLabelMaxTextWidth (String text) {
        if (text.length() > MAX_CHARS) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(text.substring(0,MAX_CHARS - 4)).append("...");
            return stringBuilder.toString();
        }
        return text;
    }

    private int getTextWidth (String text) {
        FontMetrics fm = getFontMetrics(titleLabel.getFont());
        return fm.stringWidth(text);
    }

}
