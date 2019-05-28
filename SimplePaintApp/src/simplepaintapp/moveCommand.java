/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;
import static simplepaintapp.Canvas.p;
import static simplepaintapp.Canvas.ptemp;
import static simplepaintapp.Canvas.startDrag;
import simplepaintapp.command;

/**
 *
 * @author Kevin
 */
public class moveCommand implements command {
    

    
    public void execute()
    {
      ptemp.move(startDrag, p);
   }
    
}
