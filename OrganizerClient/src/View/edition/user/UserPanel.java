package View.edition.user;

import model.user.User;
import View.edition.document.DocumentEnablePanel;
import View.edition.TransparentPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Classe encarregada de genera un panell on mostrar un llistat d'usuaris
 */
public class UserPanel extends TransparentPanel implements DocumentEnablePanel {

    private final static int PANEL_WIDTH = 225;

    private final static String INVITE_USER = "Invite User";
    private final static String NEW_USER_TITLE = "New User";
    private final static String ADD_TITLE = "+";

    private final JList<User> jlUserList;
    private final TransparentPanel tpAddUser;
    private final JButton jbAddUser;
    private final JTextField jtfNewUser;

    private DefaultListModel<User> userList;

    private ActionListener actionListener;
    private MouseListener mouseListener;

    /**
     * Crea un panell per afegir un usuari si el parametre d'entrada és 1 o crea un panell per a convidar usuaris si és
     * 0. Els dos panells tenen un botó i un text. Només el panell per afegir usuari té JTextField.
     * @param i indica quin dels dos panells es crearà.
     */
    public UserPanel(int i) {

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
        tpAddUser = new TransparentPanel();
        tpAddUser.setLayout(new BorderLayout());
        add(tpAddUser, BorderLayout.PAGE_END);

        //User adder button
        jbAddUser = new JButton(ADD_TITLE);
        jbAddUser.setEnabled(false);
        tpAddUser.add(jbAddUser, BorderLayout.LINE_END);

        //New member title
        jtfNewUser = new JTextField();
        if (i == 1) {
            final JLabel jlNewUser = new JLabel(NEW_USER_TITLE);
            jlNewUser.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
            jlNewUser.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            tpAddUser.add(jlNewUser, BorderLayout.LINE_START);

            //New member field
            jtfNewUser.setEditable(true);
            tpAddUser.add(jtfNewUser, BorderLayout.CENTER);
        } else {
            final JLabel jlNewUser = new JLabel(INVITE_USER);
            jlNewUser.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
            jlNewUser.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            tpAddUser.add(jlNewUser, BorderLayout.LINE_START);
            jbAddUser.setEnabled(true);
        }
    }

    /**
     * Mètode que permet afegir els usuaris
     * @param enableState Estat
     */
    public void setEditionState(boolean enableState) {

        remove(tpAddUser);

        if(enableState) {
            add(tpAddUser, BorderLayout.PAGE_END);
        }

    }

    /**
     * Mètode encarregat d'establir el títol de la finestra principal
     * @param title Títol
     */
    public void setTitle(String title) {
        if(title != null) {
            setBorder(BorderFactory.createTitledBorder(title));
        }
    }

    /**
     * Mètode encarregat d'establir la llista d'usuaris
     * @param users Llista d'usuaris
     */
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

    /**
     * Mètode encarregat d'afegir un usuari
     * @param user Usuari a afegir
     */
    public void addUser(User user) {
        userList.addElement(user);
        revalidate();
        repaint();
    }

    /**
     * Mètode encarregat d'eliminar un usuari
     * @param userIndex Index de l'usuari
     */
    public void removeUser(int userIndex) {
        if(userIndex < userList.size()) {
            userList.remove(userIndex);
            revalidate();
            repaint();
        }
    }

    /**
     * Mètode encarregat de netejar la llista d'usuaris
     */
    public void cleanUserList() {
        userList = new DefaultListModel<>();
        jlUserList.setModel(userList);
        revalidate();
        repaint();
    }

    /**
     * Getter del nom del nou usuari
     * @return Nom del nou usuari
     */
    public String getNewUser() {
        return jtfNewUser.getText();
    }

    /**
     * Mètode encarregat de netejar el nom del nou usuari
     */
    public void cleanNewUser() {
        jtfNewUser.setText(null);
    }

    /**
     * Mètode encarregat d'eliminar l'ActionListener de la vista
     */
    public void resetActionController() {
        jbAddUser.removeActionListener(actionListener);
        actionListener = null;
    }

    /**
     * Mètode encarregat d'eliminar un ActionListener
     * @param actionListener Controlador
     */
    public void registerActionController(ActionListener actionListener) {
        this.actionListener = actionListener;
        jbAddUser.addActionListener(actionListener);
    }

    /**
     * Getter del MouseListener de la vista
     * @return Controlador
     */
    public MouseListener getMouseListener() {
        return mouseListener;
    }

    /**
     * Mètode encarregat d'esborrar el MouseListener associat a la vista
     */
    public void resetMouseController() {
        jlUserList.removeMouseListener(mouseListener);
        mouseListener = null;
    }

    /**
     * Mètode encarregat d'afegir un MouseListener
     * @param mouseListener Controlador
     */
    public void registerMouseController(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
        jlUserList.addMouseListener(mouseListener);
    }

    /**
     * Mètode encarregat de registrar un DocumentListener
     * @param documentListener Controlador
     */
    public void registerDocumentListener(DocumentListener documentListener) {
        jtfNewUser.getDocument().addDocumentListener(documentListener);

    }

    /**
     * Mètode encarregat d'habilitar i deshabilitar l'afegir usuaris
     * @param enableState Estat
     */
    @Override
    public void setDocumentEnableState(boolean enableState) {
        jbAddUser.setEnabled(enableState);
    }

}