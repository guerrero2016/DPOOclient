package Model.user;

import java.io.Serializable;

public class UserLogIn extends User implements Serializable{

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
