package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "points")
@Table(schema = "web")
@NamedQuery(name = "getPointByOwner", query = "from points p where p.owner = :owner")
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

    @Column(name = "OWNER", nullable = false)
    private String owner;

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
    public String getOwner(){
        return owner;
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
    public void setOwner(String owner){
        this.owner = owner;
    }
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean checkHit() {
        return true;
    }
}