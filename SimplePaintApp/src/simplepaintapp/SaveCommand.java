package simplepaintapp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

 //convert all the values of the objects into a string and remember it
public class SaveCommand implements Command {

    private ArrayList<DrawAbleShape> shapes;

    public SaveCommand(ArrayList<DrawAbleShape> shapes) {
        this.shapes = shapes;
    }

    public void execute() {
        try {
            PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
            
        writeList(shapes, writer, 0, "" );
        writer.close();
        } catch (FileNotFoundException ex) {
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
