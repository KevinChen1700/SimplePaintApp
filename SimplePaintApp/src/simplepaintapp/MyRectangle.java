/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Sjimmie
 */
public class MyRectangle implements Paint {

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
    public void draw(Graphics2D g) {
        g.drawRect(this.getRect().x, this.getRect().y, this.getRect().width, this.getRect().height);
    }
    
    @Override
    public void drawPoints(Graphics2D g, Point startDrag, Point endDrag) {
        g.drawRect(this.getRect().x + endDrag.x - startDrag.x, this.getRect().y + endDrag.y - startDrag.y, this.getRect().width, this.getRect().height);
    }
    
    @Override
    public void drawExpand(Graphics2D g, int w, int h ) {
        w = rect.width + w;
        h = rect.height + h;
        if (w < 1) w=1;
        if (h < 1) h =1;
        g.drawRect(rect.x, rect.y, w, h);
    }

    @Override
    public boolean contains(Point p) {
        return this.getRect().contains(p);
    }

    @Override
    public void move(Point startDrag, Point endDrag) {
        Rectangle r = new Rectangle(this.getRect().x + (endDrag.x - startDrag.x), this.getRect().y + (endDrag.y - startDrag.y), this.getRect().width, this.getRect().height);
        this.setRect(r);
    }

    @Override
    public void resize(Point startDrag, Point endDrag) {
        int x = endDrag.x - startDrag.x;
        int y = endDrag.y - startDrag.y;
        if ((this.getRect().width + x) < 1) {
            x = (this.getRect().width - 1);
            x = -x;
        }
        if ((this.getRect().height + y) < 1) {
            y = this.getRect().height - 1;
            y = -y;
        }
        Rectangle r= new Rectangle(this.getRect().x, this.getRect().y, this.getRect().width + x, this.getRect().height + y);
        this.setRect(r);
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

}