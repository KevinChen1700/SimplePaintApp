/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Sjimmie
 */
public class MyRectangle implements Paint{
    private Rectangle rect;
    
    public MyRectangle() {
		
	}
    public MyRectangle(Rectangle r) {
		this.rect = r;
	}
    
    public void makeObject(Point startDrag, Point endDrag) {
		Rectangle r = new Rectangle(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y));	
		this.setRect(r);
	}
	
	public void makeRectangle(int x, int y, int w, int h) {
		Rectangle r = new Rectangle(x, y, w, h);
	    this.setRect(r);
	}
        
         @Override
	public void draw(GraphicAdapter g) {
        	g.getGraphicAdapter().drawRect(this.getRect().x, this.getRect().y, this.getRect().width, this.getRect().height);	
        }
  
    @Override
    public boolean contains(Point p) {
    	return this.getRect().contains(p);
    }
    @Override
    public void move(Point startDrag, Point endDrag){
    	Rectangle r = new Rectangle(this.getRect().x + (endDrag.x - startDrag.x),this.getRect().y + (endDrag.y - startDrag.y), this.getRect().width, this.getRect().height);
    	this.setRect(r);
    }
        
        
        
        public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
    
    
}
