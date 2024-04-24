package view;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.event.ComponentEvent;
import model.Punto;

public class Plane extends JPanel
{
    final int LY = Constants.LY;
    final int LX = Constants.LX;
    int GRID_SCALE = Constants.GRID_SCALE;
    private Stack<Pixel> pixelesOrigen;
    private GraphicsShape graphic;
    
    private view.shapes.ShapeView currentShape;
    private ArrayList<view.shapes.ShapeView> shapes;
    
    private int currentShapeIndex;
    
    public Plane () {
        shapes = new ArrayList<view.shapes.ShapeView>();
        pixelesOrigen = new Stack<Pixel>();
        currentShapeIndex = 0;
        setPreferredSize(new Dimension(GRID_SCALE*LX+1, GRID_SCALE*LY+1));
        setBackground(new Color(250, 250, 250));
    }
    
    public void addShape (view.shapes.ShapeView s) {
        shapes.add(s);
    }
    
    public void addShape (view.shapes.ShapeView s, int index) {
        shapes.add(index, s);
    }
    
    public ArrayList<view.shapes.ShapeView> getShapes () {
        return shapes;
    }
    
    public void removeAllShapes () {
        shapes.clear();
    }
    
    public void setCurrentShape (view.shapes.ShapeView s) {
        if (currentShape != null) currentShape.unselect(); 
        currentShape = s;
    }
    
    public view.shapes.ShapeView getCurrentShape () {
        return currentShape;
    }
    
    public view.shapes.ShapeView getShape (int index) {
        currentShapeIndex = index;
        return shapes.get(index);
    }
    
    public int getCurrentShapeIndex(){
        return currentShapeIndex;
    }
    
    public void removeCurrentShape () {
        if (currentShape != null) {
            shapes.remove(currentShape);
        }
    }
    
    public void clearPoints () {
        //repaint();
    }
    
    public void setGraphic (GraphicsShape g) {
        this.graphic = g;
    }
    
    public GraphicsShape getGraphicShape() {
        return graphic;
    }
    
    public void setPoints (ArrayList<Punto> points) {
        repaint();
    }
    
    public view.shapes.ShapeView verificarPixelPulsado(int x, int y) {
        for(view.shapes.ShapeView s : shapes ) {
            if(s.contiene(x, y)) {
                if(!s.isSelected()) {
                    s.select();
                    repaint();
                }
                return s;
            }
        }
        return null;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrilla(g);
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;
        
        g.setColor(Color.BLACK);
        g.drawLine(0, centroY, getWidth(), centroY);
        g.drawLine(centroX, 0, centroX, getHeight());
        shapes.forEach((s)->s.paint(g));
        pixelesOrigen.forEach((pixel) -> pixel.paintFill(g));
        if (graphic != null) graphic.paint((Graphics2D)g);
    }
    
    public void paintGrilla(Graphics g) {
        g.setColor(new Color(117, 117, 117));
        for (int i = 0; i < LX; i++) {
            for (int j = 0; j < LY; j++) {
                g.drawRect(i*GRID_SCALE, j*GRID_SCALE,GRID_SCALE, GRID_SCALE);
            }
        }
    }
    
    public void paintPixel (int x, int y) {
        Graphics g = getGraphics();
        int mX = LX / 2;
        int mY = LY / 2;
        g.setColor(Color.GREEN);
        g.drawRect(x+mX-1, -y+mY-1, GRID_SCALE, GRID_SCALE);
    }
    
    public void pushPixelOrigen (int x, int y) {
        int mX = LX / 2;
        int mY = LY / 2;
        pixelesOrigen.push(new Pixel(x+mX, -y+mY, GRID_SCALE, Color.RED));
        repaint();
    }
    
    public void popPixelOrigen () {
        pixelesOrigen.pop();
        repaint();
    }
    
    public Pixel peekPixelOrigen () {
        return pixelesOrigen.peek();
    }
    
    public void clearPixelesOrigen () {
        pixelesOrigen = new Stack<Pixel>();
    }
    
    public int sizePixelesOrigen () {
        return pixelesOrigen.size();
    }
}

