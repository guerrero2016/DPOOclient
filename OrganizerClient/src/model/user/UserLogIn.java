package model.user;

/**
 * Representació d'un usuari quan fa login
 */
public class UserLogIn extends User {
    public final static int serialVersionUID = 1239;
    private String password;

    public UserLogIn(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Comprovacio de que el login es correcte
     * @return Retorna cert si les dades son correctes, fals si son incorrectes
     */
    public boolean checkLogIn() {
        return userName != null && password != null;
    }

    public String getPassword() {
        return password;
    }
}
