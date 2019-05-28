/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplepaintapp;
import java.awt.Point;
import simplepaintapp.command;


/**
 *
 * @author Kevin
 */
public class makeObjectRect implements command {
    Point startDrag = new Point(Canvas.startDrag);
    Point endDrag = new Point(Canvas.p);
    private MyRectangle obj;
    public makeObjectRect(MyRectangle obj){
      this.obj = obj;
   }

   public void execute() {
      obj.makeObject(startDrag,endDrag);
      GUI.shapes.add(obj);
      //System.out.println("itworks");
   }
}
