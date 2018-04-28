package ModelAEliminar.user;

import javax.swing.*;
import java.io.Serializable;

public class Register implements Serializable{
    private final int MIN_LENGTH = 8;

    private String userName;
    private String email;
    private String password;
    private String confirm;


    public boolean checkSignIn() {
        final String userName = "TaskAddUserController";
        final String correu = "b";
        final String password = "c";
        final String confirmation = "d";
        final StringBuilder stringBuilder = new StringBuilder();
        Boolean dadesOK = true;

        if(userName.length() < MIN_LENGTH) {
            dadesOK = false;
            stringBuilder.append("  ─El nom d'usuari especificat és massa curt");
            stringBuilder.append(System.getProperty("line.separator"));
        }
        for(int i = 0; i < userName.length(); i++) {
            if(!Character.isLetterOrDigit(userName.charAt(i)) && !(userName.charAt(i) == 95)) {
                dadesOK = false;
                stringBuilder.append("  ─El nom d'usuari tan sols pot contenir lletres," +
                        System.getProperty("line.separator") + "        dígits i el caràcter '_'");
                stringBuilder.append(System.getProperty("line.separator"));
                break;
            }
        }
        boolean flag = false;
        for(int i = 0; i < correu.length(); i++) {
            if(!Character.isLetterOrDigit(correu.charAt(i)) && !(correu.charAt(i) == 95) &&
                    !(correu.charAt(i) == 64) && !(correu.charAt(i) == 46)) {
                dadesOK = false;
                stringBuilder.append("  ─El correu és incorrecte");
                stringBuilder.append(System.getProperty("line.separator"));
                flag = true;
                break;
            }
        }
        if(!flag) {
            boolean arroba = false;
            boolean dot = false;
            for (int i = 0; i < correu.length(); i++) {
                if (correu.charAt(i) == '@') {
                    arroba = true;
                }
                if (correu.charAt(i) == '.') {
                    dot = true;
                }
            }
            if (!arroba || !dot) {
                dadesOK = false;
                stringBuilder.append("  ─El correu és incorrecte");
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
        boolean flag2 = false;
        if(password.length() < MIN_LENGTH) {
            dadesOK = false;
            stringBuilder.append("  ─La contrasenya especificada és massa curta");
            stringBuilder.append(System.getProperty("line.separator"));
            flag2 = true;
        }
        if(!flag2) {
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) < 33 || password.charAt(i) > 126) {
                    dadesOK = false;
                    stringBuilder.append("  ─La contrasenya conté caràcters prohibits");
                    stringBuilder.append(System.getProperty("line.separator"));
                    break;
                }
            }
            boolean majus = false;
            boolean minus = false;
            boolean num = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isLowerCase(password.charAt(i))) {
                    minus = true;
                }
                if (Character.isUpperCase(password.charAt(i))) {
                    majus = true;
                }
                if (Character.isDigit(password.charAt(i))) {
                    num = true;
                }
            }
            if (!majus || !minus || !num) {
                dadesOK = false;
                stringBuilder.append("  ─La contrasenya ha de contenir mínim una majúscula," +
                        System.getProperty("line.separator") + "        una minúscula i un número");
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }

        if(!password.equals(confirmation)) {
            dadesOK = false;
            stringBuilder.append("  ─Les contrasenyes han de coincidir");
            stringBuilder.append(System.getProperty("line.separator"));
        }

        //TODO Quan tinguem la vista s'ha de descomentar lo comentat.
        //view.showError(stringBuilder.toString());
        JOptionPane.showMessageDialog(null, stringBuilder,
                "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        return dadesOK;
    }
}
