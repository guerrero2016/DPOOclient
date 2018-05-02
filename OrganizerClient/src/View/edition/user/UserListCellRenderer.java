package View.edition.user;

import ModelAEliminar.User;

import javax.swing.*;
import java.awt.*;

public class UserListCellRenderer implements ListCellRenderer<User> {

    public static class UserComponent extends JLabel {

        public UserComponent(String memberName) {
            setText(memberName);
            setFont(new Font(Font.DIALOG, Font.BOLD, 16));
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

    }

    @Override
    public Component getListCellRendererComponent(JList list, User member, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        //Member component
        UserComponent memberComponent = new UserComponent(member.getName());
        memberComponent.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        memberComponent.setOpaque(true);

        if (isSelected) {
            memberComponent.setBackground(list.getSelectionBackground());
            memberComponent.setForeground(list.getSelectionForeground());
        } else {
            memberComponent.setBackground(list.getBackground());
            memberComponent.setForeground(list.getForeground());
        }

        return memberComponent;

    }

}