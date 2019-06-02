/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;
import static simplepaintapp.Canvas.ptemp;


/**
 *
 * @author Kevin
 */
public class SelectCommand implements Command {
    private ArrayList<Shape> shapes;
    private Point startDrag;
    public SelectCommand(ArrayList<Shape> shapes, Point startDrag){
        this.shapes = shapes;
        this.startDrag = startDrag;
    }
    
    
    public void execute ()
    {
     for(Shape pt : shapes)
           if (pt.contains(startDrag)) {
               System.out.print("BIEM");
               if (ptemp != null) 
               {
                   shapes.add(ptemp); //puts previous selection back into the paint arraylist
               }
               ptemp = pt; 
               shapes.remove(pt);
           }
     System.out.print("REEE");
       }
    }

