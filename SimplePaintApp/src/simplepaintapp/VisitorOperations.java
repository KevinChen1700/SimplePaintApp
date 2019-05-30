/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import static simplepaintapp.Invoker.undoStack;

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
}