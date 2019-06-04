package simplepaintapp;

import java.awt.Graphics;

//class to draw the shape
public class DrawShapeCommand implements Command {

    private Shape shape;
    private Graphics g;

    public DrawShapeCommand(Shape shape, Graphics g) {
        this.shape = shape;
        this.g = g;
    }

    public void execute() {
        shape.draw(g);
    }
}
