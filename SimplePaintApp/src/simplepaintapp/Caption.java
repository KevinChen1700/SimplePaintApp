/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Kevin
 */
public class Caption implements Decorator {
    private String t;
    private int x;
    private int y;
   public Caption(String t,int x, int y)
   {
       this.t = t;
       this.x = x;
       this.y = y;
   }
   
  public void draw(Graphics g)
  {
      Graphics2D g2d = (Graphics2D) g;
      g2d.drawString(t,x,y);
  }
  
  public void move(int x, int y)
  {
      this.x += x;
      this.y +=y;
  }
  
  public int getX(){return x;};
  public int getY(){return y;};
  public String getContent(){return t;};
}
