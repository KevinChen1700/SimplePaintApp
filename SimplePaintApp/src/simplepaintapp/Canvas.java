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
    private RectangleDelegate rectangle = RectangleDelegate.getInstance();
    private EllipseDelegate ellipse = EllipseDelegate.getInstance();
    private String action ="";
    private ArrayList<Shape> shapes = new ArrayList<>(); //ArrayList of all rectangles and ellipses made
    private Point startDrag, endDrag;    //remember coordinates of cursor when mouse is being clicked and dragged
    public static Shape ptemp;                 //placeholder for object when editing a object
    private Graphics2D g;                   // used to draw the objects
    private Invoker invoker;
    private VisitorOperations visitor = new VisitorOperations();
        
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

        public void setAction(String action){
        this.action=action;
    }
    
    public ArrayList<Shape> getShapes(){
        return shapes;
    }
    
    //show animation for when editing the objects
    @Override
    public void paint(Graphics graphicsAdapter) {
        
        g = (Graphics2D) graphicsAdapter;
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
        
        for (Shape pt : shapes) {
            
            DrawShapeCommand drawShape = new DrawShapeCommand(pt, g);
            invoker.execute(drawShape);
        }
    }
}
