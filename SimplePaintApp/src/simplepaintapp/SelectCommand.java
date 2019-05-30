/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.util.ArrayList;
import static simplepaintapp.Canvas.pt;
import static simplepaintapp.Canvas.ptemp;
import static simplepaintapp.Canvas.startDrag;

/**
 *
 * @author Kevin
 */
public class SelectCommand implements Command {
    private ArrayList<DrawAbleShape> shapes;
    public SelectCommand(ArrayList<DrawAbleShape> shapes){
        this.shapes = shapes;
    }
    
    
    public void execute ()
    {
     for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
          pt = shapes.get(i);
           if (pt.contains(startDrag)) {
               if (ptemp != null) 
               {
                   shapes.add(ptemp); //puts previous selection back into the paint arraylist
               }
               ptemp = pt; 
               shapes.remove(pt);
           }
       }
    }
}
