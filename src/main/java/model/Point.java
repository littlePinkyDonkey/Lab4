package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Point implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "X")
    private double x;

    @Column(name = "Y")
    private double y;

    @Column(name = "R")
    private double r;

    @Column(name = "HIT")
    private boolean hit;

    public Point() {
    }
    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public long getId() {
        return id;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getR() {
        return r;
    }
    public boolean isHit() {
        return hit;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setR(double r) {
        this.r = r;
    }
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean checkHit() {
        return true;
    }
}