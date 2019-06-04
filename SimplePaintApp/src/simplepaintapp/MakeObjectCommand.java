package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;

//command to add the object into shapes arraylist
public class MakeObjectCommand extends ObjectCommand {

    private DrawAbleShape obj;
    ArrayList<DrawAbleShape> shapes;

    public MakeObjectCommand(ArrayList<DrawAbleShape> shapes, DrawAbleShape obj, Point startDrag, Point endDrag) {
        this.shapes = shapes;
        this.obj = obj;
        obj.makeObject(startDrag, endDrag);
    }

    public void execute() {
        shapes.add(obj);
    }

    @Override
    public void undo() {
        shapes.remove(obj);
    }

    @Override
    public void redo() {
        shapes.add(obj);
    }

}
