package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;

//class to add the created shape into shapes arraylist
public class CreateShape extends ObjectCommand {

    private ArrayList<Shape> shapes;
    private Shape obj;

    public CreateShape(ArrayList<Shape> shapes, Strategy delegate, Point startDrag, Point endDrag) {
        this.shapes = shapes;
        obj = new Shape(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y), delegate);

    }

    //add the created shape into shapes arraylist
    public void execute() {
        shapes.add(obj);
    }

    //undo and redo functionality
    @Override
    public void undo() {
        shapes.remove(obj);
    }

    @Override
    public void redo() {
        shapes.add(obj);
    }

}
