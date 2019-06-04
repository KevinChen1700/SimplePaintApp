package simplepaintapp;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

//Shape class to to make and draw rectangles and ellipses
public class Shape {

    private int x;
    private int y;
    private int w;   //width
    private int h;   //height
    private Strategy delegate;
    private ArrayList<Shape> components = new ArrayList<Shape>();   //for grouped objects
    private ArrayList<Decorator> decorators = new ArrayList<Decorator>();    //for grouped texts

    public Shape(int x, int y, int w, int h, Strategy delegate) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.delegate = delegate;
    }

    //simple get and set values
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    //draw object from delegate, draw the grouped objects and the strings
    public void draw(Graphics g) {
        delegate.draw(g, x, y, w, h);
        for (Shape s : components) {
            s.draw(g);
        }
        for (Decorator de : decorators) {
            de.draw(g);
        }

    }

    //checks if a object is clicked using select, if it is, then return true
    public boolean contains(Point p) {
        if (p.x >= x && p.x <= (x + w) && p.y >= y && p.y <= (y + h)) {
            return true;
        }
        for (Shape s : components) {
            if (s.contains(p)) {
                return true;
            }
        }
        return false;
    }

    //move function for grouped objects and grouped texts
    public void move(Point startDrag, Point endDrag) {
        x = x + endDrag.x - startDrag.x;
        y = y + endDrag.y - startDrag.y;

        for (Shape s : components) {
            s.move(startDrag, endDrag);

        }
        for (Decorator de : decorators) {
            de.move(endDrag.x - startDrag.x, endDrag.y - startDrag.y);
        }
    }

    public void add(Shape s) {
        components.add(s);
    }   //add object to the components group

    public void addText(Decorator d) {
        decorators.add(d);
    }  // add text to the decorators group

    public ArrayList<Shape> getComponents() {
        return components;
    }   //get the objects in the components group

    public ArrayList<Decorator> getDecorators() {
        return decorators;
    }   //get the texts in the decorators group

    public String toString() {
        return delegate.toString() + " " + x + " " + y + " " + w + " " + h;
    }  //Make a string with all the values of the object
}
