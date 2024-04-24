package view;
import javax.swing.*;
import java.awt.Color;


/**
 * Write a description of class ShowShapes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShowShape extends JPanel {
    private model.shapes.Shape shape;
    
    public ShowShape () {
        setBackground(Color.WHITE);
    }
    
    public void setShape (model.shapes.Shape shape) {
        this.shape = shape;
    }
    
    public void updateShape () {
        removeAll();
        JLabel label = new JLabel(shape.toString());
        add(label);
        updateUI();
    }
}
