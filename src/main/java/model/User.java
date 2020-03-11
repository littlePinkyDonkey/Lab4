package model;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public User() {
        id = null;
        login = null;
        password = null;
    }
    public User(String login, String password) throws NoSuchAlgorithmException {
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;
        return id.equals(user.id)
                && password.equals(user.password)
                && login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,login,password);
    }
}
