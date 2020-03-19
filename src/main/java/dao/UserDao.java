package main.java.dao;


import main.java.model.User;
import main.java.util.FactoryCreator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class UserDao {
    EntityManagerFactory factory;

    public UserDao(){
        this.factory = FactoryCreator.getInstance().getFactory();
    }

    public User getUserByLogin(String login){
        EntityManager manager = factory.createEntityManager();

        return manager
                .createNamedQuery("getUserByLogin",User.class)
                .setParameter("login",login)
                .getResultStream()
                .findAny()
                .orElse(null);
    }

    public boolean addUser(User user){
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            manager.persist(user);
            manager.flush();

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            manager.close();
        }
    }
}
