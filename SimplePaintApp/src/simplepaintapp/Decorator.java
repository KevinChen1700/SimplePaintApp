package simplepaintapp;

import java.awt.Graphics;

//decorator interface
public interface Decorator {

    public void draw(Graphics g);

    public void move(int x, int y);

    public int getX();

    public int getY();

    public String getContent();
}
