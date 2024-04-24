package view;
import javax.swing.*;
import java.awt.*;


/**
 * Write a description of class ControlsAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlsAnimation extends JPanel {
    private JButton clear, delete;
    public ControlsAnimation () {
        setPreferredSize(new Dimension(0, 40));
        setBackground(Color.WHITE);
        delete = new JButton("Delete Current Shape");
        clear = new JButton("Clear");
        
        delete.setBackground(new Color(6, 74, 203));
        clear.setBackground(new Color(6, 74, 203));
        
        delete.setForeground(Color.WHITE);
        clear.setForeground(Color.WHITE);
        
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        add(delete);
        add(clear);
    }
    
    public JButton clear () {
        return clear;
    }
    
    public JButton delete () {
        return delete;
    }
}
