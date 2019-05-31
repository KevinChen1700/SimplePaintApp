package simplepaintapp;

import java.awt.Point;
import java.awt.Graphics2D;
import java.util.ArrayList;

//interface for drawable objects
public interface DrawAbleShape {
	public void draw(Graphics2D g); //draws the object
        public void drawPoints(Graphics2D g, Point startDrag, Point endDrag); //draws the object based on the locations of the two points
        public void drawExpand(Graphics2D g, int w, int h ); //draws the object with a different width and height
	public boolean contains(Point p); //checks if a certain point is within the object
	public void move(Point startDrag, Point endDrag); 
        public void resize(Point startDrag, Point endDrag);
	public void makeObject(Point startDrag, Point endDrag);     
        public void add(DrawAbleShape shape);
        public ArrayList<DrawAbleShape> getComponents();
}