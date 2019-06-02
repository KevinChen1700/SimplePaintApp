/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static simplepaintapp.Invoker.undoStack;
/**
 *
 * @author Kevin
 */
public class VisitorOperations  implements Visitor {
    
    public VisitorOperations(){}

    public void visitMove(Move object) {
     Shape shape = object.getObject();
     shape.setX(shape.getX()+object.getEndDrag().x - object.getStartDrag().x);
     shape.setY(shape.getY()+object.getEndDrag().y - object.getStartDrag().y);
     if(object instanceof ObjectCommand ){undoStack.push(object);}
    }
    
    public void visitResize (Resize object){
     Shape shape = object.getObject();
     shape.setW(shape.getW()+object.getEndDrag().x - object.getStartDrag().x);
     shape.setH(shape.getH()+object.getEndDrag().y - object.getStartDrag().y);
     if(object instanceof ObjectCommand ){undoStack.push(object);}
    }
    
    public void drawMove(Graphics g, Shape ptemp , Point startDrag, Point endDrag){
        g.setColor(Color.RED);
        ptemp.setX(ptemp.getX()+endDrag.x - startDrag.x);
        ptemp.setY(ptemp.getY()+endDrag.y - startDrag.y);
        ptemp.draw(g);
    }
    public void drawResize(Graphics g, Shape ptemp , Point startDrag, Point endDrag){
       g.setColor(Color.RED);
       ptemp.setW(ptemp.getW()+endDrag.x - startDrag.x);
       ptemp.setH(ptemp.getH()+endDrag.y - startDrag.y);
       ptemp.draw(g);
    }
        
    public void visitSave (VisitorSave saveFile)
    {
        try {
        PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
        writeList(saveFile.getShapes(), writer, 0, "" );
        writer.close();
        }
         catch (Exception ex) {
            System.out.print("Error.");
        }
    }

    private void writeList(ArrayList<Shape> shapes, PrintWriter writer, int groupCount, String tabs){
        for(Shape shape : shapes){
            ArrayList<Shape> ptComponents = shape.getComponents();
            if(!(ptComponents.isEmpty())){
                writer.println(tabs + "group " + groupCount);
                writeList(ptComponents, writer, groupCount + 1, tabs + "    ");
                } else{writer.println(tabs + shape.toString());}
            }
        }


}