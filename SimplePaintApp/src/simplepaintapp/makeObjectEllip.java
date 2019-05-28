/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;
import java.awt.Point;
import static simplepaintapp.Canvas.p;
import static simplepaintapp.Canvas.startDrag;


/**
 *
 * @author Kevin
 */
public class makeObjectEllip implements command {
    private MyEllipse obj;
    public makeObjectEllip(MyEllipse obj){
      this.obj = obj;
   }

   public void execute() {
      obj.makeObject(startDrag,p);
      GUI.shapes.add(obj);
   }
}
