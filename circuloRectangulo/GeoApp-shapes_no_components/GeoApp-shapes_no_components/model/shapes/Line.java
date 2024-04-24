package model.shapes;
import model.Punto;
import model.algorithms.LineAlgorithm;
import model.algorithms.LineBresenham;
import java.util.ArrayList;

/**
 * Write a description of class Line here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Line extends Shape {
    private Punto start, end;
    public Line(Punto start, Punto end, LineAlgorithm algorithm) {
        this.start = start;
        this.end = end;
        this.algorithm = algorithm;
        this.vertexs.add(this.start);
        this.vertexs.add(this.end);
        points = algorithm.generatePoints((int) start.getX(), (int) start.getY(), (int) end.getX(),
            (int) end.getY());
    }
    
    public Line(Punto start, Punto end) {  
        this(start, end, new LineBresenham());
    }
    
    public Punto calculateCenterPoint () {
        int x = (int) ((start.getX() + end.getX())/2);
        int y = (int) ((start.getY() + end.getY())/2);
        return new Punto((double)x,(double)y);
    }
    
    public void recalcular () {
        points = ((LineAlgorithm)algorithm).generatePoints((int) start.getX(), (int) start.getY(), (int) end.getX(),
                (int) end.getY());
    }
    @Override
    public void rotar(double grados) {
        Punto pc = calculateCenterPoint();
        start.rotate(grados, (int) pc.getX(),(int) pc.getY());
        end.rotate(grados, (int) pc.getX(),(int) pc.getY());

    }
  
    public void fill () {}
    
    public void calcularGrosor () {
        for(int i = 1; i < grosor; i++) {
            ArrayList<Punto> up, down;
            up = ((LineAlgorithm)algorithm).generatePoints((int) start.getX(), (int) start.getY() + i, (int) end.getX(),
                (int) end.getY() + i);
            down = ((LineAlgorithm)algorithm).generatePoints((int) start.getX(), (int) start.getY() - i, (int) end.getX(),
                (int) end.getY() - i);
            points.addAll(up);
            points.addAll(down);
        }
    }
    
    @Override
    public String toString () {
        return "Start = " + start + "    End = " + end;
    }
}
