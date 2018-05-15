package View;

import Controller.SignInController;
import model.user.UserRegister;

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {
    public static final String SIGN = "REGISTRAR-SE";
    public static final String LOG = "INICIA SESSIÓ";
    public static final int SIGNIN = 0;

    private JTextField jtfEmail;
    private JTextField jtfUsername;
    private JPasswordField jpfPassword;
    private JPasswordField jpfConfirm;
    private JButton jbSignIn;
    private JButton jbLogIn;

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
        jpBorder.add(new JLabel("Nom d'usuari"), c);

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
        jpBorder.add(new JLabel("Correu electrònic"), c);

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
        jpBorder.add(new JLabel("Contrasenya"), c);

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
        jpBorder.add(new JLabel("Confirma contrasenya"), c);

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
        jpLog.setBorder(BorderFactory.createTitledBorder("Ja tens compte?"));
        JPanel aux6 = new JPanel(new BorderLayout());
        aux6.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        jbLogIn = new JButton(LOG);
        aux6.add(jbLogIn, BorderLayout.CENTER);

        jpLog.add(aux6, BorderLayout.CENTER);

        this.add(jpLog, BorderLayout.PAGE_END);

    }

    public UserRegister getRegister() {
        UserRegister register = new UserRegister(jtfUsername.getText(), jtfEmail.getText(),
                String.valueOf(jpfPassword.getPassword()), String.valueOf(jpfConfirm.getPassword()));
        return register;
    }

    public void setEmailBorder(Color color) {
        jtfEmail.setBorder(BorderFactory.createLineBorder(color));
    }

    public void setPasswordBorder(Color color) {
        jpfPassword.setBorder(BorderFactory.createLineBorder(color));
    }

    public void setPasswordConfirmBorder(Color color) {
        jpfConfirm.setBorder(BorderFactory.createLineBorder(color));
    }

    public void setUsernameBorder(Color color) {
        jtfUsername.setBorder(BorderFactory.createLineBorder(color));
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message,
                "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

    }

    public void addControllerButton (SignInController sic) {
        jbLogIn.addActionListener(sic);
        jbSignIn.addActionListener(sic);
    }
}
