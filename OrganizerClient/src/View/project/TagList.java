package View.project;

import Model.Tag;

import javax.swing.*;
import java.awt.*;

public class TagList implements ListCellRenderer<Tag> {

    public static class TagListComponent extends JLabel {

        public TagListComponent(String tagName) {
            setText(tagName);
            setFont(new Font(Font.DIALOG, Font.BOLD, 16));
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

    }

    @Override
    public Component getListCellRendererComponent(JList list, Tag tag, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        //Tag component
        TagListComponent tagComponent = new TagListComponent(tag.getName());
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
