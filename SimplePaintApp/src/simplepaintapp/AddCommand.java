package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;

//addCommand for grouping objects into selectedshape
public class AddCommand implements Command {

    private Shape selectedShape;
    private ArrayList<Shape> shapes;
    private Point startDrag;

    public AddCommand(Shape selectedShape, ArrayList<Shape> shapes, Point startDrag) {
        this.selectedShape = selectedShape;
        this.shapes = shapes;
        this.startDrag = startDrag;
    }

    public void execute() {
        for (Shape pt : shapes) {
            if (pt.contains(startDrag) && selectedShape != null) {
                selectedShape.add(pt);
                shapes.remove(pt);
                return;
            }
        }
    }

}
