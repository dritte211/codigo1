package model.shapes;
import model.Punto;
import model.algorithms.TriangleBresenham;
import model.algorithms.TriangleAlgorithm;
/**
 * Write a description of class Triangle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Triangle extends Shape {
    private Punto vertexA, vertexB, vertexC;
    public Triangle(Punto vertexA, Punto vertexB, Punto vertexC, TriangleAlgorithm algorithm) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.vertexC = vertexC;
        this.algorithm = algorithm;
        vertexs.add(this.vertexA);
        vertexs.add(this.vertexB);
        vertexs.add(this.vertexC);
        points = algorithm.generatePoints((int) vertexA.getX(), (int) vertexA.getY(), (int) vertexB.getX(),
        (int) vertexB.getY(), (int) vertexC.getX(),
        (int) vertexC.getY());
    }
    
    public Triangle(Punto vertexA, Punto vertexB, Punto vertexC) {
        this(vertexA, vertexB, vertexC, new TriangleBresenham());
    }
    
    public Punto calculateCenterPoint () {
        double  x = ((vertexA.getX() + vertexB.getX() + vertexC.getX()) / 3);
        double y = ((vertexA.getY() + vertexB.getY() + vertexC.getY()) / 3);
        return new Punto(x, y);
    }
    
    public void recalcular (){
        Punto center = calculateCenterPoint();
        int x1 = (int)(vertexA.getX()*factorEscalacion + center.getX() * (1 - factorEscalacion));
        int y1 = (int)(vertexA.getY()*factorEscalacion + center.getY() * (1 - factorEscalacion));
        
        int x2 = (int)(vertexB.getX()*factorEscalacion + center.getX() * (1 - factorEscalacion));
        int y2 = (int)(vertexB.getY()*factorEscalacion + center.getY() * (1 - factorEscalacion));
        
        int x3 = (int)(vertexC.getX()*factorEscalacion + center.getX() * (1 - factorEscalacion));
        int y3 = (int)(vertexC.getY()*factorEscalacion + center.getY() * (1 - factorEscalacion));
        points = ((TriangleAlgorithm)algorithm).generatePoints(x1, y1, x2, y2, x3, y3);
    }
    
    public void fill () {
        Punto center = calculateCenterPoint();
        int x = (int)center.getX();
        int y = (int)center.getY();
        cuatro_vecinos(x, y);
    }
    
    public void calcularGrosor () {}
    
    @Override
    public void rotar(double grados) {
        Punto c = calculateCenterPoint();
        for(Punto p : vertexs){
            p.rotate(grados, c.x, c.y);
        }
    }
    
    @Override
    public String toString () {
        return "VertexA = " + vertexA + "    VertexB = " + vertexB + "    VertexC = " + vertexC;
    }
}
