package ModelAEliminar;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return Objects.equals(name, user.name) && Objects.equals(email, user.email);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

}
