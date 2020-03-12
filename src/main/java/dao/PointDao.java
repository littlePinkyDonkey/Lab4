package dao;

import model.Point;
import util.FactoryCreator;

import javax.persistence.*;
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
            manager.flush();

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
