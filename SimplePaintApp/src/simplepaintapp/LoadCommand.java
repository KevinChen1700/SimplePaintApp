/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

/**
 *
 * @author Sjimmie
 */
public class LoadCommand implements Command {
    private ArrayList<Shape> shapes;
    private Canvas canvas;
    public LoadCommand(Canvas canvas){
        this.canvas = canvas;
        this.shapes = canvas.getShapes();
    }
    
    public void execute(){
        try{
            shapes.clear();
            BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
            String line = reader.readLine();
            int tabCount = 0;
            boolean needGroup = false;
            ArrayList<ArrayList<Shape>> groups = new ArrayList();
            groups.add(shapes);
            int groupCount = 0;
            ArrayList<Shape> currentShapes = shapes;
            
            while (line != null) {
                String[] splitted = line.split("\\s+");
                if("".equals(splitted[0])){splitted = Arrays.copyOfRange(splitted, 1, splitted.length);}
                
                if(needGroup){                 
                    Shape tempObj = lineToShape(splitted);
                    groups.get(groupCount).add(tempObj);
                    groups.add(tempObj.getComponents());
                    groupCount++;
                    needGroup = false;
                }
                int tempTabCount = 0;
                for(char c : line.toCharArray()){
                    char s = ' ';
                    if(' ' == c)
                    { tempTabCount++; }
                    else{break;}
                }
                if(tempTabCount < tabCount){groupCount--;}
                else if(tempTabCount > tabCount){tabCount = tempTabCount;}
                
                if("group".equals(splitted[0])){
                    needGroup = true;
                }
                else {
                    Shape tempObj = lineToShape(splitted);
                    groups.get(groupCount).add(tempObj);
                }
		// read next line
		line = reader.readLine();
            }
            reader.close();
            canvas.repaint();
        
        }catch(IOException e){ e.printStackTrace();}
    }
    
    private Shape lineToShape(String[] splitted){
        if("Rectangle".equals(splitted[0])){
                    RectangleDelegate delegate = RectangleDelegate.getInstance();
                    Shape obj = new Shape(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]), delegate);
                    return obj;
       }
        else {
                    EllipseDelegate delegate = EllipseDelegate.getInstance();
                    Shape obj = new Shape(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]), delegate);
                    return obj;
        }
    }
}
