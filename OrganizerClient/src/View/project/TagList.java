package View.project;

import Model.Tag;

import javax.swing.*;
import java.awt.*;

public class TagList implements ListCellRenderer<Tag> {

    private Font font;

    public TagList(Font font) {
        this.font = font;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Tag tag, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        //Tag component
        final JLabel jlTag = new JLabel(tag.getName());
        jlTag.setFont(font);
        jlTag.setOpaque(true);

        if (isSelected) {
            jlTag.setBackground(list.getSelectionBackground());
            jlTag.setForeground(list.getSelectionForeground());
        } else {
            jlTag.setBackground(tag.getColor());
            jlTag.setForeground(list.getForeground());
        }

        return jlTag;

    }

}
