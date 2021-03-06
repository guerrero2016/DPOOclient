package View.edition.user;

import model.user.User;

import javax.swing.*;
import java.awt.*;

/**
 * Classe encarregada de generar el CellRenderer d'usuaris
 */
public class UserListCellRenderer implements ListCellRenderer<User> {

    /**
     * Classe que genera els components de la llista
     */
    public static class UserComponent extends JLabel {

        public UserComponent(String memberName) {
            setText(memberName);
            setFont(new Font(Font.DIALOG, Font.BOLD, 16));
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

    }

    @SuppressWarnings("Duplicates")
    @Override
    public Component getListCellRendererComponent(JList list, User user, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        //Member component
        UserComponent memberComponent = new UserComponent(user.getUserName());
        memberComponent.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        memberComponent.setOpaque(true);

        if(isSelected) {
            memberComponent.setBackground(list.getSelectionBackground());
            memberComponent.setForeground(list.getSelectionForeground());
        } else {
            memberComponent.setBackground(list.getBackground());
            memberComponent.setForeground(list.getForeground());
        }

        return memberComponent;

    }

}