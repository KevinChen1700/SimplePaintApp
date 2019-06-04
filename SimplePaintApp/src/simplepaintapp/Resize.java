package simplepaintapp;

import java.awt.Point;

//resize class with constructor to get values for resize functionality
public class Resize extends ObjectCommand implements Visitable {

    private Point startDrag;
    private Point endDrag;
    private Shape object;

    public Resize(Point startDrag, Point endDrag, Shape object) {
        this.startDrag = startDrag;
        this.endDrag = endDrag;
        this.object = object;

        int x = endDrag.x - startDrag.x;
        int y = endDrag.y - startDrag.y;
        if ((object.getW() + x) < 1) {
            endDrag.x = startDrag.x - object.getW();
        }
        if ((object.getH() + y) < 1) {
            endDrag.y = startDrag.y - object.getH();
        }
    }

    //accept the visitor
    public void accept(Visitor visitor) {
        visitor.visitResize(this);
    }

    public Point getStartDrag() {
        return startDrag;
    }

    public Point getEndDrag() {
        return endDrag;
    }

    public Shape getObject() {
        return object;
    }

    private void resize(Point startDrag, Point endDrag) {
        object.setW(object.getW() + endDrag.x - startDrag.x);
        object.setH(object.getH() + endDrag.y - startDrag.y);
        for (Shape s : object.getComponents()) {
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
    }

    //add undo and redo functionality
    public void undo() {
        resize(endDrag, startDrag);
    }

    public void redo() {
        resize(startDrag, endDrag);
    }
}
