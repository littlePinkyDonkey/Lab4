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

    public void addPoint(Point point){
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(point);
            manager.flush();
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            manager.close();
        }
    }

    public List<Point> getAllPoints(){
        EntityManager manager = factory.createEntityManager();

        return manager.createQuery("from points", Point.class).getResultList();
    }
}
