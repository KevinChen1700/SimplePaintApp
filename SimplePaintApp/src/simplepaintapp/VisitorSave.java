package simplepaintapp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisitorSave implements Visitable {

    private ArrayList<Shape> shapes;

    public VisitorSave(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

//accept the visitor
    public void accept(Visitor visitor) {
        visitor.visitSave(this);
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
