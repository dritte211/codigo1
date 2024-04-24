package controller;
import java.util.ArrayList;
import javax.swing.JTextField;


public class Controller {
    private view.App app;
    private view.Header header;
    private view.Plane plane;
    
    private InputListener inputL;
    private OptionShape optionShapeL;
    private ButtonListener buttonsL;
    private PlaneListener planeL;
    
    private ControllerAttributes ctrAttributes;

    public Controller (view.App app) {
        this.app = app;
        
        header = this.app.getHeader();
        plane = this.app.getPlane();
        ctrAttributes = new ControllerAttributes(app);

        buttonsL = new ButtonListener(this.app);
        planeL = new PlaneListener(this.app);
        optionShapeL = new OptionShape(header.getOptionsShapes().getOptionsShape(), new model.ShapeMap()){
            @Override
            public void action (model.Shape shape) {
                updateShape(shape);
            }
        };
    }
    
    public void updateShape (model.Shape shape) {
        app.setShape(shape);
        plane.setGraphic(null);
    }
}
