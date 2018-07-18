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

    /**
     * Constructor a partir de diversos parameters
     * @param userName Nom d'usuari
     * @param email Email
     * @param password Contrasenya
     * @param confirm Confirmacio
     */
    public UserRegister(String userName, String email, String password, String confirm) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
    }

    /**
     * Funcio encargada d'encriptar la contrasenya
     * @return Si retorna 0 s'ha encriptat correctament, sino ha hagut algun error
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
     * Funcio encarregada de revisar si el registre es correcte
     * @return Codi d'error: <code>NAME_ERROR</code> si ha hagut error al nom; <code>EMAIL_ERROR</code>
     *          si ha hagut error a l'email; <code>PASS_ERROR</code> si ha hagut error a la contrasenya;
     *          si ha hagut error en més d'un camp.
     *          Els errors es sumen. Per exemple si hi ha error al nom i a l'email,
     *          el codi d'error sera <code>NAME_ERROR</code> + <code>EMAIL_ERROR</code>.
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