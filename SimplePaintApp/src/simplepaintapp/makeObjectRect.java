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
    private MyRectangle abcRect;
    public makeObjectRect(MyRectangle abcRect){
      this.abcRect = abcRect;
   }

   
    @Override
   public void execute() {
       Point startDrag = new Point();
       Point endDrag = new Point();
      abcRect.makeObject(startDrag,endDrag);
   }
}
