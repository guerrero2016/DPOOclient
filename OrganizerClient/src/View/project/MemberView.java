package View.project;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MemberView extends JFrame{

    private final static int WINDOW_WIDTH = 300;

    private final static String WINDOW_TITLE = "Members";
    private final static String NEW_MEMBER_TITLE = "New Member";
    private final static String ADD_TITLE = "+";

    private Font mediumFont;
    private Font smallFont;

    private final JList<User> jlMemberList;
    private final JTextField jtfNewMember;
    private final JButton jbMemberAdder;

    public MemberView(ArrayList<User> members, int height) {

        setSize(new Dimension(WINDOW_WIDTH, height));
        setResizable(false);
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //TODO: Change close operation
        setLocationRelativeTo(null);

        //Load settings
        loadFonts();

        //Main view
        final JPanel jpMain = new JPanel(new BorderLayout());
        setContentPane(jpMain);

        //Scrollable member list
        final JScrollPane jspMembersList = new JScrollPane();
        jpMain.add(jspMembersList, BorderLayout.CENTER);

        //Member list
        jlMemberList = new JList<>();
        jlMemberList.setCellRenderer(new MemberList(mediumFont));
        setMembersList(members);
        jspMembersList.getViewport().setView(jlMemberList);

        //Member adder
        final JPanel jpMemberAdder = new JPanel(new BorderLayout());
        jpMain.add(jpMemberAdder, BorderLayout.PAGE_END);

        //New member title
        final JLabel jlNewMember = new JLabel(NEW_MEMBER_TITLE);
        jlNewMember.setFont(smallFont);
        jlNewMember.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        jpMemberAdder.add(jlNewMember, BorderLayout.LINE_START);

        //New member field
        jtfNewMember = new JTextField();
        jtfNewMember.setEditable(true);
        jpMemberAdder.add(jtfNewMember, BorderLayout.CENTER);

        //Member adder button
        jbMemberAdder = new JButton(ADD_TITLE);
        jpMemberAdder.add(jbMemberAdder, BorderLayout.LINE_END);

    }

    private void loadFonts() {
        mediumFont = new Font(Font.DIALOG, Font.BOLD, 16);
        smallFont = new Font(Font.DIALOG, Font.BOLD, 12);
    }

    public User getSelectedMember() {

        if(jlMemberList.isSelectionEmpty()) {
            return null;
        }

        return jlMemberList.getSelectedValue();

    }

    public void addMember(User member) {
        jlMemberList.add(new MemberList.MemberListComponent(member.getUserName()));
    }

    public void removeMember(int memberPosition) {
        if(memberPosition < jlMemberList.getMaxSelectionIndex()) {
            jlMemberList.remove(memberPosition);
        }
    }

    public void setMembersList(ArrayList<User> members) {
        if(members != null) {

            DefaultListModel<User> membersList = new DefaultListModel<>();

            for(int i = 0; members != null && i < members.size(); i++) {
                membersList.addElement(members.get(i));
            }

            jlMemberList.setModel(membersList);

        }
    }

    public String getNewMemberName() {
        return jtfNewMember.getText();
    }

    public void cleanNewMemberName() {
        jtfNewMember.setText(null);
    }

    public void setLocationToRightOf(Component component) {
        setLocation(component.getLocation().x + component.getWidth(), component.getLocation().y);
    }

}