package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;

//addCommand for grouping objects into selectedshape
public class AddCommand implements Command{
    
    private DrawAbleShape selectedShape;    //shape for grouped objects 
    private ArrayList<DrawAbleShape> shapes;  //shape for single objects
    private Point startDrag;
    
    public AddCommand(DrawAbleShape selectedShape, ArrayList<DrawAbleShape> shapes, Point startDrag){ 
        this.selectedShape = selectedShape;
        this.shapes = shapes;
        this.startDrag = startDrag;
    }
    
    public void execute ()
    {
     for(DrawAbleShape pt : shapes){
           if (pt.contains(startDrag) && selectedShape != null) {
               selectedShape.add(pt);    //if shape is added in grouped objects
               shapes.remove(pt);       //remove the same shape in single objects
               return;
           }
       }
    }
}
