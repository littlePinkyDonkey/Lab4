package main.java.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "users")
@Table(schema = "web")
@NamedQuery(name = "getUserByLogin", query = "from users where login=:login")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Transient
    private String password;
    
    @Column(name = "SALT", nullable = false)
    private String salt;

    public User() {
        id = null;
        login = null;
        passwordHash = null;
    }
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public String getPassword(){
        return password;
    }
    public String getSalt(){
        return salt;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setSalt(String salt){
        this.salt = salt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;
        return id.equals(user.id)
                && passwordHash.equals(user.passwordHash)
                && login.equals(user.login) 
                && salt.equals(user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,login, passwordHash, salt);
    }
}
