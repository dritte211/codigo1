package controller;
import model.Punto;
import java.awt.Color;
import view.GraphicCircle;
import view.GraphicsShape;
import view.shapes.ShapeView;
import view.shapes.CircleView;

public class CircleInput extends InputShape {
    private Punto center;
    private Integer radio;
    
    public void setInputs () {
        if(complete()) {
            center = inputs.get(0);
            radio = (int)center.distance(inputs.get(1));
        }
    }
    
    public boolean complete () {
        return inputs.size() == 2;
    }
    
    public boolean readyDraw () {
        return inputs.size() >= 1;
    }
    
    public ShapeView getShape () {
        if(center != null && radio != null) return new CircleView(center, radio);
        return null;
    }
    
    public GraphicsShape getGraphicsShape () {
        if(complete()) return new GraphicCircle(0, 0, Color.RED, (int)center.getX(), (int)center.getY(), radio);
        return null;
    }
}
