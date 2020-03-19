package main.java.dao;

import main.java.model.Point;
import main.java.util.FactoryCreator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PointDao {
    private EntityManagerFactory factory;

    public PointDao(){
        this.factory = FactoryCreator.getInstance().getFactory();
    }

    public boolean addPoint(Point point){
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            manager.persist(point);

            transaction.commit();

            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            manager.close();
        }
    }

    public List<Point> getPointsByOwner(String owner)   {
        EntityManager manager = factory.createEntityManager();

        return manager
                .createNamedQuery("getPointByOwner", Point.class)
                .setParameter("owner",owner)
                .getResultList();
    }
}
