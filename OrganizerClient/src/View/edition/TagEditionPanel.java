package View.edition;

import javax.swing.*;
import java.awt.*;

public class TagEditionPanel extends JPanel {

    private final static String TAG_NAME_TITLE = "Tag Name";

    private final JTextField jtfTagName;
    private final ColorChooserPanel colorChooserPanel;

    public TagEditionPanel(String tagName) {

        //Main config
        setLayout(new BorderLayout());

        //Tag name panel
        final JPanel jpTagName = new JPanel(new BorderLayout());
        jpTagName.setBorder(BorderFactory.createTitledBorder(TAG_NAME_TITLE));
        add(jpTagName, BorderLayout.PAGE_START);

        //Tag name
        jtfTagName = new JTextField(tagName);
        jtfTagName.setEditable(true);
        jpTagName.add(jtfTagName, BorderLayout.CENTER);

        //Color chooser panel
        colorChooserPanel = new ColorChooserPanel();
        add(colorChooserPanel, BorderLayout.CENTER);

    }

    public String getTagName() {
        return jtfTagName.getText();
    }

    public ColorChooserPanel getColorChooserPanel() {
        return colorChooserPanel;
    }

}