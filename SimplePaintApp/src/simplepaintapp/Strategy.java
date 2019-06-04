package simplepaintapp;

import java.awt.Graphics;

//Strategy interface
public interface Strategy {

    public void draw(Graphics g, int x, int y, int w, int h);

    public String toString();

}
