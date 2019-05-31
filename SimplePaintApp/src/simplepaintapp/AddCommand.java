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
    private DrawAbleShape selectedShape;
    private ArrayList<DrawAbleShape> shapes;
    private Point startDrag;
    public AddCommand(DrawAbleShape selectedShape, ArrayList<DrawAbleShape> shapes, Point startDrag){ 
        this.selectedShape = selectedShape;
        this.shapes = shapes;
        this.startDrag = startDrag;
    }
    
    public void execute ()
    {
     for(DrawAbleShape pt : shapes){
           if (pt.contains(startDrag)) {
               selectedShape.add(pt);
           }
       }
    }
    
    
}
