package model.user;

public class UserRegister extends User {
    public final static int serialVersionUID = 3321;

    private final static int MIN_LENGTH = 8;

    private String email;
    private String password;
    private String confirm;

    public UserRegister(String userName, String email, String password, String confirm) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void encryptPassword() {

        if (checkSignIn() == 0 && (User.containsUpperCase(password) || User.containsUpperCase(confirm))) {
            password = User.getMD5(password);
            confirm = User.getMD5(confirm);
        } else {
            password = "";
            confirm = "";
        }
    }

    public int checkSignIn(){

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

        boolean minus = false;
        boolean num = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                minus = true;
            }

            if (Character.isDigit(password.charAt(i))) {
                num = true;
            }
        }

        if (!minus || !num) {
            return 3;
        }
        return 0;
    }
    
}
