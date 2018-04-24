package Model.user;

import java.io.Serializable;

public class UserRegister implements Serializable{
    private final int MIN_LENGTH = 8;

    private String userName;
    private String email;
    private String password;
    private String confirm;

    public String getUserName() {
        return userName;
    }

    public int checkSignIn() throws Exception{

        if (userName == null){
            return 3;
        }
        for(int i = 0; i < userName.length(); i++) {
            if(!Character.isLetterOrDigit(userName.charAt(i)) && !(userName.charAt(i) == '_')) {
                return 3;
            }
        }

        if (email == null) {
            return 3;
        }for(int i = 0; i < email.length(); i++) {
            if(!Character.isLetterOrDigit(email.charAt(i)) && !(email.charAt(i) == '@') &&
                    !(email.charAt(i) == '_') && !(email.charAt(i) == '.')) {
                return 3;
            }
        }
        boolean arroba = false;
        boolean dot = false;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                arroba = true;
            }
            if (email.charAt(i) == '.') {
                dot = true;
            }
        }
        if (!arroba || !dot) {
            return 3;
        }

        if (!password.equals(confirm)) {
            return 3;
        }

        if (password.length() < MIN_LENGTH) {
            return 3;
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

        if (!minus || !majus || !num) {
            return 3;
        }
        return 0;
        //return DataBaseManager.RegistrarUsuari(userName, email, password);
    }
}
