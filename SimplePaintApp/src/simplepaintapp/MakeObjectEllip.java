/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class MakeObjectEllip extends ObjectCommand {
    private MyEllipse obj;
    ArrayList<DrawAbleShape> shapes;
    public MakeObjectEllip(ArrayList<DrawAbleShape> shapes, MyEllipse obj, Point startDrag, Point endDrag ){
      this.shapes = shapes;
      this.obj = obj;
      obj.makeObject(startDrag, endDrag);
   }
    
    public void execute() {
      shapes.add(obj);
   }
   
    public void undo(){ shapes.remove(obj);}
    
    public void redo(){ shapes.add(obj);}

}
