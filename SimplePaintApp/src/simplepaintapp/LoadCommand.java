package simplepaintapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class LoadCommand implements Command {

    private ArrayList<DrawAbleShape> shapes;
    private Canvas canvas;

    public LoadCommand(Canvas canvas) {
        this.canvas = canvas;
        this.shapes = canvas.getShapes();
    }

    public void execute() {
        try {
            shapes.clear();
            BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] splitted = line.split(" ");
                if ("Rectangle".equals(splitted[0])) {
                    MyRectangle obj = new MyRectangle();
                    Rectangle rect = new Rectangle(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
                    obj.setRect(rect);
                    shapes.add(obj);
                } else if ("Ellipse".equals(splitted[0])) {
                    MyEllipse obj = new MyEllipse();
                    Ellipse2D elip = new Ellipse2D.Float(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
                    obj.setEllipse(elip);
                    shapes.add(obj);

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

}
