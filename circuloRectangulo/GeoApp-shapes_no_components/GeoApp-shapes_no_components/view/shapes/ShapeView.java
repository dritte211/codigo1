package view.shapes;

import model.Punto;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import model.algorithms.Algorithm;
import view.Constants;
import view.Pixel;


public abstract class ShapeView {
    protected ArrayList<Pixel> pixels;
    protected model.shapes.Shape shape;
    protected String name;
    
    protected boolean isSelected;

    public ShapeView(String name) {
        this.name = name;
        isSelected = false;
        pixels = new ArrayList<Pixel>();
    }
    
    public String getName () {
        return name;
    }
    
    public ArrayList<Pixel> getPixels() {
        return pixels;
    }
    
    public model.shapes.Shape getShape () {
        return shape;
    }
    
    public void update () {
        pixels.clear();
        generatePixels();
    }
    
    protected void generatePixels() {
        shape.getPoints().forEach((point)->{
            int x = (int) point.getX();
            int y = (int) point.getY();
            int mX = Constants.LX / 2;
            int mY = Constants.LY / 2;
            Pixel pixel = new Pixel((double)x+mX, (double)-y+mY, Constants.GRID_SCALE, shape.getColor());
            if(isSelected) pixel.select();
            pixels.add(pixel);
        });
    }
    
    public boolean contiene (int x, int y) {
        for(Pixel p : pixels) {
            if (p.contiene(x, y)) return true;
        }
        return false;
    }
    
    public boolean isSelected () {
        return isSelected;
    }
  
    public void select () {
        isSelected = true;
        pixels.forEach((pixel)->pixel.select());    
    }
    
    public void unselect () {
        isSelected = false;
        pixels.forEach((pixel)->pixel.unselect());
    }
    
    public void paint (Graphics g) {
        pixels.forEach((pixel)->{pixel.paint(g);});
    }
}
