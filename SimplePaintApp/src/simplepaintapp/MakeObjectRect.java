/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author Kevin
 */
public class MakeObjectRect extends ObjectCommand {
    private MyRectangle obj;
    public MakeObjectRect(MyRectangle obj, Point startDrag, Point endDrag){
      this.obj = obj;
      obj.makeObject(startDrag, endDrag);
   }
    
    public void execute() {
      GUI.shapes.add(obj);
   }
   
    @Override
    public void undo(){ GUI.shapes.remove(obj);}
    
    @Override
    public void redo(){ GUI.shapes.add(obj);}

}
