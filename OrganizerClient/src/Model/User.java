package Model;

public class User {

    private String name;
    private String email;

    public User(String name, String email) {

        if(name != null) {
            this.name = name.toString();
        }

        if(email != null) {
            this.email = email.toString();
        }

    }

    public User(User user) {

        if(user.name != null) {
            name = user.name.toString();
        }

        if(user.email != null) {
            email = user.email.toString();
        }

    }

    public String getName() {

        if (name != null) {
            return name.toString();
        }

        return null;

    }

    public void setName(String name) {
        if(name != null) {
            this.name = name.toString();
        }
    }

    public String getEmail() {

        if(email != null) {
            return email.toString();
        }

        return null;

    }
    public void setEmail(String email) {
        if(email != null) {
            this.email = email.toString();
        }
    }

}
