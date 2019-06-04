package simplepaintapp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveCommand implements Command {

    private ArrayList<DrawAbleShape> shapes;

    public SaveCommand(ArrayList<DrawAbleShape> shapes) {
        this.shapes = shapes;
    }

    public void execute() {
        try {
            PrintWriter writer = new PrintWriter("save.txt", "UTF-8");

            for (DrawAbleShape pt : shapes) {
                String line = "";
                if (pt instanceof MyRectangle) {
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

                } else if (pt instanceof MyEllipse) {
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
