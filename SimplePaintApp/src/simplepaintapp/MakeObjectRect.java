/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Kevin
 */
public class MakeObjectRect extends ObjectCommand {
    private MyRectangle obj;
    ArrayList<DrawAbleShape> shapes;
    public MakeObjectRect(ArrayList<DrawAbleShape> shapes, MyRectangle obj, Point startDrag, Point endDrag){
      this.shapes = shapes;
      this.obj = obj;
      obj.makeObject(startDrag, endDrag);
   }
    
    public void execute() {
      shapes.add(obj);
   }
   
    @Override
    public void undo(){ shapes.remove(obj);}
    
    @Override
    public void redo(){ shapes.add(obj);}

}
