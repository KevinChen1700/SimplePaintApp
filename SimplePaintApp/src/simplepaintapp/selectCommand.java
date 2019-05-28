/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import static simplepaintapp.Canvas.pt;
import static simplepaintapp.Canvas.ptemp;
import static simplepaintapp.Canvas.startDrag;

/**
 *
 * @author Kevin
 */
public class selectCommand implements command {
    
    public void execute ()
    {
     for (int i = GUI.shapes.size() - 1; i >= 0; i = i - 1) {
          pt = GUI.shapes.get(i);
           if (pt.contains(startDrag)) {
               if (ptemp != null) 
               {
                   GUI.shapes.add(ptemp); //puts previous selection back into the paint arraylist
               }
               ptemp = pt; 
               GUI.shapes.remove(pt);
           }
       }
    }
}
