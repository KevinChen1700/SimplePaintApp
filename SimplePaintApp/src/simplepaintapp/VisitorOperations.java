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
        
        for(DrawAbleShape pt : saveFile.getShapes()){
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
                writer.println(line);
                
            }
            else if(pt instanceof MyEllipse){
                line += "Ellipse ";
                MyEllipse temp = ((MyEllipse) pt);
                line += (Math.round(temp.getEllipse().getX()));
                line += " ";
                line += (Math.round(temp.getEllipse().getY()));
                line += " ";
                line += (Math.round(temp.getEllipse().getWidth()));
                line += " ";
                line += (Math.round(temp.getEllipse().getHeight()));
                writer.println(line);
            }
        }
        writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveCommand.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("File not found.");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SaveCommand.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Unsopperted encoding format used.");
        }
    }

}