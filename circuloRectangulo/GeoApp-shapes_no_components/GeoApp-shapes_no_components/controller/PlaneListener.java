package controller;
import java.awt.event.*;
import java.awt.*;
import view.GraphicsShape;
import model.Punto;

/**
 * Write a description of class PlaneListener here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlaneListener extends MouseAdapter {
    private view.Plane plane;
    private view.App app;
    private InputShape inputShape;
    private view.OptionsAttributes opsAttrib;
    private view.ShowShape showShape;
    
    public PlaneListener (view.App app) {
        this.app = app;
        this.plane = this.app.getPlane();
        this.opsAttrib = this.app.getOpsAttributes();
        this.showShape = this.app.getHeader().getShowShape();
        this.plane.addMouseListener(this);
        this.plane.addMouseMotionListener(this);
    }
    
    public void mouseMoved(MouseEvent e) {
        int xc = plane.getWidth() / 2;
        int yc = plane.getHeight() / 2;
        int x = e.getX()/view.Constants.GRID_SCALE-view.Constants.LX/2;
        int y = -(e.getY()/view.Constants.GRID_SCALE-view.Constants.LY/2);
        
        if (inputShape != null && inputShape.readyDraw()) {
            view.Pixel topPixel = plane.peekPixelOrigen();
            if (plane.sizePixelesOrigen() > inputShape.minCantPoints() &&
                (topPixel.getX() != x || topPixel.getY() != y)) {
                plane.popPixelOrigen();
            }
            plane.pushPixelOrigen(x, y);
            if(inputShape.size() > inputShape.minCantPoints()) inputShape.popInput();
            inputShape.addInput(new Punto(x, y));
            inputShape.setInputs();
            updateGraphicShape();
            plane.repaint();
        }
    }
    
    private void updateAttributes (model.shapes.Shape s) {
        opsAttrib.getFillCheck().setSelected(s.getFill());
        opsAttrib.getColorChooser().setColor(s.getColor());
        opsAttrib.getColorButton().setBackground(s.getColor());
        String lineS = "";
        if (s.getTipoTrazado() == model.shapes.TipoTrazado.CONTINUO){
            lineS = "Continua";
        } else if (s.getTipoTrazado() == model.shapes.TipoTrazado.SEGMENTADO) {
            lineS = "Segmentada";
        } else {
            lineS = "Punteada";
        }
        opsAttrib.getLineStyle().setSelectedItem(lineS);
    }
    
    public void mouseClicked(MouseEvent e) {
        int xc = plane.getWidth() / 2;
        int yc = plane.getHeight() / 2;
        int x = (e.getX()/view.Constants.GRID_SCALE) - (view.Constants.LX/2);
        int y = -(e.getY()/view.Constants.GRID_SCALE-view.Constants.LY/2);
        //System.out.println(x+" "+y);
        view.shapes.ShapeView s = plane.verificarPixelPulsado(e.getX(), e.getY());
        if(s != null) {
            plane.setCurrentShape(s);
            updateAttributes(s.getShape());
            showShape.setShape(s.getShape());
            showShape.updateShape();
            plane.requestFocus();
            return;
        }
        else {
            if(plane.getCurrentShape() != null) {
                plane.getCurrentShape().unselect();
                plane.setCurrentShape(null);
            }
        }
        
        if(inputShape == null) {
            plane.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if (app.getModelShape() instanceof model.LineShape) {
                inputShape = new LineInput();
            } else if (app.getModelShape() instanceof model.CircleShape) {
                inputShape = new CircleInput();
            } else if (app.getModelShape() instanceof model.SquareShape) {
                inputShape = new SquareInput();
            } else if (app.getModelShape() instanceof model.TriangleShape) {
                inputShape = new TriangleInput();
            }
        }
        
        Punto p = new Punto((double)x, (double)y);
        inputShape.addInput(p);
        plane.pushPixelOrigen((int)p.getX(), (int)p.getY());
        
        if (inputShape.complete()) {
            inputShape.setInputs();
            updateGraphicShape();
            updateUIShape();
            
            plane.clearPixelesOrigen();
            inputShape.clearInputs();
            
            plane.repaint();
            inputShape = null;
            plane.setGraphic(null);
            plane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    private void updateUIShape () {
        view.shapes.ShapeView s = inputShape.getShape();
        plane.addShape(s);
        showShape.setShape(s.getShape());
        showShape.updateShape();
        plane.repaint();
    }
    
    private void updateGraphicShape () {
        int xc = plane.getWidth() / 2;
        int yc = plane.getHeight() / 2;
        
        GraphicsShape g = inputShape.getGraphicsShape();
        if (g!=null) {
            g.setCenter(xc, yc);
            plane.setGraphic(g);
        }
    }
}
