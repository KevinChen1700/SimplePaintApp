package simplepaintapp;

import java.awt.Point;

//move class with constructor to get values for move functionality
public class Move extends ObjectCommand implements Visitable {

    private Point startDrag;
    private Point endDrag;
    private Shape object;

    public Move(Point startDrag, Point endDrag, Shape object) {
        this.startDrag = startDrag;
        this.endDrag = endDrag;
        this.object = object;
    }

    //accept the visitor
    public void accept(Visitor visitor) {
        visitor.visitMove(this);
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

    private void move(Point startDrag, Point endDrag) {
        object.setX(object.getX() + endDrag.x - startDrag.x);
        object.setY(object.getY() + endDrag.y - startDrag.y);
    }

    //add undo and redo functionality
    public void undo() {
        move(endDrag, startDrag);
    }

    public void redo() {
        move(startDrag, endDrag);
    }
}
