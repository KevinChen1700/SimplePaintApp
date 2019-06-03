/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Sjimmie
 */
public class Shape {
    private int x;
    private int y;
    private int w;
    private int h;
    private Strategy delegate;
    private ArrayList<Shape> components = new ArrayList<Shape>();
    private ArrayList<Decorator> decorators = new ArrayList<Decorator>();
    
    public Shape(int x, int y, int w, int h, Strategy delegate ){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.delegate = delegate;
    }
    
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setW(int w){this.w = w;}
    public void setH(int h){this.h = h;}
    
    public int getX(){return x;}
    public int getY(){return y;}
    public int getH(){return h;}
    public int getW(){return w;}
    
    public void draw(Graphics g){
        delegate.draw(g, x, y, w, h);
        for(Shape s : components){
            s.draw(g);
        }
        for(Decorator de : decorators){
            de.draw(g);
        }
    
    }
    
    public boolean contains(Point p){
        if(p.x >= x && p.x <= (x + w) && p.y >= y && p.y <= (y + h)){return true;}
        for(Shape s : components){
            if(s.contains(p)){return true;}
        }
        return false;
    }
    
    public void move(Point startDrag, Point endDrag){
        x = x + endDrag.x - startDrag.x;
        y = y + endDrag.y - startDrag.y;
        
        for (Shape s : components) {
            s.move(startDrag, endDrag);
            
        }
        for (Decorator de : decorators) {
            de.move(endDrag.x - startDrag.x,endDrag.y - startDrag.y);
        }
    }
    
    
    public void add(Shape s){components.add(s);}
    public void addText(Decorator d){decorators.add(d);}
    public ArrayList<Shape> getComponents(){return components;} 
    public ArrayList<Decorator> getDecorators(){return decorators;}
    public String toString(){return delegate.toString() + " " + x + " " + y + " " + w + " " + h;}
}
