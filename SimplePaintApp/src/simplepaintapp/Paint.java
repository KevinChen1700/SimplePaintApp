package simplepaintapp;

import java.awt.Point;
import java.io.BufferedWriter;
import java.awt.Graphics;
import java.awt.Graphics2D;

public interface Paint {
	public void draw(Graphics2D g);
        public void drawPoints(Graphics2D g, Point startDrag, Point endDrag);
        public void drawExpand(Graphics2D g, int w, int h );
	public boolean contains(Point p);
	public void move(Point startDrag, Point endDrag);
        public void resize(Point startDrag, Point endDrag);
	public void makeObject(Point startDrag, Point endDrag);
}