package simplepaintapp;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyRectangle implements DrawAbleShape {

    private Rectangle rect;

    public MyRectangle() {

    }

    public MyRectangle(Rectangle r) {
        this.rect = r;
    }    


    @Override
    public void draw(Graphics2D g) {
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }
    
    @Override
    public void drawPoints(Graphics2D g, Point startDrag, Point endDrag) {
        g.drawRect(rect.x + endDrag.x - startDrag.x, rect.y + endDrag.y - startDrag.y, rect.width, rect.height);
    }
    
    //draws a resized version of the object but this version can't have a width and/or height of less than 1
    @Override
    public void drawExpand(Graphics2D g, int w, int h ) {
        w = rect.width + w;
        h = rect.height + h;
        if (w < 1) w=1;
        if (h < 1) h =1;
        g.drawRect(rect.x, rect.y, w, h);
    }
    
    @Override
    public void makeObject(Point startDrag, Point endDrag) {
        Rectangle r = new Rectangle(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y));
        rect = r;
    }

    @Override
    public void move(Point startDrag, Point endDrag) {
        Rectangle r = new Rectangle(rect.x + (endDrag.x - startDrag.x), rect.y + (endDrag.y - startDrag.y), rect.width, rect.height);
        rect = r;
    }

    //resizes the object but not to less than 1 width and/or 1 height
    @Override
    public void resize(Point startDrag, Point endDrag) {
        int x = endDrag.x - startDrag.x;
        int y = endDrag.y - startDrag.y;
        if ((rect.width + x) < 1) {
            x = (rect.width - 1);
            x = -x;
        }
        if ((rect.height + y) < 1) {
            y = rect.height - 1;
            y = -y;
        }
        Rectangle r= new Rectangle(rect.x, rect.y, rect.width + x, rect.height + y);
        rect = r;
    }
    
    @Override
    public boolean contains(Point p) {
        return rect.contains(p);
    }

}
