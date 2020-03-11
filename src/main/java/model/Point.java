package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "points")
@Table(schema = "web")
public class Point implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "X", nullable = false)
    private double x;

    @Column(name = "Y", nullable = false)
    private double y;

    @Column(name = "R", nullable = false)
    private double r;

    @Column(name = "HIT", nullable = false)
    private boolean hit;

    public Point() {
    }
    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Point(double x, double y, double r, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
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

    @Override
    public String toString() {
        return x + " " + y + " " + r;
    }
}