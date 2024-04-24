package view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Write a description of class Header here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Header extends JPanel{
    private Title title;
    private OptionsShapes oA;
    private JPanel containerO;
    
    private ShowShape showShape;
    
    public Header (String textTitle,model.Shape shape) {
        setLayout(new GridLayout(2, 1));
        setBackground(Color.WHITE);
        containerO = new JPanel(new GridLayout(1, 2));
        containerO.setBackground(Color.WHITE);
        
        showShape = new ShowShape();
        //revisar
        String[] s = {"Line", "Circle", "Square", "Triangle"};
        
        oA = new OptionsShapes(s);        
        containerO.add(oA);
        containerO.add(showShape);
        
        title = new Title(textTitle);
        
        add(title);
        add(containerO);
        
        setPreferredSize(new Dimension(0, 66));
    }
    
    public void setTitleAlgorithm (String text) {
        title.setTitle(text);
    } 
    
    public OptionsShapes getOptionsShapes () {
        return oA;
    }
    
    public ShowShape getShowShape () {
        return showShape;
    }
}
