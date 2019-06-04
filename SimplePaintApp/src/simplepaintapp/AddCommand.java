package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;

//addCommand for grouping objects into selectedshape
public class AddCommand implements Command {

    private Shape selectedShape;       //shape for grouped objects 
    private ArrayList<Shape> shapes;  //shape for single objects
    private Point startDrag;

    public AddCommand(Shape selectedShape, ArrayList<Shape> shapes, Point startDrag) {
        this.selectedShape = selectedShape;
        this.shapes = shapes;
        this.startDrag = startDrag;
    }

    public void execute() {
        for (Shape pt : shapes) {
            if (pt.contains(startDrag) && selectedShape != null) {
                selectedShape.add(pt);     //if shape is added in grouped objects
                shapes.remove(pt);         //remove the same shape in single objects
                return;
            }
        }
    }
}
