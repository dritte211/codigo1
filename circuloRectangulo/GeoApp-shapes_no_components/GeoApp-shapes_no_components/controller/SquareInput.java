package controller;
import model.Punto;
import java.awt.Color;
import view.GraphicSquare;
import view.GraphicLine;
import view.GraphicsShape;
import view.shapes.ShapeView;
import view.shapes.SquareView;


/**
 * Write a description of class SquareInput here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SquareInput extends InputShape {
    private Punto topLeftVertex, bottomRightVertex;
    
    public void setInputs () {
        if(complete()) {
            topLeftVertex = inputs.get(0);
            bottomRightVertex = inputs.get(1);
        }
    }
    
    public boolean complete () {
        return inputs.size() == 2;
    }
    
    public boolean readyDraw () {
        return inputs.size() >= 1;
    }
    
    public ShapeView getShape () {
        if(topLeftVertex != null && bottomRightVertex != null) return new SquareView(topLeftVertex, bottomRightVertex);
        return null;
    }
    
    public GraphicsShape getGraphicsShape () {
        if(complete()) {
            int width = (int)bottomRightVertex.getX() - (int)topLeftVertex.getX();
            int height = (int)topLeftVertex.getY() - (int)bottomRightVertex.getY();
            return new GraphicSquare(0, 0, Color.RED, (int)topLeftVertex.getX(), (int)topLeftVertex.getY(), width, height);
        }
        return null;
    }
}
