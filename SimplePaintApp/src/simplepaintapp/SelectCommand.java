package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;
import static simplepaintapp.Canvas.ptemp;

//Command with functionality for when an object is selected
public class SelectCommand implements Command {

    private ArrayList<DrawAbleShape> shapes;
    private Point startDrag;

    public SelectCommand(ArrayList<DrawAbleShape> shapes, Point startDrag) {
        this.shapes = shapes;
        this.startDrag = startDrag;
    }

    public void execute() {

        for (DrawAbleShape pt : shapes) {
            if (pt.contains(startDrag)) {
                if (ptemp != null) {
                    shapes.add(ptemp); //puts previous selection back into the paint arraylist
                }
                ptemp = pt;
                shapes.remove(pt);
                break;
            }
        }
    }
}
