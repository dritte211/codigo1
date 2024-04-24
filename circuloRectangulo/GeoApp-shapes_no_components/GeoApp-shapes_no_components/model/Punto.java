package model;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

/**
 * Write a description of class Punto here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Punto extends Point2D.Double {
    public Punto(double x, double y) {
        super(x, y);
    }

    public void rotate (double grados,double xPuntoRotar,double yPuntoRotar) {
        double seno = Math.sin(Math.toRadians(grados));
        double coseno = Math.cos(Math.toRadians(grados));
        double auxX = this.x;
        double auxY = this.y;
        double xvar = (xPuntoRotar + (auxX - xPuntoRotar)*coseno - (auxY - yPuntoRotar)*seno);
        double yvar = (yPuntoRotar + (auxX - xPuntoRotar)*seno + (auxY - yPuntoRotar)*coseno);
        x = xvar;
        y = yvar;
    }
    
    public void traslate (int dx, int dy) {
        double newX = this.x + dx;
        double newY = this.y + dy;
        this.setLocation(newX, newY);
    }
    
    @Override
    public boolean equals (Object o) {
        if(this == o) return true;
        if(o instanceof Punto) {
            Punto otro = (Punto)o;
            return otro.getX() == x && otro.getY() == y;
        }
        return false;
    }
    
    @Override
    public String toString () {
        double xP = (double)Math.round(x * 100d) / 100d;
        double yP = (double)Math.round(y * 100d) / 100d;
        return "("+xP+", "+yP+")";
    }
}
