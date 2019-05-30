package simplepaintapp;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Canvas extends JComponent {

    protected static Point startDrag, endDrag;    //remember coordinates of cursor when mouse is being clicked and dragged
    
    public static DrawAbleShape ptemp;                 //placeholder for object when editing a object
    public static DrawAbleShape pt;               //used to get the objects in the shapes list
    public static Graphics2D g;                   // used to draw the objects
    //private ArrayList<Visitable> items;
    Invoker invoker;
        
    public Canvas(Invoker invoker) {
        this.invoker = invoker;
        
      VisitorOperations visitor = new VisitorOperations();

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                if (GUI.action == "select") {
                    SelectCommand select = new SelectCommand();
                    invoker.execute(select);
                }
            }
            
            //after releasing mouse drag, draw the corresponding object
            public void mouseReleased(MouseEvent e) {
                if (GUI.action == "Rectangle") {
                    MyRectangle obj = new MyRectangle ();
                    MakeObjectRect makeObjectRectCommand = new MakeObjectRect(obj, startDrag, endDrag);
                    invoker.execute(makeObjectRectCommand);
                } else if (GUI.action == "Ellipse") {
                    MyEllipse obj = new MyEllipse();
                    MakeObjectEllip makeObjectEllipCommand = new MakeObjectEllip(obj, startDrag, endDrag);
                    invoker.execute(makeObjectEllipCommand);
                } else if (ptemp != null) {
                    if (ptemp.contains(startDrag)) {
                        if (GUI.action == "move") {
                            //MoveObjectCommand moveObjectCommand = new MoveObjectCommand(startDrag, endDrag, ptemp);
                            //invoker.execute(moveObjectCommand);
                            Move moveObject = new Move (startDrag,endDrag,ptemp);
                            visitor.visitMove(moveObject);
                        } else if (GUI.action == "resize") {
                            //ResizeObjectCommand resizeObjectCommand = new ResizeObjectCommand(startDrag, endDrag, ptemp);
                            //invoker.execute(resizeObjectCommand);
                            Resize resizeObject = new Resize (startDrag,endDrag,ptemp);
                            visitor.visitResize(resizeObject);
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
            DrawShapesCommand drawShapes = new DrawShapesCommand();
            invoker.execute(drawShapes);
        }

        if (startDrag != null && endDrag != null && GUI.action != "select") {
            if (GUI.action == "Rectangle") {
                MyRectangle obj = new MyRectangle();
                DrawRect drawRectCommand = new DrawRect(obj);
                invoker.execute(drawRectCommand);
            } else if (GUI.action == "Ellipse") {
                MyEllipse obj = new MyEllipse();
                DrawEllip drawEllipCommand = new DrawEllip(obj);
                invoker.execute(drawEllipCommand);
            }
            if (ptemp != null) {
                if (ptemp.contains(startDrag)) {
                    if (GUI.action == "move") {
                        DrawMoveCommand moveCommand = new DrawMoveCommand();
                        invoker.execute(moveCommand);
                    } else if (GUI.action == "resize") {
                        DrawResizeCommand resizeCommand = new DrawResizeCommand();
                        invoker.execute(resizeCommand);
                    }
                } else {
                    DrawSelectedCommand selectCommand = new DrawSelectedCommand();
                    invoker.execute(selectCommand);
                }
            }
        } else if (ptemp != null) {
            DrawSelectedCommand selectCommand = new DrawSelectedCommand();
            invoker.execute(selectCommand);
        }
    }
}
