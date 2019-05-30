/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

/**
 *
 * @author Kevin
 */
public class VisitorOperations  implements Visitor {
    
    
    public void visitMove(Move object) {
    //implement function to do move using the values in Move
     //object.move(startDrag, endDrag);
     object.getObject().move(object.getStartDrag(), object.getEndDrag());
    }
    
    public void visitResize (Resize object){
    //implement function to do move using the values in Resize
    //object.resize(startDrag, endDrag);
     object.getObject().move(object.getStartDrag(), object.getEndDrag());
    }
}