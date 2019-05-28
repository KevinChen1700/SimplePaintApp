package simplepaintapp;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyEllipse implements DrawAbleShape {

    private Ellipse2D elip;

    public MyEllipse() {

    }

    public MyEllipse(Ellipse2D e) {
        this.elip = e;
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.drawOval((int) elip.getX(), (int) elip.getY(), (int) elip.getWidth(), (int) elip.getHeight());
    }
    
    @Override
    public void drawPoints(Graphics2D g, Point startDrag, Point endDrag) {
        g.drawOval((int) elip.getX() + endDrag.x - startDrag.x, (int) elip.getY() + endDrag.y - startDrag.y, (int) elip.getWidth(), (int) elip.getHeight());
    }
    
    //draws a resized version of the object but this version can't have a width and/or height of less than 1
    @Override
    public void drawExpand(Graphics2D g, int w, int h ) {
        w = (int) elip.getWidth() + w;
        h = (int) elip.getHeight() + h;
        if(w<1)w=1;
        if(h<1)h=1;

        g.drawOval((int)elip.getX(), (int) elip.getY(), w, h);
    }

    @Override
    public void makeObject(Point startDrag, Point endDrag) {
        Ellipse2D e = new Ellipse2D.Float(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y));
        elip = e;
    }


    @Override
    public void move(Point startDrag, Point endDrag) {
        elip.setFrame(elip.getX() + endDrag.x - startDrag.x, elip.getY() + endDrag.y - startDrag.y, elip.getWidth(), elip.getHeight());
    }

    //resizes the object but not to less than 1 width and/or 1 height
    @Override
    public void resize(Point startDrag, Point endDrag) {
        int x = endDrag.x - startDrag.x;
        int y = endDrag.y - startDrag.y;
        if ((elip.getWidth() + x) < 1) {
            x = (int) elip.getWidth() - 1;
            x = -x;
        }
        if ((elip.getHeight() + y) < 1) {
            y = (int) elip.getHeight() - 1;
            y = -y;
        }
        Ellipse2D e = new Ellipse2D.Float((int) elip.getX(), (int) elip.getY(), (int) elip.getWidth() + x, (int) elip.getHeight() + y);
        elip = e;
    }
  
    @Override
    public boolean contains(Point p) {
        return this.elip.contains(p);
    }
}
