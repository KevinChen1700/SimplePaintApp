/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;


/**
 *
 * @author Sjimmie
 */
public class AddCommand implements Command{
    private Shape selectedShape;
    private ArrayList<Shape> shapes;
    private Point startDrag;
    public AddCommand(Shape selectedShape, ArrayList<Shape> shapes, Point startDrag){ 
        this.selectedShape = selectedShape;
        this.shapes = shapes;
        this.startDrag = startDrag;
    }
    
    public void execute ()
    {
     for(Shape pt : shapes){
           if (pt.contains(startDrag) && selectedShape != null) {
               selectedShape.add(pt);
               shapes.remove(pt);
               return;
           }
       }
    }
}
