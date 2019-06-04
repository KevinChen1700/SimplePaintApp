package simplepaintapp;

import java.awt.Graphics;
import java.awt.Graphics2D;

//caption class for making texts on a object
public class Caption implements Decorator {

    private String t;   //tekst
    private int x;
    private int y;

    public Caption(String t, int x, int y) {
        this.t = t;
        this.x = x;
        this.y = y;
    }

    //draw the text
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString(t, x, y);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    //simple get values
    public int getX() {
        return x;
    }

    ;
  public int getY() {
        return y;
    }

    ;
  public String getContent() {
        return t;
    }
;
}
