package model.user;

//import model.DataBaseManager;

import java.io.Serializable;

public class UserLogIn implements Serializable{
        private String userName;
        private String password;

    public UserLogIn(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean checkLogIn() {
        return false;
        //return DataBaseManager.IniciarSessio(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
