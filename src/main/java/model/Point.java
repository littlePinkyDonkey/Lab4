package model;


import org.json.JSONObject;

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
        double ax = r / 7.0;
        double ay = r / 6.0;

        boolean ellipse = (Math.pow(x, 2)) / (49 * Math.pow(ax, 2)) + (Math.pow(y, 2)) / (9 * Math.pow(ay, 2)) - 1.0 <= 0;
        boolean ellipseBottomX = (Math.abs(x / ax)) >= 4;
        boolean ellipseBottomY = (y / ay >= -3 * Math.sqrt(33) / 7.0) && (y / ay <= 0);
        boolean ellipseTopX = (Math.abs(x / ax)) >= 3;
        boolean ellipseTopY = y >= 0;
        if (ellipse && ellipseBottomX && ellipseBottomY) {
            return true;
        }
        if (ellipse && ellipseTopX && ellipseTopY) {
            return true;
        }

        boolean tail = (((3 * Math.sqrt(33) - 7) * Math.pow(x, 2)) / (-112.0 * Math.pow(ax, 2))
                + Math.abs(x / ax) / 2
                + Math.sqrt(1 - Math.pow(Math.abs((Math.abs(x / ax)) - 2) - 1, 2)) - y / ay - 3) <= 0;
        boolean tailX = (x / ax <= 4) && (x / ax >= -4);
        boolean tailY = (y / ay >= -3) && (y / ay <= 0);
        if (tail && tailX && tailY) {
            return true;
        }

        boolean ear1 = -8 * Math.abs(x / ax) - y / ay + 9 >= 0;
        boolean earsY = y >= 0;
        boolean ear1X = Math.abs(x / ax) <= 1 && Math.abs(x / ax) >= 0.75;
        if (ear1 && earsY && ear1X) {
            return true;
        }

        boolean ear2 = 3 * Math.abs(x / ax) - y / ay + 0.75 >= 0;
        boolean ears2X = Math.abs(x / ax) <= 0.75 && Math.abs(x / ax) >= 0.5;
        if (ear2 && earsY && ears2X) {
            return true;
        }

        boolean head = 9.0 / 4.0 - y / ay >= 0;
        boolean headX = Math.abs(x / ax) <= 0.5;
        boolean headY = y >= 0;
        if (head && headX && headY) {
            return true;
        }

        boolean wings = -Math.abs(x / ax) / 2 - (3.0 / 7.0) * Math.sqrt(10) * Math.sqrt(4 - Math.pow(Math.abs(x / ax) - 1, 2)) - y / ay + (6 * Math.sqrt(10)) / 7.0 + 1.5 >= 0;
        boolean wingsX = Math.abs(x / ax) <= 3 && Math.abs(x / ax) >= 1;
        boolean wingsY = y >= 0;
        if (wings && wingsX && wingsY) {
            return true;
        }

        return false;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("x", x);
        jsonObject.put("y", y);
        jsonObject.put("r", r);
        jsonObject.put("entered", hit);

        return jsonObject;
    }
}