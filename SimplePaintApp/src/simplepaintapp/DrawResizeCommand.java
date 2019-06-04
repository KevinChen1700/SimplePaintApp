package simplepaintapp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

//command for animation of resizing object using graphics 
public class DrawResizeCommand implements Command {

    private DrawAbleShape obj;
    private Graphics2D g;
    private Point startDrag;
    private Point endDrag;

    public DrawResizeCommand(DrawAbleShape obj, Graphics2D g, Point startDrag, Point endDrag) {
        this.obj = obj;
        this.g = g;
        this.startDrag = startDrag;
        this.endDrag = endDrag;
    }

    public void execute() {
        g.setColor(Color.RED);
        int w = endDrag.x - startDrag.x;
        int h = endDrag.y - startDrag.y;
        obj.drawExpand(g, w, h);
    }
}
