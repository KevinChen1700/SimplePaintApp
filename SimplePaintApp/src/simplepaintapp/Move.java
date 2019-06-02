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
    private Point startDrag;
    private Point endDrag;
    private Shape object;
    
     public Move(Point startDrag, Point endDrag, Shape object)
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
    public Shape getObject(){
        return object;
    }
    private void move(Point startDrag, Point endDrag){
        object.setX(object.getX()+endDrag.x - startDrag.x);
        object.setY(object.getY()+endDrag.y - startDrag.y);
    }
    
    public void undo(){ move(endDrag, startDrag);}
    public void redo(){ move(startDrag, endDrag);}
}
