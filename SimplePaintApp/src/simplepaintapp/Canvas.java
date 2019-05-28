package simplepaintapp;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Canvas extends JComponent {

    private Point startDrag, endDrag;    //remember coordinates of cursor when mouse is being clicked and dragged
    private DrawAbleShape ptemp;                 //placeholder for object when editing a object
    private static ArrayList<DrawAbleShape> shapes = new ArrayList<DrawAbleShape>(); //ArrayList of all rectangles and ellipses made
    private String action = "";
    
    
    public Canvas() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                if (action == "select") {
                    for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                        DrawAbleShape pt = shapes.get(i);
                        if (pt.contains(startDrag)) {
                            if (ptemp != null) 
                            {
                                shapes.add(ptemp); //puts previous selection back into the paint arraylist
                            }
                            ptemp = pt; 
                            shapes.remove(pt);
                        }
                    }

                }

            }
            
            //after releasing mouse drag, draw the corresponding object
            public void mouseReleased(MouseEvent e) {
                Point p = new Point(e.getX(), e.getY());
                if (action == "Rectangle") {
                    MyRectangle obj = new MyRectangle();
                    obj.makeObject(startDrag, p);
                    shapes.add(obj);
                } else if (action == "Ellipse") {
                    MyEllipse obj = new MyEllipse();
                    obj.makeObject(startDrag, p);
                    shapes.add(obj);
                } else if (ptemp != null) {
                    if (ptemp.contains(startDrag)) {
                        if (action == "move") {
                            ptemp.move(startDrag, p);
                        } else if (action == "resize") {
                            ptemp.resize(startDrag, p);
                        }
                    }
                }
                startDrag = null;
                endDrag = null;
                repaint();
            }

        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());  //get coordinates during releasing mouse drag
                repaint();
            }
        });

    }
    
    public void setAction(String action ){
        this.action = action;
    }

    //show animation for when editing the objects
    @Override
    public void paint(Graphics graphicsAdapter) {
        
        Graphics2D g = (Graphics2D) graphicsAdapter;
        boolean movingPtemp = false;

        for (DrawAbleShape pt : shapes) {
            pt.draw(g);
        }

        if (startDrag != null && endDrag != null && action != "select") {
            if (action == "Rectangle") {
                MyRectangle obj = new MyRectangle();
                obj.makeObject(startDrag, endDrag);
                obj.draw(g);
            } else if (action == "Ellipse") {
                MyEllipse obj = new MyEllipse();
                obj.makeObject(startDrag, endDrag);
                obj.draw(g);
            }
            if (ptemp != null) {
                g.setColor(Color.RED);
                if (ptemp.contains(startDrag)) {
                    if (action == "move") {
                        ptemp.drawPoints(g, startDrag, endDrag);
                    } else if (action == "resize") {
                        int w = endDrag.x - startDrag.x;
                        int h = endDrag.y - startDrag.y;
                        ptemp.drawExpand(g, w, h);
                    }
                } else {
                    ptemp.draw(g);
                }
            }
        } else if (ptemp != null) {
            g.setColor(Color.RED);
            ptemp.draw(g);
        }
    }
}
