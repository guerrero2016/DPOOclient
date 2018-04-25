package View.edition.user;

import ModelAEliminar.User;

import javax.swing.*;
import java.awt.*;

public class UserList implements ListCellRenderer<User> {

    public static class MemberListComponent extends JLabel {

        public MemberListComponent(String memberName) {
            setText(memberName);
            setFont(new Font(Font.DIALOG, Font.BOLD, 16));
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

    }

    @Override
    public Component getListCellRendererComponent(JList list, User member, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        //Member component
        UserList.MemberListComponent memberComponent = new UserList.MemberListComponent(member.getName());
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