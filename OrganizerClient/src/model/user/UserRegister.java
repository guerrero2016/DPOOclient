package model.user;

/**
 * Representacio d'un usuari en el moment de registrar-se
 */
public class UserRegister extends User {
    public final static int serialVersionUID = 1240;
    public final static int NAME_ERROR = 11;
    public final static int EMAIL_ERROR = 13;
    public final static int PASS_ERROR = 29;

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

    /**
     * Funcio encargada d'encriptar la contrasenya
     * @return Si retorna 0 s'ha encriptat correctament, sino hi ha hagut algun error
     */
    public int encryptPassword() {
        int error = checkSignIn();

        if (error == 0 && (User.containsUpperCase(password) || User.containsUpperCase(confirm))) {
            password = User.getMD5(password);
            confirm = User.getMD5(confirm);
        } else {
            password = "";
            confirm = "";
        }
        return error;
    }

    /**
     * Funció encarregada de revisar si el registre és correcte.
     * @return un codi depenent de l'error. <code>NAME_ERROR</code> si hi ha hagut error al nom. <code>EMAIL_ERROR</code>
     *          si hi ha hagut error al email. <code>PASS_ERROR</code> si n'hi ha hagut a la contrasenya.
     *          Si hi ha hagut error en més d'un camp, els errors es sumen; per exemple si hi ha error al nom i al email,
     *          el codi d'error serà <code>NAME_ERROR</code> + <code>EMAIL_ERROR</code>.
     */
    private int checkSignIn() {
        int error = 0;

        if (userName == null || userName.equals("")) {
            error = NAME_ERROR;
        } else {
            for (int i = 0; i < userName.length(); i++) {
                if (!Character.isLetterOrDigit(userName.charAt(i)) && !(userName.charAt(i) == '_')) {
                    error = NAME_ERROR;
                }
            }
        }

        if (email == null) {
            error += EMAIL_ERROR;
        } else {
            for (int i = 0; i < email.length(); i++) {
                if (!Character.isLetterOrDigit(email.charAt(i)) && !(email.charAt(i) == '@') &&
                        !(email.charAt(i) == '_') && !(email.charAt(i) == '.')) {
                    error += EMAIL_ERROR;
                }
            }

            if (error < 13) {
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
                    error += EMAIL_ERROR;
                }
            }
        }

        if (!password.equals(confirm) || password.length() < MIN_LENGTH) {
            error += PASS_ERROR;
        } else {

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
                error += PASS_ERROR;
            }
        }
        return error;
    }
    
}
