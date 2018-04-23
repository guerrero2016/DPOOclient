package View.edition.user;

import Model.User;
import View.edition.TransparentPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class UserPanel extends TransparentPanel {

    private final static int PANEL_WIDTH = 225;

    private final static String NEW_USER_TITLE = "New User";
    private final static String ADD_TITLE = "+";

    private final JList<User> jlUsersList;
    private final JTextField jtfNewUser;
    private final JButton jbUserAdder;

    private DefaultListModel<User> usersList;

    public UserPanel(String title) {

        //Panel settings
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(title));
        setPreferredSize(new Dimension(PANEL_WIDTH, getHeight()));

        //Scrollable users list
        final JScrollPane jspUsersList = new JScrollPane();
        add(jspUsersList, BorderLayout.CENTER);

        //Member list
        jlUsersList = new JList<>();
        jlUsersList.setCellRenderer(new UserList());
        jlUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jspUsersList.getViewport().setView(jlUsersList);

        //Member adder
        final TransparentPanel tpUserAdder = new TransparentPanel();
        tpUserAdder.setLayout(new BorderLayout());
        add(tpUserAdder, BorderLayout.PAGE_END);

        //New member title
        final JLabel jlNewUser = new JLabel(NEW_USER_TITLE);
        jlNewUser.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNewUser.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        tpUserAdder.add(jlNewUser, BorderLayout.LINE_START);

        //New member field
        jtfNewUser = new JTextField();
        jtfNewUser.setEditable(true);
        tpUserAdder.add(jtfNewUser, BorderLayout.CENTER);

        //Member adder button
        jbUserAdder = new JButton(ADD_TITLE);
        tpUserAdder.add(jbUserAdder, BorderLayout.LINE_END);

    }

    public UserPanel(ArrayList<User> users, String title) {

        //Panel settings
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(title));
        setPreferredSize(new Dimension(PANEL_WIDTH, getHeight()));

        //Scrollable users list
        final JScrollPane jspUsersList = new JScrollPane();
        add(jspUsersList, BorderLayout.CENTER);

        //Member list
        jlUsersList = new JList<>();
        jlUsersList.setCellRenderer(new UserList());
        jlUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setUsersList(users);
        jspUsersList.getViewport().setView(jlUsersList);

        //Member adder
        final TransparentPanel tpUserAdder = new TransparentPanel();
        tpUserAdder.setLayout(new BorderLayout());
        add(tpUserAdder, BorderLayout.PAGE_END);

        //New member title
        final JLabel jlNewUser = new JLabel(NEW_USER_TITLE);
        jlNewUser.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNewUser.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        tpUserAdder.add(jlNewUser, BorderLayout.LINE_START);

        //New member field
        jtfNewUser = new JTextField();
        jtfNewUser.setEditable(true);
        tpUserAdder.add(jtfNewUser, BorderLayout.CENTER);

        //Member adder button
        jbUserAdder = new JButton(ADD_TITLE);
        jbUserAdder.setEnabled(false);
        tpUserAdder.add(jbUserAdder, BorderLayout.LINE_END);

    }

    private void setUsersList(ArrayList<User> users) {

        usersList = new DefaultListModel<>();

        for(int i = 0; i < users.size(); i++) {
            usersList.addElement(users.get(i));
        }

        jlUsersList.setModel(usersList);

    }

    public void addUser(User user) {
        usersList.addElement(user);
    }

    public void removeUser(int userIndex) {
        usersList.remove(userIndex);
    }

    public String getNewUser() {
        return jtfNewUser.getText();
    }

    public void cleanNewUser() {
        jtfNewUser.setText(null);
    }

    public void setUserAddButtonState(boolean buttonState) {
        jbUserAdder.setEnabled(buttonState);
    }

    public void registerActionController(ActionListener actionListener) {
        jbUserAdder.addActionListener(actionListener);
    }

    public void registerMouseListener(MouseListener mouseListener) {
        jlUsersList.addMouseListener(mouseListener);
    }

    public void registerDocumentListener(DocumentListener documentListener) {
        jtfNewUser.getDocument().addDocumentListener(documentListener);
    }

}