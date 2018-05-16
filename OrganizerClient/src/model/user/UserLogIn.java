package model.user;

        import java.io.Serializable;

public class UserLogIn extends User {

    private String password;

    public UserLogIn(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean checkLogIn() {
        return true;
        //return DataBaseManager.IniciarSessio(userName, password);
    }

    public String getPassword() {
        return password;
    }
}
