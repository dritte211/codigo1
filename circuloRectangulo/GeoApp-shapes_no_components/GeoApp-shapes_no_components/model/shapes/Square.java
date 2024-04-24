package model.shapes;
import model.Punto;

import model.algorithms.SquareBresenham;
import model.algorithms.SquareAlgorithm;

/**
 * Write a description of class Square here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Square extends Shape {
    private Punto topLeftVertex, bottomRightVertex;
    private Punto topRinghtVertex;
    private Punto bottomLeftVertex;
    public Square(Punto topLV, Punto bottomRV, SquareAlgorithm algorithm) {
        this.topLeftVertex = topLV;
        this.bottomRightVertex = bottomRV;
        this.algorithm = algorithm;
        vertexs.add(topLeftVertex);
        vertexs.add(bottomRightVertex);

        vertexs.add(topRinghtVertex =new Punto((double)bottomRV.x,(double)topLV.y));
        vertexs.add(bottomLeftVertex= new Punto((double)topLV.x,(double)bottomRV.y));

        points= algorithm.generatePoints((int)topLeftVertex.getX(), (int) topLeftVertex.getY(),
                                         (int) bottomRightVertex.getX(),(int) bottomRightVertex.getY(),
                                         (int) topRinghtVertex.getX(),(int) topRinghtVertex.getY(),
                                         (int) bottomLeftVertex.getX(),(int) bottomLeftVertex.getY());
    }
    
    public Square(Punto topLV, Punto bottomRV) {
        this(topLV, bottomRV, new SquareBresenham());
    }

    
    public Punto calculateCenterPoint () {
        double x = (topLeftVertex.x + topRinghtVertex.x + bottomLeftVertex.x + bottomRightVertex.x) / 4;
        double y = (topLeftVertex.y + topRinghtVertex.y + bottomLeftVertex.y + bottomRightVertex.y) / 4;
        return new Punto(x, y);
    }
    
    public void recalcular(){
        Punto center = calculateCenterPoint();
        int x1 = (int)(topLeftVertex.getX() * factorEscalacion + center.getX() * (1 - factorEscalacion));
        int y1 = (int)(topLeftVertex.getY() * factorEscalacion + center.getY() * (1 - factorEscalacion));

        int x2 = (int)(bottomRightVertex.getX() * factorEscalacion + center.getX() * (1 - factorEscalacion));
        int y2 = (int)(bottomRightVertex.getY() * factorEscalacion + center.getY() * (1 - factorEscalacion));

        int x3 = (int) (topRinghtVertex.getX()*factorEscalacion + center.getX() *(1-factorEscalacion));
        int y3 = (int) (topRinghtVertex.getY()*factorEscalacion + center.getY() *(1-factorEscalacion));

        int x4 = (int)(bottomLeftVertex.getX()*factorEscalacion + center.getX() *(1-factorEscalacion));
        int y4 = (int)(bottomLeftVertex.getY()*factorEscalacion + center.getY() *(1-factorEscalacion));
        points = ((SquareAlgorithm)algorithm).generatePoints(x1, y1, x2, y2,x3,y3,x4,y4);
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
        return "TopLeftVertex = " + topLeftVertex.toString() + "    BottomRightVertex = " + bottomRightVertex.toString();
    }
}
