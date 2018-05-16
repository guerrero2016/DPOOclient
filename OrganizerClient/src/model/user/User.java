package model.user;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class User implements Serializable {

    protected String userName;

    public User() {}

    public User(String userName) {
        if(userName != null) {
            this.userName = userName;
        }
    }

    public String getUserName() {
        return userName;
    }

    public static String getMD5(String entry) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(entry.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(userName, user.userName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

}