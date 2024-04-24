package view.shapes;

import model.Punto;
import java.util.ArrayList;

import model.algorithms.LineAlgorithm;
import model.algorithms.LineBresenham;

public class LineView extends ShapeView {
  public LineView(Punto start, Punto end) {
    super("Line");
    shape = new model.shapes.Line(start, end);
    generatePixels();
  }
}
