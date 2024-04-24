package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import model.Punto;

/**
 * Write a description of class Ventana here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class App extends JFrame {
    private final int LY = Constants.LY;
    private final int LX = Constants.LX;
    
    private model.Shape shape;
    
    private Plane plane;
    private Header header;
    private ControlsAnimation crtAnimation;
    private OptionsAttributes opsAttributes;

    public App () {
        super("GeoApp");
        setLayout(new BorderLayout(10,10));
        getContentPane().setBackground(Color.WHITE);
        
        shape = new model.LineShape();
        
        header = new Header("Graficacion :)", shape);
        plane = new Plane();
        crtAnimation = new ControlsAnimation();
        opsAttributes = new OptionsAttributes();
        
        add(header, BorderLayout.NORTH);
        add(plane, BorderLayout.CENTER);
        add(crtAnimation, BorderLayout.SOUTH);
        add(new Border(), BorderLayout.EAST);
        add(opsAttributes, BorderLayout.WEST);
        
        int w = (Constants.LX*Constants.GRID_SCALE+1) + 40+200 + 10+10 + (14);
        int h = (Constants.LY*Constants.GRID_SCALE+1) + 66 + (10+10) + 40 + 37;
        //1267x696
        setBounds(0, 0, w, h);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void setShape (model.Shape shape) {
        this.shape = shape;
    }
    
    public OptionsAttributes getOpsAttributes () {
        return opsAttributes;
    }
    
    public Plane getPlane () {
        return plane;
    }
    
    public Header getHeader () {
        return header;
    }
    
    public model.Shape getModelShape () {
        return shape;
    }
    
    public ControlsAnimation getCrtAnimation () {
        return crtAnimation;
    }
    
    public static void main(String[] args) {
        new App();
    }
}
