package dao;

import model.Point;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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
        EntityTransaction transaction = manager.getTransaction();
        List<Point> history = null;

        try {
            transaction.begin();
            TypedQuery<Point> query = manager.createQuery("Select e from Point e", Point.class);
            history = query.getResultList();
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            manager.close();
        }
        return history;
    }
}
