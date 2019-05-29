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
public class MakeObjectEllip extends ObjectCommand {
    private MyEllipse obj;
    public MakeObjectEllip(MyEllipse obj, Point startDrag, Point endDrag ){
      this.obj = obj;
      obj.makeObject(startDrag, endDrag);
   }
    
    public void execute() {
      GUI.shapes.add(obj);
   }
   
    public void undo(){ GUI.shapes.remove(obj);}
    
    public void redo(){ GUI.shapes.add(obj);}

}
