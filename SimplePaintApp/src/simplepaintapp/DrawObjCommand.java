package simplepaintapp;

import java.awt.Graphics2D;
import java.awt.Point;

//command for animation of drawing object using graphics 
public class DrawObjCommand implements Command {

    private DrawAbleShape obj;
    private Graphics2D g;
    private Point startDrag;
    private Point endDrag;

    public DrawObjCommand(DrawAbleShape obj, Graphics2D g, Point startDrag, Point endDrag) {
        this.obj = obj;
        this.g = g;
        this.startDrag = startDrag;
        this.endDrag = endDrag;
    }

    public void execute() {
        obj.makeObject(startDrag, endDrag);
        obj.draw(g);
    }
}
