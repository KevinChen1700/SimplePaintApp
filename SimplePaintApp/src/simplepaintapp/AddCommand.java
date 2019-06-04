package simplepaintapp;

import java.awt.Point;
import java.util.ArrayList;


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
           if (pt.contains(startDrag) && selectedShape != null) {
               selectedShape.add(pt);
               shapes.remove(pt);
               return;
           }
       }
    }
    
    
}
