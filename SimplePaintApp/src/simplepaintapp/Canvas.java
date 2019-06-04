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

    private String action ="";
    private ArrayList<DrawAbleShape> shapes = new ArrayList<>(); //ArrayList of all rectangles and ellipses made
    private Point startDrag, endDrag;    //remember coordinates of cursor when mouse is being clicked and dragged
    protected static DrawAbleShape ptemp;                 //placeholder for object when editing a object
    private Graphics2D g;                   // used to draw the objects
    Invoker invoker;
    VisitorOperations visitor = new VisitorOperations();
        
    public Canvas(Invoker invoker) {
        this.invoker = invoker;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                if (action == "select") {
                    SelectCommand select = new SelectCommand(shapes, startDrag);
                    invoker.execute(select);
                }
                else if(action == "add"){
                    AddCommand add = new AddCommand(ptemp, shapes, startDrag);
                    invoker.execute(add);
                }
            }
            
            //after releasing mouse drag, draw the corresponding object
            public void mouseReleased(MouseEvent e) {
                if (action == "Rectangle") {
                    MyRectangle obj = new MyRectangle ();
                    MakeObjectCommand makeObjectCommand = new MakeObjectCommand(shapes, obj, startDrag, endDrag);
                    invoker.execute(makeObjectCommand);
                } else if (action == "Ellipse") {
                    MyEllipse obj = new MyEllipse();
                    MakeObjectCommand makeObjectCommand = new MakeObjectCommand(shapes, obj, startDrag, endDrag);
                    invoker.execute(makeObjectCommand);
                } else if (ptemp != null) {
                    if (ptemp.contains(startDrag)) {
                        if (action == "move") {
                            Move moveObject = new Move (startDrag,endDrag,ptemp);
                            visitor.visitMove(moveObject);
                        } else if (action == "resize") {
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

        public void setAction(String action){
        this.action=action;
    }
    
    public ArrayList<DrawAbleShape> getShapes(){
        return shapes;
    }
    
    //show animation for when editing the objects
    @Override
    public void paint(Graphics graphicsAdapter) {
        
        g = (Graphics2D) graphicsAdapter;

        for (DrawAbleShape pt : shapes) {
            DrawShapesCommand drawShapes = new DrawShapesCommand(pt, g);
            invoker.execute(drawShapes);
        }

        if (startDrag != null && endDrag != null && action != "select") {
            if (action == "Rectangle") {
                MyRectangle obj = new MyRectangle();
                DrawObjCommand draw = new DrawObjCommand(obj, g, startDrag, endDrag);
                invoker.execute(draw);
            } else if (action == "Ellipse") {
                MyEllipse obj = new MyEllipse();
                DrawObjCommand draw = new DrawObjCommand(obj, g, startDrag, endDrag);
                invoker.execute(draw);
            }
            if (ptemp != null) {
                if (ptemp.contains(startDrag)) {
                    if (action == "move") {
                        visitor.drawMove();
                    } else if (action == "resize") {
                        visitor.drawResize();
                    }
                } else {
                    DrawSelectedCommand selectCommand = new DrawSelectedCommand(ptemp, g);
                    invoker.execute(selectCommand);
                }
            }
        } else if (ptemp != null) {
            DrawSelectedCommand selectCommand = new DrawSelectedCommand(ptemp, g);
            invoker.execute(selectCommand);
        }
    }
}
