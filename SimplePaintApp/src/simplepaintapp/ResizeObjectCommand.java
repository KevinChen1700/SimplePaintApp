package simplepaintapp;

import java.awt.Point;

/**
 *
 * @author Kevin
 */
public class ResizeObjectCommand extends ObjectCommand {
    private Point startDrag;
    private Point endDrag;
    private DrawAbleShape object;
    public ResizeObjectCommand(Point startDrag, Point endDrag, DrawAbleShape object)
    {
        this.startDrag = startDrag;
        this.endDrag = endDrag;
        this.object = object;
    }
    public void execute()
    {
      object.resize(startDrag, endDrag);
    }
    
    public void undo(){ object.resize(endDrag, startDrag);}
    
    public void redo(){ object.resize(startDrag, endDrag);}
}
