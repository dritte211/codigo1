package controller;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;


/**
 * Write a description of class ControllerAttributes here.
 * @author (your name)
 * @version (a version number or a date)
 */
public class ControllerAttributes extends KeyAdapter implements ActionListener, ItemListener, ChangeListener { 
    private JButton up, down, left, right;
    private view.Plane plane;
    private view.OptionsAttributes opsAttrib;
    private JCheckBox fill;
    private JComboBox escalar;
    private JButton colorButton;
    private JColorChooser colorChooser;
    private JPopupMenu popupColor;
    private JButton rotar;
    private JTextField grados;
    
    private JComboBox grosor;
    private JComboBox lineStyle;
    
    private view.shapes.ShapeView viewShape;
    private model.shapes.Shape modelShape;
    
    private view.ShowShape showShape;
    
    public ControllerAttributes (view.App app) {
        this.plane = app.getPlane();
        opsAttrib = app.getOpsAttributes();
        showShape = app.getHeader().getShowShape();
        
        fill = opsAttrib.getFillCheck();
        escalar = opsAttrib.getEscala();
        colorButton = opsAttrib.getColorButton();
        colorChooser = opsAttrib.getColorChooser();
        popupColor = opsAttrib.getPopupColor();
        
        up = opsAttrib.getUpDirecciontion();
        down = opsAttrib.getDownDirecciontion();
        left = opsAttrib.getLelftDirecciontion();
        right = opsAttrib.getRightDirecciontion();
        up.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);
        left.addActionListener(this);
        
        escalar.addActionListener(this);
        fill.addItemListener(this);
        colorButton.addActionListener(this);
        colorChooser.getSelectionModel().addChangeListener(this);
        
        rotar = opsAttrib.getRotar();
        grados = opsAttrib.getGrados();
        rotar.addActionListener(this);

        grosor = opsAttrib.getGrosor();
        grosor.addActionListener(this);
        
        lineStyle = opsAttrib.getLineStyle();
        lineStyle.addActionListener(this);
        
        this.plane.addKeyListener(this);
    }
    
    private void updateCurrentShape () {
        viewShape = plane.getCurrentShape();
        modelShape = viewShape.getShape();
    }
    
    private void updateUIPlane () {
        modelShape.update();
        viewShape.update();
        showShape.updateShape();
        plane.updateUI();
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        Object src = e.getSource();
        if (plane.getCurrentShape() != null) {
            updateCurrentShape();
            if(src.equals(escalar)) {
                double s = (double)(escalar.getSelectedItem());
                modelShape.escalar(s);
            } else if (src.equals(colorButton)) {
                popupColor.show(colorButton, 0, colorButton.getHeight());
            } else if(src.equals(rotar)) {
                double g = Double.parseDouble(grados.getText());
                modelShape.rotar(g);
            } else if(src.equals(grosor)) {
                int g = (int)(grosor.getSelectedItem());
                modelShape.setGrosor(g);
            } else if(src.equals(lineStyle)) {
                String style = (String)lineStyle.getSelectedItem();
                if (style.equals("Continua")) {
                    modelShape.setTipoTrazado(model.shapes.TipoTrazado.CONTINUO);
                } else if(style.equals("Segmentada")) {
                    modelShape.setTipoTrazado(model.shapes.TipoTrazado.SEGMENTADO);
                } else {
                    modelShape.setTipoTrazado(model.shapes.TipoTrazado.PUNTEADO);
                }
            }
            eventMoved(src);
            updateUIPlane();
        }
        plane.requestFocus();
    }
    
    @Override
    public void itemStateChanged (ItemEvent itemEvent) {
        Object src = itemEvent.getSource();
        if (plane.getCurrentShape() != null) {
            updateCurrentShape();
            if (src.equals(fill)) {
                if(itemEvent.getStateChange() == 1) {
                    modelShape.setFill(true);
                }else {
                    modelShape.setFill(false);
                }    
            }
            updateUIPlane();
        }
        plane.requestFocus();
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        Color color = colorChooser.getColor();
        colorButton.setBackground(color);
        if(plane.getCurrentShape() != null) {
            updateCurrentShape();
            modelShape.setColor(color);
            updateUIPlane();
        }
        plane.requestFocus();
    }
    
    private void eventMoved (Object src) {
        if(src.equals(up)) {
            modelShape.trasladar(0, 1);
        }else if(src.equals(down)) {
            modelShape.trasladar(0,-1);
        }else if(src.equals(left)) {
            modelShape.trasladar(-1, 0);
        }else if(src.equals(right)) {
            modelShape.trasladar(1, 0);
        }        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(plane.getCurrentShape() != null) {
            updateCurrentShape();
            if(keyCode == KeyEvent.VK_UP) {
                modelShape.trasladar(0, 1);
            }else if(keyCode == KeyEvent.VK_DOWN){
                modelShape.trasladar(0,-1);
            }else if(keyCode == KeyEvent.VK_LEFT){
                modelShape.trasladar(-1, 0);
            }else if(keyCode == KeyEvent.VK_RIGHT){
                modelShape.trasladar(1, 0);
            }
            updateUIPlane();
        }
    }
}
