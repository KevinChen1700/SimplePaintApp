/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import static simplepaintapp.Canvas.endDrag;
import static simplepaintapp.Canvas.startDrag;
import static simplepaintapp.Canvas.g;
/**
 *
 * @author Kevin
 */
public class DrawEllip implements Command {
    private MyEllipse obj;
    public DrawEllip(MyEllipse obj){
    this.obj = obj;
    }
    
    public void execute()
    {
      obj.makeObject(startDrag, endDrag);
      obj.draw(g);
    }
}
