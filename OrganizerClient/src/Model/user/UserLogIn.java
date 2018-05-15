package Model.user;

//import model.DataBaseManager;

import java.io.Serializable;

public class UserLogIn extends User implements Serializable{

    private String password;

    public boolean checkLogIn() {
        return false;
        //return DataBaseManager.IniciarSessio(userName, password);
    }

    public String getPassword() {
        return password;
    }

}
