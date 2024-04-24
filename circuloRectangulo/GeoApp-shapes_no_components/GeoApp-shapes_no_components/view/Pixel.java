package view;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Cursor;
import java.awt.Graphics2D;

public class Pixel {
    private int width;
    private double x, y;
    private Color color;
    private boolean isSelected;
    private view.shapes.ShapeView parentShape;
    public Pixel(double x, double y, int width, Color color){
        this.width = width;
        this.x = x;
        this.y = y;
        this.color = color;
        isSelected = false;
        parentShape = null;
    }
    
    public Pixel(double x, double y, int width){
        this(x, y, width, Color.BLACK);
    }
    
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setParentShape (view.shapes.ShapeView pS) {
        parentShape = pS;
    }
    
    public view.shapes.ShapeView getParentShape () {
        return parentShape;
    }
    
    public double getXPlane () {
        return x;
    }
    
    public double getYPlane () {
        return y;
    }
    
    public void setColor (Color color) {
        this.color = color;
    }
    
    public void paintFill(Graphics g) {
        g.setColor(color);
        g.fillRect((int)(x*width), (int)(y*width), width, width);
    }
    
    public void select () {
        isSelected = true;
    }
    
    public void unselect () {
        isSelected = false;
    }
    
    public void paint(Graphics g) {
        if (isSelected) {
            Color selectedColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 128);
            g.setColor(selectedColor);
            g.fillRect((int)(x*width-1), (int)(y*width-1), width+3, width+3);
        }

        g.setColor(color);
        g.fillRect((int)(x*width), (int)(y*width), width, width);
    }
    
    public boolean contiene(int x, int y) {
        return x >= this.x*width && x <= this.x*width + this.width &&
               y >= this.y*width && y <= this.y*width + this.width;
    }
    
    @Override
    public boolean equals (Object o) {
        if(this == o) return true;
        if(o instanceof Pixel) {
            Pixel otro = (Pixel)o;
            return otro.getX() == x && otro.getY() == y;
        }
        return false;
    }
    
    @Override
    public String toString () {
        return "("+x+", "+y+")";
    }
}
