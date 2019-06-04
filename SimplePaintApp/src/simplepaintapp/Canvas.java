package simplepaintapp;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Canvas extends JComponent {
    private RectangleDelegate rectangle = RectangleDelegate.getInstance();   //through instance of singleton, make a rectangle
    private EllipseDelegate ellipse = EllipseDelegate.getInstance();         //through instance of singleton, make a ellipse
    private String action ="";           //this string will be used for our functions depending on what sits inside the string
    private ArrayList<Shape> shapes = new ArrayList<>(); //ArrayList of all rectangles and ellipses made
    private Point startDrag, endDrag;    //remember coordinates of cursor when mouse is being clicked and dragged
    public static Shape ptemp;                 //placeholder for object when editing a object
    private Graphics2D g;                   // used to draw the objects
    private Invoker invoker;             //invoker used for command executes
    private VisitorOperations visitor = new VisitorOperations();      //visitor used for move,resize and save
        
     //functions for when mouse is pressed
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
                    CreateShape createCommand = new CreateShape(shapes, rectangle, startDrag, endDrag);
                    invoker.execute(createCommand);
                } else if (action == "Ellipse") {
                    CreateShape createCommand = new CreateShape(shapes, ellipse, startDrag, endDrag);
                    invoker.execute(createCommand);
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
        //change the action string to the corresponding action
        public void setAction(String action){
        this.action=action;
    }
     //get the shapes
    public ArrayList<Shape> getShapes(){
        return shapes;
    }
    
    //show animation for when editing the objects
    @Override
    public void paint(Graphics graphicsAdapter) {
        
        g = (Graphics2D) graphicsAdapter;
        for (Shape pt : shapes) {
            
            DrawShapeCommand drawShape = new DrawShapeCommand(pt, g);
            invoker.execute(drawShape);
        }
        
        //draw animation for rectangle and ellipse
        if (startDrag != null && endDrag != null && action != "select") {
            if (action == "Rectangle") {
                Shape obj = new Shape(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y), rectangle);
                DrawShapeCommand drawShape = new DrawShapeCommand(obj, g);
                invoker.execute(drawShape);
            } else if (action == "Ellipse") {
                Shape obj = new Shape(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y), ellipse);
                DrawShapeCommand drawShape = new DrawShapeCommand(obj, g);
                invoker.execute(drawShape);
            }
            
             //draw animation for move and resize
            if (ptemp != null) {
                if (ptemp.contains(startDrag)) {
                    if (action == "move") {
                        visitor.drawMove(g, ptemp, startDrag, endDrag);
                    } else if (action == "resize") {
                        visitor.drawResize(g, ptemp, startDrag, endDrag);
                    }
                } else {
                    g.setColor(Color.red);
                    DrawShapeCommand drawShape = new DrawShapeCommand(ptemp, g);
                    invoker.execute(drawShape);
                }
            }
        } else if (ptemp != null) {
            g.setColor(Color.red);
            DrawShapeCommand drawShape = new DrawShapeCommand(ptemp, g);
            invoker.execute(drawShape);
        }
        
        
        
    }
}
