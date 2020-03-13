package service;

import dao.UserDao;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    private UserDao dao;

    private final byte[] PEPPER = "#/<&&>[421fSS]@!".getBytes();
    private final int SALT_LENGTH = 12;
    private final String PASSWORD_HASH_ALGORITHM = "SHA-1";

    public UserService(){
        this.dao = new UserDao();
    }

    public User getUserByLogin(String login){
        return dao.getUserByLogin(login);
    }

    public boolean addUser(User user){
        String salt = RandomStringUtils.random(SALT_LENGTH,true,true);
        String passwordHash = hash(user.getPassword(),salt);

        user.setSalt(salt);
        user.setPasswordHash(passwordHash);

        return dao.addUser(user);
    }

    public boolean checkAccess(User user, String password){
        return user.getPasswordHash().equals(hash(password,user.getSalt()));
    }

    private String hash(String password, String salt){
        MessageDigest messageDigest;
        try {
             messageDigest = MessageDigest.getInstance(PASSWORD_HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        messageDigest.update(PEPPER);
        messageDigest.update(password.getBytes());
        messageDigest.update(salt.getBytes());
        byte[] digest = messageDigest.digest();

        StringBuilder passwordHash = new StringBuilder();
        for (byte b : digest)
            passwordHash.append(String.format("%02x",b));

        return passwordHash.toString();
    }
}
