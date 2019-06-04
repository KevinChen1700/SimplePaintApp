package simplepaintapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static simplepaintapp.Invoker.undoStack;

//Functionalities for Visitor 
public class VisitorOperations implements Visitor {

    public VisitorOperations() {
    }

    //move function using visitor
    public void visitMove(Move object) {
        Shape shape = object.getObject();
        shape.move(object.getStartDrag(), object.getEndDrag());

        if (object instanceof ObjectCommand) {
            undoStack.push(object);
        }
    }

    //resize function using visitor
    public void visitResize(Resize object) {
        Shape shape = object.getObject();
        Point endDrag = object.getEndDrag();
        Point startDrag = object.getStartDrag();
        shape.setW(shape.getW() + object.getEndDrag().x - object.getStartDrag().x);
        shape.setH(shape.getH() + object.getEndDrag().y - object.getStartDrag().y);
        for (Shape s : shape.getComponents()) {
            Point temp = endDrag;
            int x = endDrag.x - startDrag.x;
            int y = endDrag.y - startDrag.y;
            if ((s.getW() + x) < 1) {
                temp.x = startDrag.x - s.getW();
            }
            if ((s.getH() + y) < 1) {
                temp.y = startDrag.y - s.getH();
            }
            s.setW(s.getW() + temp.x - startDrag.x);
            s.setH(s.getH() + temp.y - startDrag.y);
        }
        if (object instanceof ObjectCommand) {
            undoStack.push(object);
        }
    }

    public void drawMove(Graphics g, Shape ptemp, Point startDrag, Point endDrag) {
        g.setColor(Color.RED);

        //draw moving object by checking if the string of the object contains "Rectangle or "Ellipse"
        if (ptemp.toString().contains("Rectangle")) {
            g.drawRect((ptemp.getX() + endDrag.x - startDrag.x), (ptemp.getY() + endDrag.y - startDrag.y), ptemp.getW(), ptemp.getH());
        } else {
            g.drawOval((ptemp.getX() + endDrag.x - startDrag.x), (ptemp.getY() + endDrag.y - startDrag.y), ptemp.getW(), ptemp.getH());
        }

        //draw moving grouped objects
        for (Shape s : ptemp.getComponents()) {

            if (s.toString().contains("Rectangle")) {
                g.drawRect((s.getX() + endDrag.x - startDrag.x), (s.getY() + endDrag.y - startDrag.y), s.getW(), s.getH());
            } else {
                g.drawOval((s.getX() + endDrag.x - startDrag.x), (s.getY() + endDrag.y - startDrag.y), s.getW(), s.getH());
            }
        }

        //draw moving the texts
        for (Decorator de : ptemp.getDecorators()) {
            Graphics2D g2d = (Graphics2D) g;
            g.drawString(de.getContent(), de.getX() + endDrag.x - startDrag.x, de.getY() + endDrag.y - startDrag.y);
        }
    }

    public void drawResize(Graphics g, Shape ptemp, Point startDrag, Point endDrag) {
        g.setColor(Color.RED);

        //draw resizing object by checking if the string of the object contains "Rectangle" , else it is a ellipse
        if (ptemp.toString().contains("Rectangle")) {
            g.drawRect(ptemp.getX(), ptemp.getY(), (ptemp.getW() + endDrag.x - startDrag.x), (ptemp.getH() + endDrag.y - startDrag.y));
        } else {
            g.drawOval(ptemp.getX(), ptemp.getY(), (ptemp.getW() + endDrag.x - startDrag.x), (ptemp.getH() + endDrag.y - startDrag.y));
        }

        //draw resizing grouped objects
        for (Shape s : ptemp.getComponents()) {
            Point temp = endDrag;
            int x = endDrag.x - startDrag.x;
            int y = endDrag.y - startDrag.y;
            if ((s.getW() + x) < 1) {
                temp.x = startDrag.x - s.getW();
            }
            if ((s.getH() + y) < 1) {
                temp.y = startDrag.y - s.getH();
            }
            if (s.toString().contains("Rectangle")) {
                g.drawRect(s.getX(), s.getY(), (s.getW() + temp.x - startDrag.x), (s.getH() + temp.y - startDrag.y));
            } else {
                g.drawOval(s.getX(), s.getY(), (s.getW() + temp.x - startDrag.x), (s.getH() + temp.y - startDrag.y));
            }
        }
    }

    //put the contents of writeList into save.txt
    public void visitSave(VisitorSave saveFile) {
        try {
            PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
            writeList(saveFile.getShapes(), writer, 0, "");
            writer.close();
        } catch (Exception ex) {
            System.out.print("Error.");
        }
    }

    //convert all the values of the objects,grouped objects,and drawStrings into a string and remember it
    private void writeList(ArrayList<Shape> shapes, PrintWriter writer, int groupCount, String tabs) {
        for (Shape shape : shapes) {
            ArrayList<Shape> ptComponents = shape.getComponents();
            ArrayList<Decorator> ptDecorators = shape.getDecorators();

            if (!(ptComponents.isEmpty())) {
                writer.println(tabs + "group " + groupCount);
                writer.println(tabs + shape.toString());
                if (!(ptDecorators.isEmpty())) {
                    for (Decorator de : ptDecorators) {
                        writer.println(tabs + "Decorator " + de.getContent() + " " + de.getX() + " " + de.getY());
                    }
                }

                writeList(ptComponents, writer, groupCount + 1, tabs + "    ");
            } else {
                writer.println(tabs + shape.toString());
                if (!(ptDecorators.isEmpty())) {
                    for (Decorator de : ptDecorators) {
                        writer.println(tabs + "Decorator " + de.getContent() + " " + de.getX() + " " + de.getY());
                    }
                }
            }
        }

    }
}
