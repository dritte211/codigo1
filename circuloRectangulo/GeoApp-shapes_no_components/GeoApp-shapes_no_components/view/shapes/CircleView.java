package view.shapes;

import model.Punto;
import java.util.ArrayList;

import model.algorithms.CircleAlgorithm;
import model.algorithms.CircleMidPoint;

public class CircleView extends ShapeView {
    private Punto center;
    private int radio;
    public CircleView(Punto center, int radio) {
        super("Circle");
        this.center = center;
        this.radio = radio;
        shape = new model.shapes.Circle(this.center, this.radio);
        generatePixels();
    }
}
