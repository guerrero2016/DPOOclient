package model.user;

public class UserLogIn extends User {
    public final static int serialVersionUID = 1239;
    private String password;

    public UserLogIn(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean checkLogIn() {
        return userName != null && password != null;
    }

    public String getPassword() {
        return password;
    }
}
