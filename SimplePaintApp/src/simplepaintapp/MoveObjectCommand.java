/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Point;

/**
 *
 * @author Kevin
 */
public class MoveObjectCommand extends ObjectCommand {
    private Point startDrag;
    private Point endDrag;
    private DrawAbleShape object;
    public MoveObjectCommand(Point startDrag, Point endDrag, DrawAbleShape object)
    {
        this.startDrag = startDrag;
        this.endDrag = endDrag;
        this.object = object;
    }
    public void execute()
    {
      object.move(startDrag, endDrag);
    }
    
    public void undo(){ object.move(endDrag, startDrag);}
    
    public void redo(){ object.move(startDrag, endDrag);}
}
