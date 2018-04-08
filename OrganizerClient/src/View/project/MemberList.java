package View.project;

import Model.User;

import javax.swing.*;
import java.awt.*;

public class MemberList implements ListCellRenderer<User> {

    public static class MemberListComponent extends JLabel {

        public MemberListComponent(String memberName) {
            setText(memberName);
        }

    }

    private Font font;

    public MemberList(Font font) {
        this.font = font;
    }

    @Override
    public Component getListCellRendererComponent(JList list, User member, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        //Member component
        MemberList.MemberListComponent memberComponent = new MemberList.MemberListComponent(member.getUserName());
        memberComponent.setFont(font);
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