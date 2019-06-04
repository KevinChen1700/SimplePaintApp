package simplepaintapp;

import java.awt.Graphics;
import java.awt.Graphics2D;

//command for drawing the objects
public class DrawShapesCommand implements Command {
    private DrawAbleShape pt;
    private Graphics2D g;
    public DrawShapesCommand(DrawAbleShape pt, Graphics2D g)
    {
        this.pt = pt;
        this.g = g;
    }
    
    public void execute()
    {
      pt.draw(g);
    }
}
