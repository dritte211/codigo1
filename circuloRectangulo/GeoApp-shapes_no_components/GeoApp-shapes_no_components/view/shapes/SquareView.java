package view.shapes;
import model.Punto;
import java.util.ArrayList;
/**
 * Write a description of class Square here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SquareView extends ShapeView{
    private Punto topLeftVertex, bottomRightVertex;
    public SquareView(Punto topLV, Punto bottomRV) {
        super("Square");
        this.topLeftVertex = topLV;
        this.bottomRightVertex = bottomRV;
        shape = new model.shapes.Square(this.topLeftVertex, this.bottomRightVertex);
        generatePixels();
    }
}
