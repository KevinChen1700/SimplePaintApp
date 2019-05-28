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

    public static Point startDrag, endDrag, p;    //remember coordinates of cursor when mouse is being clicked and dragged
    public static DrawAbleShape ptemp;                 //placeholder for object when editing a object
    public static DrawAbleShape pt;               //used to get the objects in the shapes list
    public static Graphics2D g;                   // used to draw the objects
    Invoker invoker = new Invoker();
        
    public Canvas() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                if (GUI.action == "select") {
                    selectCommand select = new selectCommand();
                    invoker.takeAction(select);
                    invoker.placeAction();
                }
            }
            
            //after releasing mouse drag, draw the corresponding object
            public void mouseReleased(MouseEvent e) {
                 p = new Point (e.getX(), e.getY());
                if (GUI.action == "Rectangle") {
                    MyRectangle obj = new MyRectangle ();
                    makeObjectRect makeObjectRectCommand = new makeObjectRect(obj);
                    invoker.takeAction(makeObjectRectCommand);
                    invoker.placeAction();
                } else if (GUI.action == "Ellipse") {
                    MyEllipse obj = new MyEllipse();
                    makeObjectEllip makeObjectEllipCommand = new makeObjectEllip(obj);
                    invoker.takeAction(makeObjectEllipCommand);
                    invoker.placeAction();
                } else if (ptemp != null) {
                    if (ptemp.contains(startDrag)) {
                        if (GUI.action == "move") {
                            moveCommand moveObjectCommand = new moveCommand();
                            invoker.takeAction(moveObjectCommand);
                            invoker.placeAction();
                        } else if (GUI.action == "resize") {
                            resizeCommand resizeObjectCommand = new resizeCommand();
                            invoker.takeAction(resizeObjectCommand);
                            invoker.placeAction();
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
        
        g = (Graphics2D) graphicsAdapter;

        for (DrawAbleShape pt : GUI.shapes) {
            this.pt = pt;
            drawShapesCommand drawShapes = new drawShapesCommand();
            invoker.takeAction(drawShapes);
            invoker.placeAction();
        }

        if (startDrag != null && endDrag != null && GUI.action != "select") {
            if (GUI.action == "Rectangle") {
                MyRectangle obj = new MyRectangle();
                drawRect drawRectCommand = new drawRect(obj);
                invoker.takeAction(drawRectCommand);
                invoker.placeAction();
            } else if (GUI.action == "Ellipse") {
                MyEllipse obj = new MyEllipse();
                drawEllip drawEllipCommand = new drawEllip(obj);
                invoker.takeAction(drawEllipCommand);
                invoker.placeAction();
            }
            if (ptemp != null) {
                g.setColor(Color.RED);
                if (ptemp.contains(startDrag)) {
                    if (GUI.action == "move") {
                        drawMoveCommand moveCommand = new drawMoveCommand();
                        invoker.takeAction(moveCommand);
                        invoker.placeAction();
                    } else if (GUI.action == "resize") {
                        drawResizeCommand resizeCommand = new drawResizeCommand();
                        invoker.takeAction(resizeCommand);
                        invoker.placeAction();
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
