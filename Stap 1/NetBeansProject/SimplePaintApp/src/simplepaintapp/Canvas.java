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

public class Canvas extends JComponent {

    public Point startDrag, endDrag;    //remember coordinates of cursor when mouse is being clicked and dragged
    public Paint ptemp;                 //placeholder for object when editing a object

    
    
    public Canvas() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                if (GUI.action == "select") {
                    for (int i = GUI.paint.size() - 1; i >= 0; i = i - 1) {
                        Paint pt = GUI.paint.get(i);
                        if (pt.contains(startDrag)) {
                            if (ptemp != null) 
                            {
                                GUI.paint.add(ptemp); //puts previous selection back into the paint arraylist
                            }
                            ptemp = pt; 
                            GUI.paint.remove(pt);
                        }
                    }

                }

            }
            
            //after releasing mouse drag, draw the corresponding object
            public void mouseReleased(MouseEvent e) {
                Point p = new Point(e.getX(), e.getY());
                if (GUI.action == "Rectangle") {
                    MyRectangle obj = new MyRectangle();
                    obj.makeObject(startDrag, p);
                    GUI.paint.add(obj);
                } else if (GUI.action == "Ellipse") {
                    MyEllipse obj = new MyEllipse();
                    obj.makeObject(startDrag, p);
                    GUI.paint.add(obj);
                } else if (ptemp != null) {
                    if (ptemp.contains(startDrag)) {
                        if (GUI.action == "move") {
                            ptemp.move(startDrag, p);
                        } else if (GUI.action == "resize") {
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

    //show animation for when editing the objects
    @Override
    public void paint(Graphics graphicsAdapter) {
        
        Graphics2D g = (Graphics2D) graphicsAdapter;
        boolean movingPtemp = false;

        for (Paint pt : GUI.paint) {
            pt.draw(g);
        }

        if (startDrag != null && endDrag != null && GUI.action != "select") {
            if (GUI.action == "Rectangle") {
                MyRectangle obj = new MyRectangle();
                obj.makeObject(startDrag, endDrag);
                obj.draw(g);
            } else if (GUI.action == "Ellipse") {
                MyEllipse obj = new MyEllipse();
                obj.makeObject(startDrag, endDrag);
                obj.draw(g);
            }
            if (ptemp != null) {
                g.setColor(Color.RED);
                if (ptemp.contains(startDrag)) {
                    if (GUI.action == "move") {
                        ptemp.drawPoints(g, startDrag, endDrag);
                    } else if (GUI.action == "resize") {
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