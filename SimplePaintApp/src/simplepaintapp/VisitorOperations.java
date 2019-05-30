/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Color;
import static simplepaintapp.Invoker.undoStack;
import static simplepaintapp.Canvas.endDrag;
import static simplepaintapp.Canvas.g;
import static simplepaintapp.Canvas.ptemp;
import static simplepaintapp.Canvas.startDrag;
/**
 *
 * @author Kevin
 */
public class VisitorOperations  implements Visitor {
    
    
    public void visitMove(Move object) {
     object.getObject().move(object.getStartDrag(), object.getEndDrag());
     if(object instanceof ObjectCommand ){undoStack.push(object);}
    }
    
    public void visitResize (Resize object){
     object.getObject().resize(object.getStartDrag(), object.getEndDrag());
     if(object instanceof ObjectCommand ){undoStack.push(object);}
    }
    
    public void drawMove(){
        g.setColor(Color.RED);
        ptemp.drawPoints(g, startDrag, endDrag);
    }
    public void drawResize(){
       g.setColor(Color.RED);
       int w = endDrag.x - startDrag.x;
       int h = endDrag.y - startDrag.y;
       ptemp.drawExpand(g, w, h);
    }
}