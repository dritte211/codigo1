package view;
import javax.swing.*;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Color;


/**
 * Write a description of class OptionsAlgorithm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OptionsShapes extends JPanel {
    private JComboBox optionsShape;
    
    public OptionsShapes (String[] shapes) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        setBackground(Color.WHITE);
        optionsShape = new JComboBox();
        
        createItems(shapes);
        
        add(optionsShape);
    }
    
    private void createItems (String[] shapes) {
        for (String s : shapes) {
            optionsShape.addItem(s);
        }
    }
    
    public JComboBox getOptionsShape () {
        return optionsShape;
    }
}
