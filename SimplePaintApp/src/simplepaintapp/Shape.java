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
    
    public void draw(Graphics g){delegate.draw(g, x, y, w, h);}
    
    public boolean contains(Point p){
        if(p.x <= x && p.x >= w && p.y <= y && p.y >= h){return true;}
        else{return false;}
    }
    
    public void add(Shape s){components.add(s);}
    public ArrayList<Shape> getComponents(){return components;} 
    
    public String toString(){return delegate.toString() + " " + x + " " + y + " " + w + " " + h;}
    
}
