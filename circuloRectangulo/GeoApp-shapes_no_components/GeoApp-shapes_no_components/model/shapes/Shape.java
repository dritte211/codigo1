package model.shapes;
import model.Punto;
import java.util.ArrayList;
import java.awt.Color;
import model.algorithms.Algorithm;
import java.util.Stack;

public abstract class Shape {
    protected ArrayList<Punto> points;
    protected ArrayList<Punto> vertexs;
    protected int grosor;
    protected Color color;
    protected boolean rellenado;
    protected TipoTrazado tipoTrazado;
    protected double factorEscalacion;
    
    protected Algorithm algorithm;
    
    public Shape () {
        points = new ArrayList<Punto>();
        vertexs = new ArrayList<Punto>();
        grosor = 1;
        factorEscalacion = 1;
        color = Color.BLACK;
        tipoTrazado = TipoTrazado.CONTINUO;
        rellenado = false;
    }
    
    public void setFill (boolean f) {
        rellenado = f;
    }
    public void setColor (Color color) {
        this.color = color;
    }
    public void setTipoTrazado (TipoTrazado tipo) {
        tipoTrazado = tipo;
    }
    public void setGrosor (int t) {
        grosor = t;
    }
    
    public TipoTrazado getTipoTrazado () {
        return tipoTrazado;
    }
    public boolean getFill () {
        return rellenado;
    }
    public Color getColor () {
        return color;
    }
    public ArrayList<Punto> getPoints () {
        return points;
    }
    public ArrayList<Punto> getVertexs () {
        return vertexs;
    }

    public void trasladar (int dx, int dy) {
        for (Punto punto : vertexs) {
            punto.traslate(dx, dy);
        }
    }
    
    public void rotar (double grados) {
        Punto c = calculateCenterPoint();
        for(Punto p : vertexs) {
            p.rotate(grados, c.x, c.y);
        }
    }
    
    /*
           Actualizar la lista points
       */
    public abstract void calcularGrosor ();
    
    public void calcularTipoTrazado () {
        //Actualizar la lista points
        ArrayList<Punto> newPoints = new ArrayList<Punto>();
        if(tipoTrazado == TipoTrazado.PUNTEADO) {
            for(int i = 0; i < points.size(); i+=2) {
                newPoints.add(new Punto(points.get(i).x, points.get(i).y));
            }
            points = newPoints;
        } else if (tipoTrazado == TipoTrazado.SEGMENTADO) {
            for(int i = 0; i < points.size(); i+=4) {
                newPoints.add(new Punto(points.get(i).x, points.get(i).y));
                if(i + 1 < points.size())newPoints.add(new Punto(points.get(i+1).x, points.get(i+1).y));
            }
            points = newPoints;
        }
    }
    
    public void escalar (double s) {
        factorEscalacion = s;
    }
    
    public abstract void recalcular ();
    public abstract void fill ();
    public abstract Punto calculateCenterPoint ();    
    
    public void build() {
        recalcular();
        if (rellenado && tipoTrazado == TipoTrazado.CONTINUO) {
            fill();
        }
        calcularGrosor();
        calcularTipoTrazado();
    }
    
    public void update () {
        build();
    }
    
    protected void cuatro_vecinos(int x, int y) {
        Stack<Punto> stack = new Stack<Punto>();
        stack.push(new Punto(x, y));
        while (!stack.empty()) {
            Punto p = stack.pop();
            if (!points.contains(p)) {
                points.add(new Punto((int)p.getX(), (int)p.getY()));
                stack.push(new Punto((int)p.getX(), (int)p.getY()+1));
                stack.push(new Punto((int)p.getX()+1, (int)p.getY()));
                stack.push(new Punto((int)p.getX(), (int)p.getY()-1));
                stack.push(new Punto((int)p.getX()-1, (int)p.getY()));
            }
        }
    }
}
