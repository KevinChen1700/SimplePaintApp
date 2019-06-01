/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static simplepaintapp.Invoker.undoStack;
import static simplepaintapp.Canvas.endDrag;
import static simplepaintapp.Canvas.g;
import static simplepaintapp.Canvas.ptemp;
import static simplepaintapp.Canvas.startDrag;
/**
 *
 * @author Kevin
 */
public class VisitorOperations  implements Visitor {

    public void visitMove(Move object) {
     object.getObject().move(object.getStartDrag(), object.getEndDrag());
     if(object instanceof ObjectCommand ){undoStack.push(object);}
    }
    
    public void visitResize (Resize object){
     object.getObject().resize(object.getStartDrag(), object.getEndDrag());
     if(object instanceof ObjectCommand ){undoStack.push(object);}
    }
    
    public void drawMove(){
        g.setColor(Color.RED);
        ptemp.drawPoints(g, startDrag, endDrag);
    }
    public void drawResize(){
       g.setColor(Color.RED);
       int w = endDrag.x - startDrag.x;
       int h = endDrag.y - startDrag.y;
       ptemp.drawExpand(g, w, h);
    }
        
    public void visitSave (VisitorSave saveFile)
    {
        try {
        PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
        writeList(saveFile.getShapes(), writer, 0, "" );
        writer.close();
        }
         catch (FileNotFoundException ex) {
            Logger.getLogger(SaveCommand.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("File not found.");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SaveCommand.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Unsopperted encoding format used.");
        }
    }

    private void writeList(ArrayList<DrawAbleShape> shapes, PrintWriter writer, int groupCount, String tabs){
        for(DrawAbleShape shape : shapes){
            ArrayList<DrawAbleShape> ptComponents = shape.getComponents();
            if(!(ptComponents.isEmpty())){
                writer.println(tabs + "group " + groupCount);
                writeList(ptComponents, writer, groupCount + 1, tabs + "    ");
                } else{writer.println(tabs + getLine(shape));}
        }
           
            
        }
    
    private String getLine(DrawAbleShape pt){
        String line = "";
        if(pt instanceof MyRectangle){
                line += "Rectangle ";
                MyRectangle temp = ((MyRectangle) pt);
                line += (Math.round(temp.getRect().getX()));
                line += " ";
                line += (Math.round(temp.getRect().getY()));
                line += " ";
                line += (Math.round(temp.getRect().getWidth()));
                line += " ";
                line += (Math.round(temp.getRect().getHeight()));
                return line;
                
            }
        else{
                line += "Ellipse ";
                MyEllipse temp = ((MyEllipse) pt);
                line += (Math.round(temp.getEllipse().getX()));
                line += " ";
                line += (Math.round(temp.getEllipse().getY()));
                line += " ";
                line += (Math.round(temp.getEllipse().getWidth()));
                line += " ";
                line += (Math.round(temp.getEllipse().getHeight()));
                return line;
            }
    
    }

}