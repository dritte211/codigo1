package view.shapes;
import model.Punto;
import java.util.ArrayList;

/**
 * Write a description of class Square here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TriangleView extends ShapeView{
    private Punto vertexA, vertexB, vertexC;
    public TriangleView(Punto vertexA, Punto vertexB, Punto vertexC) {
        super("Triangle");
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.vertexC = vertexC;
        shape = new model.shapes.Triangle(this.vertexA, this.vertexB, this.vertexC);
        generatePixels();
    }
}
