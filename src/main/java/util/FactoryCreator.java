package main.java.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryCreator {
    private EntityManagerFactory factory;

    private static volatile FactoryCreator instance = null;
    private FactoryCreator(){
        this.factory
                = Persistence.createEntityManagerFactory("test");
    }
    public static FactoryCreator getInstance(){
        synchronized (FactoryCreator.class){
            if (instance == null){
                synchronized (FactoryCreator.class){
                    instance = new FactoryCreator();
                }
            }
        }
        return instance;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }
}
