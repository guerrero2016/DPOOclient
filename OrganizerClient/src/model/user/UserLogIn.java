package model.user;

public class UserLogIn extends User {

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
