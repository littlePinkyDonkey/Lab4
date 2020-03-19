package main.java.service;

import main.java.dao.PointDao;
import main.java.model.Point;

import java.util.List;

public class PointService {
    private PointDao dao;

    public PointService(){
        this.dao = new PointDao();
    }

    public boolean addPoint(Point point){
        return dao.addPoint(point);
    }

    public List<Point> getPointsByOwner(String owner){
        return dao.getPointsByOwner(owner);
    }
}
