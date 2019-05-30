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
public class Move extends ObjectCommand implements Visitable {
    //code to get the values for move function
    private Point startDrag;
    private Point endDrag;
    private DrawAbleShape object;
    
     public Move(Point startDrag, Point endDrag, DrawAbleShape object)
    {
         this.startDrag = startDrag;
         this.endDrag = endDrag;
         this.object = object;
    }
     
    //accept the visitor
    public void accept(Visitor visitor) {
    visitor.visitMove(this);
  }
    
    public Point getStartDrag(){
        return startDrag;
    }
    public Point getEndDrag(){
        return endDrag;
    }
    public DrawAbleShape getObject(){
        return object;
    }
    
    public void undo(){ object.move(endDrag, startDrag);}
    public void redo(){ object.move(startDrag, endDrag);}
}
