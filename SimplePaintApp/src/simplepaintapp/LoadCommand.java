package simplepaintapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

//command to read contents in save.txt and draw the corresponding objects back into the app
public class LoadCommand implements Command {

    private ArrayList<DrawAbleShape> shapes;       //to hold all of the objects that was created from Shape class
    private Canvas canvas;

    public LoadCommand(Canvas canvas) {
        this.canvas = canvas;
        this.shapes = canvas.getShapes();
    }

    public void execute() {
        try {
            shapes.clear();
            BufferedReader reader = new BufferedReader(new FileReader("save.txt"));     //reader to read all text in save.txt
            String line = reader.readLine();
            int tabCount = 0;
            boolean needGroup = false;
            ArrayList<ArrayList<DrawAbleShape>> groups = new ArrayList();      //to hold all grouped objects that was 
            groups.add(shapes);
            int groupCount = 0;
            ArrayList<DrawAbleShape> currentShapes = shapes;
            
            while (line != null) {
                String[] splitted = line.split("\\s+");
                if("".equals(splitted[0])){splitted = Arrays.copyOfRange(splitted, 1, splitted.length);}
                
                if(needGroup){                 
                    DrawAbleShape tempObj = lineToShape(splitted);
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
                    DrawAbleShape tempObj = lineToShape(splitted);
                    groups.get(groupCount).add(tempObj);
                
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
            canvas.repaint();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private DrawAbleShape lineToShape(String[] splitted){
        if("Rectangle".equals(splitted[0])){
                    MyRectangle obj = new MyRectangle();
                    Rectangle rect = new Rectangle(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
                    obj.setRect(rect);
                    return obj;
                }
        else {
                    MyEllipse obj = new MyEllipse();
                    Ellipse2D elip = new Ellipse2D.Float(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
                    obj.setEllipse(elip);
                    return obj;
                
                }
        
    }
    
    
}
