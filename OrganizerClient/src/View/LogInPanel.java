package View;

import Controller.LogInController;
import model.user.User;
import model.user.UserLogIn;

import javax.swing.*;
import java.awt.*;

public class LogInPanel extends JPanel{
    public static final String LOG = "ENTRAR";
    public static final String SIGN = "CREAR COMPTE";
    public static final int LOGIN = 1;

    private JTextField jtfUsername;
    private JPasswordField jpfPassword;
    private JButton jbLogIn;
    private JButton jbSignIn;

    public LogInPanel() {

        this.setLayout(new BorderLayout());

        JPanel jpLog = new JPanel(new BorderLayout());
        jpLog.setBorder(BorderFactory.createTitledBorder("LogIn"));
        JPanel jpBorder = new JPanel(new GridBagLayout());
        jpBorder.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
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
        aux.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        jtfUsername = new JTextField();
        aux.add(jtfUsername, BorderLayout.CENTER);
        jpBorder.add(aux, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        jpBorder.add(new JLabel("Contrasenya"), c);

        //JTextField per la contrasenya
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        JPanel aux2 = new JPanel(new BorderLayout());
        aux2.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        jpfPassword = new JPasswordField();
        jpfPassword.setEchoChar('*');
        aux2.add(jpfPassword, BorderLayout.CENTER);
        jpBorder.add(aux2, c);

        //JButton
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        JPanel aux3 = new JPanel(new BorderLayout());
        aux3.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        jbLogIn = new JButton(LOG);
        aux3.add(jbLogIn);
        jpBorder.add(aux3, c);

        jpLog.add(jpBorder, BorderLayout.CENTER);
        this.add(jpLog);

        JPanel jpSign = new JPanel(new BorderLayout());
        jpSign.setBorder(BorderFactory.createTitledBorder("No tens compte?"));
        JPanel aux4 = new JPanel(new BorderLayout());
        aux4.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        jbSignIn = new JButton(SIGN);
        aux4.add(jbSignIn, BorderLayout.CENTER);

        jpSign.add(aux4, BorderLayout.CENTER);

        this.add(jpSign, BorderLayout.PAGE_END);

    }

    public UserLogIn getLogin() {
        String userName = jtfUsername.getText();
        String password = User.getMD5(String.valueOf(jpfPassword.getPassword()));
        UserLogIn logIn = new UserLogIn(userName, password);
        return logIn;
    }

    public void setPasswordBorder(Color color) {
        jpfPassword.setBorder(BorderFactory.createLineBorder(color));
    }

    public void setUsernameBorder(Color color) {
        jtfUsername.setBorder(BorderFactory.createLineBorder(color));
    }

    public LogInPanel getPanel() {
        return this;
    }


    public void addControllerButton (LogInController lic) {
        jbSignIn.addActionListener(lic);
        jbLogIn.addActionListener(lic);
    }

}
