package simplepaintapp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class DrawMoveCommand implements Command {

    private DrawAbleShape obj;
    private Graphics2D g;
    private Point startDrag;
    private Point endDrag;

    public DrawMoveCommand(DrawAbleShape obj, Graphics2D g, Point startDrag, Point endDrag) {
        this.obj = obj;
        this.g = g;
        this.startDrag = startDrag;
        this.endDrag = endDrag;
    }

    public void execute() {
        g.setColor(Color.RED);
        obj.drawPoints(g, startDrag, endDrag);
    }
}
