package View.project;

import Model.Tag;

import javax.swing.*;
import java.awt.*;

public class TagList implements ListCellRenderer<Tag> {

    private Font font;

    public static class TagListComponent extends JLabel {

        public TagListComponent(String tagName) {
            setText(tagName);
        }

    }

    public TagList(Font font) {
        this.font = font;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Tag tag, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        //Tag component
        TagListComponent tagComponent = new TagListComponent(tag.getName());
        tagComponent.setFont(font);
        tagComponent.setOpaque(true);

        if (isSelected) {
            tagComponent.setBackground(list.getSelectionBackground());
            tagComponent.setForeground(list.getSelectionForeground());
        } else {
            tagComponent.setBackground(tag.getColor());
            tagComponent.setForeground(list.getForeground());
        }

        return tagComponent;

    }

}
