package View.edition.user;

import Model.user.User;
import View.edition.document.DocumentEnablePanel;
import View.edition.TransparentPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class UserPanel extends TransparentPanel implements DocumentEnablePanel {

    private final static int PANEL_WIDTH = 225;

    private final static String NEW_USER_TITLE = "New User";
    private final static String ADD_TITLE = "+";

    private final JList<User> jlUserList;
    private final JTextField jtfNewUser;
    private final JButton jbAddUser;

    private DefaultListModel<User> userList;

    private ActionListener actionListener;
    private MouseListener mouseListener;

    public UserPanel() {

        //Panel settings
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, getHeight()));

        //Scrollable users list
        final JScrollPane jspUsersList = new JScrollPane();
        add(jspUsersList, BorderLayout.CENTER);

        //Member list
        jlUserList = new JList<>();
        jlUserList.setCellRenderer(new UserListCellRenderer());
        jlUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jspUsersList.getViewport().setView(jlUserList);

        //Member adder
        final TransparentPanel tpAddUser = new TransparentPanel();
        tpAddUser.setLayout(new BorderLayout());
        add(tpAddUser, BorderLayout.PAGE_END);

        //New member title
        final JLabel jlNewUser = new JLabel(NEW_USER_TITLE);
        jlNewUser.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNewUser.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        tpAddUser.add(jlNewUser, BorderLayout.LINE_START);

        //New member field
        jtfNewUser = new JTextField();
        jtfNewUser.setEditable(true);
        tpAddUser.add(jtfNewUser, BorderLayout.CENTER);

        //User adder button
        jbAddUser = new JButton(ADD_TITLE);
        jbAddUser.setEnabled(false);
        tpAddUser.add(jbAddUser, BorderLayout.LINE_END);

    }

    public void setTitle(String title) {
        if(title != null) {
            setBorder(BorderFactory.createTitledBorder(title));
        }
    }

    public void setUserList(ArrayList<User> users) {
        if(users != null) {

            userList = new DefaultListModel<>();

            for (int i = 0; i < users.size(); i++) {
                userList.addElement(users.get(i));
            }

            jlUserList.setModel(userList);
            revalidate();
            repaint();

        }
    }

    public void addUser(User user) {
        userList.addElement(user);
        revalidate();
        repaint();
    }

    public void removeUser(int userIndex) {
        if(userIndex < userList.size()) {
            userList.remove(userIndex);
            revalidate();
            repaint();
        }
    }

    public void cleanUserList() {
        userList = new DefaultListModel<>();
        jlUserList.setModel(userList);
        revalidate();
        repaint();
    }

    public String getNewUser() {
        return jtfNewUser.getText();
    }

    public void cleanNewUser() {
        jtfNewUser.setText(null);
    }

    public void cleanActionController() {
        jbAddUser.removeActionListener(actionListener);
    }

    public void resetActionController() {
        jbAddUser.removeActionListener(actionListener);
    }

    public void registerActionController(ActionListener actionListener) {
        this.actionListener = actionListener;
        jbAddUser.addActionListener(actionListener);
    }

    public void resetMouseController() {
        jlUserList.removeMouseListener(mouseListener);
    }

    public void registerMouseController(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
        jlUserList.addMouseListener(mouseListener);
    }

    public void registerDocumentListener(DocumentListener documentListener) {
        jtfNewUser.getDocument().addDocumentListener(documentListener);
    }

    @Override
    public void setDocumentEnableState(boolean enableState) {
        jbAddUser.setEnabled(enableState);
    }

}