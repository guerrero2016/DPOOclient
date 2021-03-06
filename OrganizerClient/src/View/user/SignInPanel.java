package View.user;

import model.user.UserRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe que genera el panell per a registrar-se
 */
public class SignInPanel extends JPanel {

    public static final String SIGN = "REGISTER";
    public static final String LOG = "LOG IN";
    public static final int SIGN_IN = 0;

    private JTextField jtfEmail;
    private JTextField jtfUsername;
    private JPasswordField jpfPassword;
    private JPasswordField jpfConfirm;
    private JButton jbSignIn;
    private JButton jbLogIn;

    /**
     * Crea el panell amb els diferents botons i zones de text
     */
    public SignInPanel() {

        this.setLayout(new BorderLayout());

        JPanel jpSign = new JPanel(new BorderLayout());
        jpSign.setBorder(BorderFactory.createTitledBorder("SignIn"));
        JPanel jpBorder = new JPanel(new GridBagLayout());
        jpBorder.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        GridBagConstraints c = new GridBagConstraints();

        c.ipadx = 20;
        c.weightx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        jpBorder.add(new JLabel("Username"), c);

        //JTextField per al nom d'usuari
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        JPanel aux = new JPanel(new BorderLayout());
        aux.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jtfUsername = new JTextField();
        aux.add(jtfUsername, BorderLayout.CENTER);
        jpBorder.add(aux, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        jpBorder.add(new JLabel("Email"), c);

        //JTextField per al nom d'usuari
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        JPanel aux2 = new JPanel(new BorderLayout());
        aux2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jtfEmail = new JTextField();
        aux2.add(jtfEmail, BorderLayout.CENTER);
        jpBorder.add(aux2, c);

        //JLabel Contrasenya
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        jpBorder.add(new JLabel("Password"), c);

        //JTextField per la contrasenya
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        JPanel aux3 = new JPanel(new BorderLayout());
        aux3.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpfPassword = new JPasswordField();
        jpfPassword.setEchoChar('*');
        aux3.add(jpfPassword, BorderLayout.CENTER);
        jpBorder.add(aux3, c);

        //JLabel Confirmació
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        jpBorder.add(new JLabel("Confirm your password"), c);

        //JTextField per la confirmació
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        JPanel aux4 = new JPanel(new BorderLayout());
        aux4.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpfConfirm = new JPasswordField();
        jpfConfirm.setEchoChar('*');
        aux4.add(jpfConfirm, BorderLayout.CENTER);
        jpBorder.add(aux4, c);

        //JButton
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        JPanel aux5 = new JPanel(new BorderLayout());
        aux5.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jbSignIn = new JButton(SIGN);
        aux5.add(jbSignIn);
        jpBorder.add(aux5, c);

        jpSign.add(jpBorder, BorderLayout.CENTER);
        this.add(jpSign);

        JPanel jpLog = new JPanel(new BorderLayout());
        jpLog.setBorder(BorderFactory.createTitledBorder("Already have an account?"));
        JPanel aux6 = new JPanel(new BorderLayout());
        aux6.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        jbLogIn = new JButton(LOG);
        aux6.add(jbLogIn, BorderLayout.CENTER);

        jpLog.add(aux6, BorderLayout.CENTER);

        this.add(jpLog, BorderLayout.PAGE_END);

    }

    /**
     * Funcio que recupera allo escrit en les diferents zones de text
     * @return Un objecte de tipus <code>UserRegister</code> amb els atributs iniciats amb allo escrit per l'usuari
     *         Si els camps de la contrasenya no tenen majuscula, es posen a <code>null</code>
     */
    public UserRegister getRegister() {
        String username = jtfUsername.getText();
        String email = jtfEmail.getText();
        String password = String.valueOf(jpfPassword.getPassword());
        String confirm = String.valueOf(jpfConfirm.getPassword());
        UserRegister userRegister = new UserRegister(username, email, password, confirm);
        setErrorsView(userRegister.encryptPassword());
        return userRegister;
    }

    /**
     * Procediment que s'encarrega de, donat un codi d'error, saber quins son els camps els quals contenen errors
     * Pinta els camps amb errors de color vermerll
     * @param error Codi d'error
     */
    private void setErrorsView(int error){
        clearFieldsBorder();
        if (error >= UserRegister.PASS_ERROR) {
            error -= UserRegister.PASS_ERROR;
            setPasswordBorder(Color.RED);
        }

        if (error >= UserRegister.EMAIL_ERROR) {
            error -= UserRegister.EMAIL_ERROR;
            setEmailBorder(Color.RED);
        }

        if (error >= UserRegister.NAME_ERROR) {
            setUsernameBorder(Color.RED);
        }
    }

    /**
     * Procediment que pinta el Border del JTextField del correu
     * @param color Camp que indica de quin color es vol pintar. Si es <code>null</code>, es retorna al color per
     *              defecte
     */
    private void setEmailBorder(Color color) {
        if (color == null) {
            jtfEmail.setBorder(UIManager.getBorder("TextField.border"));
        } else {
            jtfEmail.setBorder(BorderFactory.createLineBorder(color));
        }
    }

    /**
     * Procediment que pinta el Border dels JTextField de contrasenya i de confirm
     * @param color Camp que indica de quin color es vol pintar. Si es <code>null</code>, es retorna al color per
     *              defecte
     */
    private void setPasswordBorder(Color color) {
        if (color == null) {
            jpfPassword.setBorder(UIManager.getBorder("TextField.border"));
            jpfConfirm.setBorder(UIManager.getBorder("TextField.border"));
        } else {
            jpfPassword.setBorder(BorderFactory.createLineBorder(color));
            jpfConfirm.setBorder(BorderFactory.createLineBorder(color));
        }

    }

    /**
     * Procediment que pinta el Border del JTextField d'usuari
     * @param color Camp que indica de quin color es vol pintar. Si es <code>null</code>, es retorna al color per
     *              defecte
     */
    private void setUsernameBorder(Color color) {
        if (color == null) {
            jtfUsername.setBorder(UIManager.getBorder("TextField.border"));
        } else {
            jtfUsername.setBorder(BorderFactory.createLineBorder(color));
        }
    }

    /**
     * Procediment que neteja els colors dels Borders dels JTextFields
     */
    private void clearFieldsBorder() {
        setEmailBorder(null);
        setUsernameBorder(null);
        setPasswordBorder(null);
    }

    /**
     * Procediment que registra els botons amb un <code>ActionListener</code>
     * @param al <code>ActionListener</code> el qual serà notificat quan es premi un boto
     */
    public void addControllerButton(ActionListener al) {
        jbLogIn.addActionListener(al);
        jbSignIn.addActionListener(al);
    }

    /**
     * Procediment per netejar els camps
     */
    public void clearScreen(){
        clearFieldsBorder();
        jtfEmail.setText(null);
        jtfUsername.setText(null);
        jpfPassword.setText(null);
        jpfConfirm.setText(null);
    }

}