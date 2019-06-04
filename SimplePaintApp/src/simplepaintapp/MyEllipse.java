package simplepaintapp;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class MyEllipse implements DrawAbleShape {

    private Ellipse2D elip;
    private ArrayList<DrawAbleShape> components = new ArrayList<DrawAbleShape>();

    public MyEllipse() {

    }

    public MyEllipse(Ellipse2D e) {
        this.elip = e;
    }

    @Override
    public void makeObject(Point startDrag, Point endDrag) {
        Ellipse2D e = new Ellipse2D.Float(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y));
        this.setEllipse(e);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawOval((int) getEllipse().getX(), (int) getEllipse().getY(), (int) getEllipse().getWidth(), (int) getEllipse().getHeight());
        for(DrawAbleShape pt : components){pt.draw(g); }
    }
    
    @Override
    public void drawPoints(Graphics2D g, Point startDrag, Point endDrag) {
        g.drawOval((int) this.getEllipse().getX() + endDrag.x - startDrag.x, (int) this.getEllipse().getY() + endDrag.y - startDrag.y, (int) this.getEllipse().getWidth(), (int) this.getEllipse().getHeight());
        for(DrawAbleShape pt : components){pt.drawPoints(g, startDrag, endDrag); }
    }
    
    //draws a resized version of the object but this version can't have a width and/or height of less than 1
    @Override
    public void drawExpand(Graphics2D g, int w, int h ) {
        w = (int) elip.getWidth() + w;
        h = (int) elip.getHeight() + h;
        if(w<1)w=1;
        if(h<1)h=1;

        g.drawOval((int)elip.getX(), (int) elip.getY(), w, h);
        for(DrawAbleShape pt : components){pt.drawExpand(g, w, h); }
    }


    @Override
    public boolean contains(Point p) {
        if(this.getEllipse().contains(p))
        {
            return true;
        }
        else if(!(components.isEmpty()))
        {
            for(DrawAbleShape pt : components){
                if (pt.contains(p)){return true;}
            
            }
            return false;
        }
        else {return false;}
    }

    @Override
    public void move(Point startDrag, Point endDrag) {
        this.getEllipse().setFrame(getEllipse().getX() + endDrag.x - startDrag.x, this.getEllipse().getY() + endDrag.y - startDrag.y, this.getEllipse().getWidth(), this.getEllipse().getHeight());
        for(DrawAbleShape pt : components){pt.move(startDrag, endDrag); }
    }

    //resizes the object but not to less than 1 width and/or 1 height
    @Override
    public void resize(Point startDrag, Point endDrag) {
        int x = endDrag.x - startDrag.x;
        int y = endDrag.y - startDrag.y;
        if ((this.getEllipse().getWidth() + x) < 1) {
            x = (int) this.getEllipse().getWidth() - 1;
            x = -x;
        }
        if ((this.getEllipse().getHeight() + y) < 1) {
            y = (int) this.getEllipse().getHeight() - 1;
            y = -y;
        }
        Ellipse2D e = new Ellipse2D.Float((int) this.getEllipse().getX(), (int) this.getEllipse().getY(), (int) this.getEllipse().getWidth() + x, (int) this.getEllipse().getHeight() + y);
        this.setEllipse(e);
        for(DrawAbleShape pt : components){pt.resize(startDrag, endDrag); }
    }

    public Ellipse2D getEllipse() {
        return elip;
    }

    public void setEllipse(Ellipse2D ellipse) {
        this.elip = ellipse;
    }
    
    public void add(DrawAbleShape shape) {
        this.components.add(shape);
    }
    
    public ArrayList<DrawAbleShape> getComponents() {
        return components;
    }
}
